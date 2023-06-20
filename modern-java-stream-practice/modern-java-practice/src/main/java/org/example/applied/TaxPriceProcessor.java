package org.example.applied;

import java.math.BigDecimal;
import java.util.function.Function;

import org.example.stream.Order;


public class TaxPriceProcessor implements Function<Order, Order>{

	private final BigDecimal taxRate;
	
	public TaxPriceProcessor(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}


	@Override
	public Order apply(Order order) {
		return order.setAmount(order.getAmount()
				.multiply(taxRate.divide(new BigDecimal(100)).add(BigDecimal.ONE))); 	// 세율을 적용해준다.
	}

}