package org.example.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CarMain {
	public static void main(String[] args) {
		Map<String, BiFunction<String, String, Car>> carTypeToConstructorMap = new HashMap<>();
		carTypeToConstructorMap.put("sedan", Sedan::new);
		carTypeToConstructorMap.put("van", Sedan::new);
		carTypeToConstructorMap.put("suv", Sedan::new);

		String[][] inputs = new String[][] {
			{"sedan", "Sonata", "Hyundai"},
			{"van", "Sienna", "Toyota"},
			{"sedan", "Model S", "Tesla"},
			{"suv", "Sorento", "Kia"}
		};

		List<Car> cars = Arrays.stream(inputs)
			.map(input -> {
				String carType = input[0];
				String name = input[1];
				String brand = input[2];
				return carTypeToConstructorMap.get(carType).apply(name, brand);
			})
			.toList();
	}
}
