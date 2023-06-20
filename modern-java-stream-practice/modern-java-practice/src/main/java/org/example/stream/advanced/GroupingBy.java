package org.example.stream.advanced;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.example.stream.Order;
import org.example.stream.Order.OrderStatus;

public class GroupingBy {
	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(12, 2, 101, 203, 304, 402, 305, 349, 2313, 203);
		// 1의 자리로 묶어서 같은 1의 자리를 갖는 값으로 묶어보자

		Map<Integer, List<Integer>> unitDigitMap = integers.stream().collect(Collectors.groupingBy(number -> number % 10));
		// System.out.println(unitDigitMap);

		Map<Integer, Set<Integer>> collect = integers.stream()
			.collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));

		Map<Integer, List<String>> integerListMap = integers.stream()
			.collect(Collectors.groupingBy(number -> number % 10,
				Collectors.mapping(number -> " unit digit is " + number, Collectors.toList())));
		// System.out.println(integerListMap);


		Order order1 = new Order()
			.setId(1001L)
			.setAmount(BigDecimal.valueOf(2000))
			.setStatus(OrderStatus.CREATED);
		Order order2 = new Order()
			.setId(1002L)
			.setAmount(BigDecimal.valueOf(4000))
			.setStatus(OrderStatus.ERROR);
		Order order3 = new Order()
			.setId(1003L)
			.setAmount(BigDecimal.valueOf(3000))
			.setStatus(OrderStatus.ERROR);
		Order order4 = new Order()
			.setId(1004L)
			.setAmount(BigDecimal.valueOf(7000))
			.setStatus(OrderStatus.PROCESSED);
		List<Order> orders = Arrays.asList(order1, order2, order3, order4);

		// orderStatus 별로 order를 묶어보자. => 같은 orderStatus를 묶어서 map을 만들기

		Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
			.collect(Collectors.groupingBy(Order::getStatus,
				Collectors.mapping(Order::getAmount,
					Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
		System.out.println(orderStatusToSumOfAmountMap);
	}


}
