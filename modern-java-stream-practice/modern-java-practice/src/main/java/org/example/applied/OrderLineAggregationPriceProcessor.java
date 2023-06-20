package org.example.applied;

import java.math.BigDecimal;
import java.util.function.Function;

import org.example.stream.Order;
import org.example.stream.OrderLine;

public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {

	@Override
	public Order apply(Order order) {
		return order.setAmount(order.getOrderLines().stream()
				.map(OrderLine::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add));
	}

}