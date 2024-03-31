package org.example.javaconcurrencyproblem.api.interfaces;

import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.BuyOrderRequest;
import org.example.javaconcurrencyproblem.api.domain.SellOrderRequest;
import org.example.javaconcurrencyproblem.api.infrastructure.StockPortfolioRepositoryAdapter;
import org.example.javaconcurrencyproblem.client.IdentifierLockManager;
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
@RequestMapping("/stock-portfolio/identifier")
@RequiredArgsConstructor
public class IdentifierStockPortfolioController {

	private final StockPortfolioRepositoryAdapter stockPortfolioRepository;

	@PostMapping("/{id}/buy")
	public StockPortfolio buyStock(@PathVariable Long id, @RequestBody BuyOrderRequest request) {
		String identifier = id + ":" + request.getAaplStockAmount();
		if (!IdentifierLockManager.tryLock(identifier)) {
			throw new IllegalStateException("이미 처리 중인 요청입니다. 나중에 다시 시도해 주세요.");
		}

		try {
			StockPortfolio stockPortfolio = stockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
			if (stockPortfolio.isRequestDelayBelowMs(1000)) {
				throw new IllegalStateException("매수 요청이 너무 자주 발생했습니다. 나중에 다시 시도해 주세요.");
			}
			stockPortfolio.addStocks(request.getAaplStockAmount());
			return stockPortfolioRepository.save(stockPortfolio);
		} finally {
			IdentifierLockManager.unlock(identifier);
		}
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
