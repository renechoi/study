package org.example.javaconcurrencyproblem.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BuyOrderRequest {
	private Long aaplStockAmount;
}
