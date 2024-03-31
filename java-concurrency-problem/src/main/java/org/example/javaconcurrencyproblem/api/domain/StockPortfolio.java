package org.example.javaconcurrencyproblem.api.domain;

import static java.time.LocalDateTime.*;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
public class StockPortfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String portfolioId;

	@Column(nullable = false)
	private String owner;

	@Column(nullable = true)
	private long aaplStockAmount;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime modifiedAt;

	public void addStocks(long amount) {
		this.aaplStockAmount += amount;
	}

	public void subtractStocks(long amount) {
		this.aaplStockAmount -= amount;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = now();
		modifiedAt = createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		modifiedAt = now();
	}

	/**
	 * 마지막 수정 시간이 주어진 thresholdMillis 이내인지 확인하여 동시성 문제를 시뮬레이션합니다.
	 */
	public boolean isRequestDelayBelowMs(long thresholdMillis) {
		return this.getModifiedAt().plusNanos(thresholdMillis * 1_000_000).isAfter(now());
	}
}