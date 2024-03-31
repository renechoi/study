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
 * <p>
 * 동시성 제어를 위한 SynchronizedStockTradingController.
 * </p>
 * 별도의 동기화 객체를 사용합니다.
 * <p>
 * 이 방식은 다음과 같은 이점을 제공합니다:
 * <ul>
 *     <li><strong>세밀한 제어:</strong> 동기화의 범위와 세밀함을 더 잘 제어할 수 있으며, 특정 작업에만 동기화를 적용하고,
 *     다른 작업은 동시에 실행될 수 있도록 할 수 있습니다.</li>
 *     <li><strong>동일 락 공유 가능:</strong> 서로 다른 메서드나 클래스에서 동일한 동기화 객체를 공유함으로써,
 *     보다 유연하게 동시성 제어를 수행할 수 있습니다. 이는 여러 컴포넌트 간의 동기화가 필요할 때 유용합니다.</li>
 *     <li><strong>성능 최적화:</strong> 필요한 부분에만 동기화를 적용함으로써, 시스템의 전반적인 성능을 향상시킬 수 있습니다.
 *     불필요한 동기화로 인한 성능 저하를 방지할 수 있습니다.</li>
 * </ul>
 * 본 클래스에서는 매수, 매도, 조회 기능을 제공하며, 이러한 작업들은 단일 객체 락을 사용하여 동기화됩니다.
 * </p>
 *
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@RestController
@RequestMapping("/stock-portfolio/synchronized")
@RequiredArgsConstructor
public class SynchronizedStockTradingController {

	private final StockPortfolioRepositoryAdapter stockPortfolioRepository;

	private final Object lock = new Object();

	@PostMapping("/{id}/buy")
	public StockPortfolio buyStock(@PathVariable Long id, @RequestBody BuyOrderRequest request) {
		synchronized (lock) {
			StockPortfolio stockPortfolio = stockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
			if (stockPortfolio.isRequestDelayBelowMs(1000)) {
				throw new IllegalStateException("매수 요청이 너무 자주 발생했습니다. 나중에 다시 시도해 주세요.");
			}
			stockPortfolio.addStocks(request.getAaplStockAmount());
			return stockPortfolioRepository.save(stockPortfolio);
		}
	}

	@PostMapping("/{id}/sell")
	public StockPortfolio sellStock(@PathVariable Long id, @RequestBody SellOrderRequest request) {
		synchronized (lock) {
			Long amount = request.getAaplStockAmount();
			StockPortfolio portfolio = stockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
			if (portfolio.getAaplStockAmount() < amount) {
				throw new RuntimeException("매도 가능한 주식이 없습니다.");
			}
			portfolio.subtractStocks(amount);
			return stockPortfolioRepository.save(portfolio);
		}
	}

	@GetMapping("/{id}")
	public StockPortfolio fetchStock(@PathVariable Long id) {
		synchronized (lock) {
			return stockPortfolioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		}
	}
}
