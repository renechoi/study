package com.example.concurrencystudy1.facade;

import org.springframework.stereotype.Service;

import com.example.concurrencystudy1.service.OptimisticLockStockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

	private final OptimisticLockStockService optimisticLockStockService;

	public void decrease(Long id, Long quantity) throws InterruptedException {
		while (true) {
			try {
				optimisticLockStockService.decrease(id, quantity);
				break;
			} catch (Exception e) {
				Thread.sleep(50);
			}
		}
	}
}