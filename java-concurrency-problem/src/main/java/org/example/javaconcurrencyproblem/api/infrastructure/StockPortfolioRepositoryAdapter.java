package org.example.javaconcurrencyproblem.api.infrastructure;

import java.util.Optional;

import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@Component
@RequiredArgsConstructor
public class StockPortfolioRepositoryAdapter {
	private final PortfolioRepository portfolioRepository;


	public Optional<StockPortfolio> findById(Long id) {
		simulateDatabaseDelay();
		return portfolioRepository.findById(id);
	}

	public StockPortfolio save(StockPortfolio stockPortfolio) {
		simulateDatabaseDelay();
		return portfolioRepository.save(stockPortfolio);
	}

	private void simulateDatabaseDelay() {
		try {
			long delay = 200 + (long) (Math.random() * (300 - 200));
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
