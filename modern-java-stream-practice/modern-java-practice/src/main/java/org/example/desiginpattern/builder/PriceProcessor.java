package org.example.desiginpattern.builder;

import org.example.desiginpattern.domain.Price;

public interface PriceProcessor {
	Price process(Price price);

	// 자신 다음에 실행될 프로세서를 받아온다.
	// 호출시 새로운 프라이스 프로세서를 호출해주는데, Functional interface이므로 람다를 이용해 자신 먼저 작업을 하고
	// 그 다음에 next로 들어온 process를 호출해주는 새로운 priceprocess를 호출해서 리턴해준다.
	default PriceProcessor andThen(PriceProcessor next){
		return price -> next.process(process(price));
	}
}
