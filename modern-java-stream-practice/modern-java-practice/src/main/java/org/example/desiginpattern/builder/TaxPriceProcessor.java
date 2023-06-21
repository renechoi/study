package org.example.desiginpattern.builder;

import org.example.desiginpattern.domain.Price;

public class TaxPriceProcessor implements PriceProcessor {

	@Override
	public Price process(Price price) {
		return new Price(price.getPrice() + ", then applied tax");
	}

}