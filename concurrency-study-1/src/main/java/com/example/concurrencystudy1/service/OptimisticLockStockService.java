package com.example.concurrencystudy1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrencystudy1.domain.Stock;
import com.example.concurrencystudy1.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptimisticLockStockService {

	private final StockRepository stockRepository;

	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithOptimisticLock(id);
		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}