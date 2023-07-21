package com.example.concurrencystudy2.service;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.concurrencystudy2.repository.CouponRepository;

@SpringBootTest
class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;

	@Autowired
	private CouponRepository couponRepository;

	@Test
	public void applyOnce() {
		applyService.apply(1L);

		long count = couponRepository.count();
		assertThat(count).isEqualTo(1);
	}

	@Test
	public void applyMulti() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					applyService.apply(userId);
				} finally {
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();

		Thread.sleep(10000);

		long count = couponRepository.count();

		assertThat(count).isEqualTo(100);
	}

	@Test
	public void applyMultiWithUniqueUserPerOne() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					applyService.apply(1L);
				} finally {
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();

		Thread.sleep(10000);

		long count = couponRepository.count();

		assertThat(count).isEqualTo(1);
	}

}