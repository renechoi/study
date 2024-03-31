package org.example.javaconcurrencyproblem.cucumber.steps;

import static org.example.javaconcurrencyproblem.testhelpers.executor.DefaultStockPortfolioApiExecutor.*;
import static org.example.javaconcurrencyproblem.testhelpers.executor.DynamicStockPortfolioApiExecutor.*;
import static org.example.javaconcurrencyproblem.testhelpers.parser.ExtractableResponseParser.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.example.javaconcurrencyproblem.api.domain.AtomicStockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.BuyOrderRequest;
import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.example.javaconcurrencyproblem.testhelpers.context.UrlDynamicContext;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java8.En;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

/**
 * @author : Rene Choi
 * @since : 2024/02/23
 */
public class StockPortfolioStepDef implements En {

	private StockPortfolio stockPortfolio;
	private AtomicStockPortfolio atomicStockPortfolio;

	private ExtractableResponse<Response> fetchResponse;

	private List<ExtractableResponse<Response>> buyOrderResponses;

	@Before
	public void setup() {
		stockPortfolio = null;
		fetchResponse = null;
		atomicStockPortfolio = null;
		buyOrderResponses = new ArrayList<>();
	}

	@Before
	public void setupBackground(Scenario scenario) {
		UrlDynamicContext.apiType = scenario.getName();
	}

	public StockPortfolioStepDef() {
		Given("{string} API를 호출", this::setupApiUrl);
		Given("초기 주식 수가 {long}인 포트폴리오가 주어졌을 때", this::createPortfolioAndStockAmount);
		Given("초기 주식 수가 {long}인 Atomic 포트폴리오가 주어졌을 때", this::createAtomicPortfolioAndStockAmount);
		And("다음 스텝을 위해 {int}초간 딜레이", this::delayForNextStep);
		And("제한 간격을 준수하여 {long}을 매수하려고 {int}회 시도한다", this::doExecuteValidatedRequestForBuyOrder);
		And("{int}개의 스레드가 동시에 {long}을 매수하려고 시도한다", this::doExecuteRequestForBuyOrderRacingMultipleThread);
		And("{int}개의 스레드가 동시에 Atomic {long}을 매수하려고 시도한다", this::doExecuteRequestForAtomicBuyOrderRacingMultipleThread);
		When("포트폴리오를 조회하면", this::fetchPortfolio);
		When("Atomic 포트폴리오를 조회하면", this::fetchAtomicPortfolio);
		Then("의도한 예외가 확인된다", this::verifyExpectedException);
		Then("포트폴리오에 주식 수는 {long}으로 확인되어야 한다", this::verifyTotalStockAmount);
		Then("Atomic 포트폴리오에 주식 수는 {long}으로 확인되어야 한다", this::verifyAtomicTotalStockAmount);
		And("의도한 예외는 발생하지 않는다", this::verifyExpectedExceptionNotFound);
		And("주식 수는 {long} 혹은 {long}으로 확인된다", this::totalStockAmountFoundWithTwoPossibleAmount);
	}

	private void setupApiUrl(String apiType) {
		UrlDynamicContext.apiType = apiType;
	}

	private void verifyExpectedException() {
		assertTrue(buyOrderResponses.stream().anyMatch(response -> response.statusCode() != 200), "의도한 예외가 확인되지 않았습니다.");
	}

	private void verifyExpectedExceptionNotFound() {
		assertTrue(buyOrderResponses.stream().noneMatch(response -> response.statusCode() != 200), "의도한 예외가 확인되지 않았습니다.");
	}

	private void doExecuteValidatedRequestForBuyOrder(long amount, int count) {
		for (int i = 0; i < count; i++) {
			buyStock(stockPortfolio.getId(), BuyOrderRequest.of(amount));
			delayForNextStep(1);
		}
	}

	private void createPortfolioAndStockAmount(long amount) {
		stockPortfolio = parsePortfolio(createPortfolio());
		delayForNextStep(1);
		buyStock(stockPortfolio.getId(),  BuyOrderRequest.of(amount));
	}

	private void createAtomicPortfolioAndStockAmount(long amount) {
		atomicStockPortfolio = parseAtomicPortfolio(createAtomicPortfolio());
		delayForNextStep(1);
		buyStock(atomicStockPortfolio.getId(),  BuyOrderRequest.of(amount));
	}

	private void delayForNextStep(int delay) {
		try {
			Thread.sleep(delay * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void doExecuteRequestForBuyOrderRacingMultipleThread(int threads, long amount) {
		ExecutorService executorService = Executors.newFixedThreadPool(threads);
		List<Future<ExtractableResponse<Response>>> futures = new ArrayList<>();
		for (int i = 0; i < threads; i++) {
			Future<ExtractableResponse<Response>> future = executorService.submit(() -> buyStock(stockPortfolio.getId(),  BuyOrderRequest.of(amount)));
			futures.add(future);
		}
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				throw new IllegalStateException("모든 매수 작업이 완료되지 않았습니다.");
			}
			for (Future<ExtractableResponse<Response>> future : futures) {
				buyOrderResponses.add(future.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("매수 작업을 기다리는 동안 인터럽트 발생", e);
		}
	}

	private void doExecuteRequestForAtomicBuyOrderRacingMultipleThread(int threads, long amount) {
		ExecutorService executorService = Executors.newFixedThreadPool(threads);
		List<Future<ExtractableResponse<Response>>> futures = new ArrayList<>();
		for (int i = 0; i < threads; i++) {
			Future<ExtractableResponse<Response>> future = executorService.submit(() -> buyStock(atomicStockPortfolio.getId(),  BuyOrderRequest.of(amount)));
			futures.add(future);
		}
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				throw new IllegalStateException("모든 매수 작업이 완료되지 않았습니다.");
			}
			for (Future<ExtractableResponse<Response>> future : futures) {
				buyOrderResponses.add(future.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("매수 작업을 기다리는 동안 인터럽트 발생", e);
		}
	}

	private void fetchPortfolio() {
		fetchResponse = fetchAmount(stockPortfolio.getId());
		stockPortfolio = parsePortfolio(fetchResponse);
	}

	private void fetchAtomicPortfolio() {
		fetchResponse = fetchAmount(atomicStockPortfolio.getId());
		atomicStockPortfolio = parseAtomicPortfolio(fetchResponse);
	}

	private void verifyTotalStockAmount(long expectedAmount) {
		assertEquals(expectedAmount, stockPortfolio.getAaplStockAmount(), "기대한 주식이 일치하지 않습니다");
	}

	private void totalStockAmountFoundWithTwoPossibleAmount(long expectedAmount1, long expectedAmount2) {
		long aaplStockAmount = stockPortfolio.getAaplStockAmount();
		boolean isAmountCorrect = aaplStockAmount == expectedAmount1 || aaplStockAmount == expectedAmount2;
		assertTrue(isAmountCorrect, "주식이 예상한 " + expectedAmount1 + " 혹은 " + expectedAmount2 + "와 일치하지 않습니다. 실제 주식: " + aaplStockAmount);
	}

	private void verifyAtomicTotalStockAmount(long expectedAmount) {
		assertEquals(expectedAmount, atomicStockPortfolio.getAaplStockAmount(), "기대한 주식이 일치하지 않습니다");
	}

}
