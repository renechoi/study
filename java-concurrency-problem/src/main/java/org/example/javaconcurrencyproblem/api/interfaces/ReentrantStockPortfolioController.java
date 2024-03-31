package org.example.javaconcurrencyproblem.api.interfaces;

import java.util.concurrent.locks.ReentrantLock;

import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.BuyOrderRequest;
import org.example.javaconcurrencyproblem.api.domain.SellOrderRequest;
import org.example.javaconcurrencyproblem.api.infrastructure.StockPortfolioRepositoryAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@RestController
@RequestMapping("/stock-portfolio/reentrantLock")
@RequiredArgsConstructor
public class ReentrantStockPortfolioController {

	private final StockPortfolioRepositoryAdapter portfolioRepository;
	private final ReentrantLock lock = new ReentrantLock();

	@PostMapping("/{id}/buy")
	public StockPortfolio buyStock(@PathVariable Long id, @RequestBody BuyOrderRequest request) {
		lock.lock();
		try {
			StockPortfolio stockPortfolio = portfolioRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
			if (stockPortfolio.isRequestDelayBelowMs(1000)) {
				throw new IllegalStateException("매수 요청이 너무 자주 발생했습니다. 나중에 다시 시도해 주세요.");
			}
			stockPortfolio.addStocks(request.getAaplStockAmount());
			return portfolioRepository.save(stockPortfolio);
		} finally {
			lock.unlock();
		}
	}

	@PostMapping("/{id}/sell")
	public StockPortfolio sellStock(@PathVariable Long id, @RequestBody SellOrderRequest request) {
		lock.lock();
		try {
			StockPortfolio stockPortfolio = portfolioRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
			Long amount = request.getAaplStockAmount();
			if (stockPortfolio.getAaplStockAmount() < amount) {
				throw new RuntimeException("매도 가능한 주식이 없습니다.");
			}
			stockPortfolio.subtractStocks(amount);
			return portfolioRepository.save(stockPortfolio);
		} finally {
			lock.unlock();
		}
	}

	@GetMapping("/{id}")
	public StockPortfolio fetchStock(@PathVariable Long id) {
		lock.lock();
		try {
			return portfolioRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
		} finally {
			lock.unlock();
		}
	}
}
