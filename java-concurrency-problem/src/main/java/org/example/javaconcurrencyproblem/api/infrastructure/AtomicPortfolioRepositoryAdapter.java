package org.example.javaconcurrencyproblem.api.infrastructure;

import java.util.Optional;

import org.example.javaconcurrencyproblem.api.domain.AtomicStockPortfolio;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@Component
@RequiredArgsConstructor
public class AtomicPortfolioRepositoryAdapter {
	private final AtomicPortfolioRepository atomicPortfolioRepository;


	public Optional<AtomicStockPortfolio> findById(Long id) {
		simulateDatabaseDelay();
		return atomicPortfolioRepository.findById(id);
	}

	public AtomicStockPortfolio save(AtomicStockPortfolio atomicStockPortfolio) {
		simulateDatabaseDelay();
		return atomicPortfolioRepository.save(atomicStockPortfolio);
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
