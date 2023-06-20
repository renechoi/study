package org.example.applied;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.example.stream.Order;
import org.example.stream.OrderLine;

public class ComposeAndAndThen {
	public static void main(String[] args) {
		Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
		Function<Integer, Integer> addTen = x -> x + 10;

		Function<Integer, Integer> integerIntegerFunction = multiplyByTwo.andThen(addTen);
		System.out.println(integerIntegerFunction.apply(3));

		Order unprocessedOrder = new Order()
			.setId(1001L)
			.setOrderLines(Arrays.asList(
				new OrderLine().setAmount(BigDecimal.valueOf(1000)),
				new OrderLine().setAmount(BigDecimal.valueOf(2000))));

		List<Function<Order, Order>> priceProcessors = getPriceProcessors(unprocessedOrder);

		Function<Order, Order> mergedPriceProcessors = priceProcessors.stream()
			.reduce(Function.identity(), Function::andThen); // 초기값으로는 Order를 받아 그냥 Order를 넘기는 identity, 그리고 함수 두 개를 이어준다.

		Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);  // 최종 합성된 processor가 가격을 처리한다.
		System.out.println(processedOrder);

	}

	public static List<Function<Order, Order>> getPriceProcessors(Order order) {
		return Arrays.asList(new OrderLineAggregationPriceProcessor(),
			new TaxPriceProcessor(new BigDecimal("9.375")));
	}
}
