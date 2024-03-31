package org.example.javaconcurrencyproblem.api.interfaces;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import org.example.javaconcurrencyproblem.api.domain.AtomicStockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.BuyOrderRequest;
import org.example.javaconcurrencyproblem.api.domain.SellOrderRequest;
import org.example.javaconcurrencyproblem.api.infrastructure.AtomicPortfolioRepositoryAdapter;
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
@RequestMapping("/stock-portfolio/atomic-approach")
@RequiredArgsConstructor
public class AtomicApproachStockPortfolioController {

	private final AtomicPortfolioRepositoryAdapter AtomicStockPortfolioRepository;

	private AtomicReference<LocalDateTime> lastDepositTimestamp = new AtomicReference<>(LocalDateTime.now());

	@PostMapping("/{id}/buy")
	public AtomicStockPortfolio buyStock(@PathVariable Long id, @RequestBody BuyOrderRequest request) {
		AtomicStockPortfolio atomicStockPortfolio = AtomicStockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime lastTimestamp = lastDepositTimestamp.get();

		// 중복 요청 검사
		if (Duration.between(lastTimestamp, now).toMillis() < 1000) {
			throw new IllegalStateException("매수 요청이 너무 자주 발생했습니다. 나중에 다시 시도해 주세요.");
		}

		// CAS 연산으로 타임스탬프 업데이트
		if (!lastDepositTimestamp.compareAndSet(lastTimestamp, now)) {
			throw new IllegalStateException("동시 요청 감지. 요청을 처리할 수 없습니다.");
		}

		// 매수 처리
		atomicStockPortfolio.addStocks(request.getAaplStockAmount());
		return AtomicStockPortfolioRepository.save(atomicStockPortfolio);
	}

	@PostMapping("/{id}/sell")
	public AtomicStockPortfolio sellStock(@PathVariable Long id, @RequestBody SellOrderRequest request) {
		Long amount = request.getAaplStockAmount();
		AtomicStockPortfolio atomicStockPortfolio = AtomicStockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		if (atomicStockPortfolio.getAaplStockAmount() < amount) {
			throw new RuntimeException("매도 가능한 주식이 없습니다.");
		}
		atomicStockPortfolio.subtractStocks(amount);
		return AtomicStockPortfolioRepository.save(atomicStockPortfolio);
	}

	@GetMapping("/{id}")
	public AtomicStockPortfolio fetchStock(@PathVariable Long id) {
		return AtomicStockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
}
