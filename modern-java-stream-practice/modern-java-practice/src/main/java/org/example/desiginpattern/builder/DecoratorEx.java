package org.example.desiginpattern.builder;

import org.example.desiginpattern.domain.Price;

public class DecoratorEx {
	public static void main(String[] args) {
		Price originalPrice = new Price("Original Price");

		BasicPriceProcessor basicPriceProcessor = new BasicPriceProcessor();
		DiscountPriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
		TaxPriceProcessor taxPriceProcessor = new TaxPriceProcessor();

		PriceProcessor decoratedPriceProcessor = basicPriceProcessor.andThen(discountPriceProcessor);
		Price processedPrice = decoratedPriceProcessor.process(originalPrice);
		System.out.println(processedPrice.getPrice());

		PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor.andThen(discountPriceProcessor).andThen(taxPriceProcessor);


		PriceProcessor decoratedPriceProcessor3 = basicPriceProcessor
			.andThen(price -> new Price(price.getPrice() + " apply another process"));


	}
}
