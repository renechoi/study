package com.example.concurrencystudy2.service;

import org.springframework.stereotype.Service;

import com.example.concurrencystudy2.producer.CouponCreateProducer;
import com.example.concurrencystudy2.repository.AppliedUserRepository;
import com.example.concurrencystudy2.repository.CouponCountRepository;
import com.example.concurrencystudy2.repository.CouponRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApplyService {

	private final CouponRepository couponRepository;

	private final CouponCountRepository couponCountRepository;

	private final CouponCreateProducer couponCreateProducer;

	private final AppliedUserRepository appliedUserRepository;

	public void apply(Long userId) {

		Long appliedUser = appliedUserRepository.add(userId);

		if (appliedUser != 1){
			return;
		}

		// long count = couponRepository.count();
		Long count = couponCountRepository.increment();
		log.info("쿠폰 개수: {}", count);

		if (count > 100) {
			return;
		}

		// couponRepository.save(new Coupon(userId)); // 직접 쿠폰을 생성하는 로직을 삭제

		couponCreateProducer.create(userId);
	}
}
