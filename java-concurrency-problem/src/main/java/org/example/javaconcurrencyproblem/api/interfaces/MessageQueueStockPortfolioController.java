package org.example.javaconcurrencyproblem.api.interfaces;

import static java.time.LocalDateTime.*;
import static org.springframework.http.ResponseEntity.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.BuyOrderRequest;
import org.example.javaconcurrencyproblem.api.domain.BuyOrderTask;
import org.example.javaconcurrencyproblem.api.domain.SellOrderRequest;
import org.example.javaconcurrencyproblem.api.infrastructure.StockPortfolioRepositoryAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@RestController
@RequestMapping("/stock-portfolio/queue")
@RequiredArgsConstructor
public class MessageQueueStockPortfolioController {

	private final StockPortfolioRepositoryAdapter stockPortfolioRepository;
	private final ConcurrentLinkedQueue<BuyOrderTask> buyOrderTasksQueue = new ConcurrentLinkedQueue<>();
	private ExecutorService executorService;

	@PostConstruct
	public void init() {
		executorService = Executors.newSingleThreadExecutor();
		executorService.submit(this::processBuyOrderTasks);
	}

	private void processBuyOrderTasks() {
		while (!Thread.currentThread().isInterrupted()) {
			BuyOrderTask task = buyOrderTasksQueue.poll();
			if (task != null) {
				try {
					performBuyOrder(task);
					task.getFutureResult().complete(ok().build()); // 성공 응답 설정
				} catch(Exception e){
					task.getFutureResult().completeExceptionally(e); // 예외 발생시 실패 응답 설정
				}
			}
		}
	}

	private void performBuyOrder(BuyOrderTask task) {
		StockPortfolio stockPortfolio = stockPortfolioRepository.findById(task.getPortfolioId()).orElseThrow(EntityNotFoundException::new);
		if (stockPortfolio.isRequestDelayBelowMs(1000)) {
			throw new IllegalStateException("매수 요청이 너무 자주 발생했습니다. 나중에 다시 시도해 주세요.");
		}
		stockPortfolio.addStocks(task.getAaplStockAmount());
		stockPortfolioRepository.save(stockPortfolio);
	}

	@PostMapping("/{id}/buy")
	public CompletableFuture<ResponseEntity<?>> buyStock(@PathVariable Long id, @RequestBody BuyOrderRequest request) {
		CompletableFuture<ResponseEntity<?>> futureResult = new CompletableFuture<>();
		buyOrderTasksQueue.offer(new BuyOrderTask(id, request.getAaplStockAmount(), now(), futureResult));
		return futureResult;
	}

	@PostMapping("/{id}/sell")
	public StockPortfolio sellStock(@PathVariable Long id, @RequestBody SellOrderRequest request) {
		Long amount = request.getAaplStockAmount();
		StockPortfolio stockPortfolio = stockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		if (stockPortfolio.getAaplStockAmount() < amount) {
			throw new RuntimeException("매도 가능한 주식이 없습니다.");
		}
		stockPortfolio.subtractStocks(amount);
		return stockPortfolioRepository.save(stockPortfolio);
	}

	@GetMapping("/{id}")
	public StockPortfolio fetchStock(@PathVariable Long id) {
		return stockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
}
