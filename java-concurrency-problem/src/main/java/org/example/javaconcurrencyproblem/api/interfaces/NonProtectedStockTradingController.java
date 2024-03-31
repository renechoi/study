package org.example.javaconcurrencyproblem.api.interfaces;

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
 * @since : 2024/03/20
 */

@RestController
@RequestMapping("/stock-portfolio/normal")
@RequiredArgsConstructor
public class NonProtectedStockTradingController {

	private final StockPortfolioRepositoryAdapter portfolioRepository;

	@PostMapping("/{id}/buy")
	public StockPortfolio buyStock(@PathVariable Long id, @RequestBody BuyOrderRequest request) {
		StockPortfolio portfolio = portfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		if (portfolio.isRequestDelayBelowMs(1000)) {
			throw new IllegalStateException("매수 요청이 너무 자주 발생했습니다. 나중에 다시 시도해 주세요.");
		}
		portfolio.addStocks(request.getAaplStockAmount());
		return portfolioRepository.save(portfolio);
	}

	@PostMapping("/{id}/sell")
	public StockPortfolio sellStock(@PathVariable Long id, @RequestBody SellOrderRequest request) {
		Long amount = request.getAaplStockAmount();
		StockPortfolio stockPortfolio = portfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		if (stockPortfolio.getAaplStockAmount() < amount) {
			throw new RuntimeException("매도 가능한 주식이 없습니다.");
		}
		stockPortfolio.subtractStocks(amount);
		return portfolioRepository.save(stockPortfolio);
	}

	@GetMapping("/{id}")
	public StockPortfolio fetchStock(@PathVariable Long id) {
		return portfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
}

