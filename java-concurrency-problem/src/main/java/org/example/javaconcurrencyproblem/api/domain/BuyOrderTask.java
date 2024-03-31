package org.example.javaconcurrencyproblem.api.domain;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : Rene Choi
 * @since : 2024/03/22
 */
@AllArgsConstructor
@Getter
public class BuyOrderTask {
	private final Long portfolioId;
	private final Long aaplStockAmount;
	private final LocalDateTime timestamp;
	private final CompletableFuture<ResponseEntity<?>> futureResult;
}
