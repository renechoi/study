package org.example.desiginpattern.builder;

import org.example.desiginpattern.domain.Price;

public class BasicPriceProcessor implements PriceProcessor {

	@Override
	public Price process(Price price) {
		return price;
	}

}