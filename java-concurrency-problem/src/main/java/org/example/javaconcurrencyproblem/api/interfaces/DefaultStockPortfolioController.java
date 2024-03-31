package org.example.javaconcurrencyproblem.api.interfaces;

import static org.springframework.http.HttpStatus.*;

import java.util.Random;
import java.util.stream.Collectors;

import org.example.javaconcurrencyproblem.api.domain.AtomicStockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.example.javaconcurrencyproblem.api.infrastructure.AtomicPortfolioRepository;
import org.example.javaconcurrencyproblem.api.infrastructure.PortfolioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@RestController
@RequestMapping("/stock-portfolio/default")
@RequiredArgsConstructor
public class DefaultStockPortfolioController {

	private final PortfolioRepository portfolioRepository;
	private final AtomicPortfolioRepository atomicPortfolioRepository;
	@PostMapping
	public ResponseEntity<StockPortfolio> create() {
		return ResponseEntity.status(CREATED).body(portfolioRepository.save(StockPortfolio.builder().portfolioId(generateRandomNumber()).owner(generateRandomOwner()).build()));
	}

	@PostMapping("/atomic")
	public ResponseEntity<AtomicStockPortfolio> createAtomic() {
		return ResponseEntity.status(CREATED).body(atomicPortfolioRepository.save(AtomicStockPortfolio.builder().portfolioId(generateRandomNumber()).owner(generateRandomOwner()).build()));
	}


	private String generateRandomNumber() {
		return new Random().ints(0, 10)
			.limit(6)
			.mapToObj(String::valueOf)
			.collect(Collectors.joining());
	}

	private String generateRandomOwner() {
		return new Random().ints(65, 91)
			.limit(5)
			.mapToObj(c -> String.valueOf((char) c))
			.collect(Collectors.joining());
	}

}
