package com.example.concurrencystudy1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrencystudy1.domain.Stock;
import com.example.concurrencystudy1.repository.StockRepository;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
	private final StockRepository stockRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public synchronized void decrease(Long id, Long quantity){
		Stock stock = stockRepository.findById(id).orElseThrow();
		stock.decrease(quantity);
		stockRepository.save(stock);
	}
}
