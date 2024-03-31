package org.example.javaconcurrencyproblem.api.domain;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtomicStockPortfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String portfolioId;

	private String owner;


	/**
	 * AtomicLong으로 대체하여 원자적 연산을 보장
	 * AtomicLong 타입의 필드는 객체 생성 시 명시적으로 초기화되어야 합니다.
	 * 그렇지 않으면 해당 필드는 null 값을 가지게 되어, 해당 필드에 대한 어떤 연산을 시도할 때 NullPointerException이 발생합니다.
	 * -> 초기값 0으로 설정
	 */
	@Builder.Default
	private volatile AtomicLong aaplStockAmount = new AtomicLong(0);

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	public void addStocks(long amount) {
		this.aaplStockAmount.updateAndGet(current -> current + amount);
		this.modifiedAt = LocalDateTime.now();
	}

	public void subtractStocks(long amount) {
		this.aaplStockAmount.updateAndGet(current -> {
			if (current < amount) {
				throw new RuntimeException("매도 가능한 주식이 없습니다.");
			}
			return current - amount;
		});
		this.modifiedAt = LocalDateTime.now();
	}

	public long getAaplStockAmount() {
		return this.aaplStockAmount.get();
	}

	public boolean isRequestDelayBelowMs(long thresholdMillis) {
		return this.getModifiedAt().plusNanos(thresholdMillis * 1_000_000).isAfter(LocalDateTime.now());
	}

	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
		modifiedAt = createdAt;
	}

	@PreUpdate
	private void onUpdate() {
		modifiedAt = LocalDateTime.now();
	}

}
