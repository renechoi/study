package com.example.concurrencystudy2.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.concurrencystudy2.domain.Coupon;
import com.example.concurrencystudy2.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {

	private final CouponRepository couponRepository;

	@KafkaListener(topics = "coupon create", groupId = "group_1")
	public void listener(Long userId){
		couponRepository.save(new Coupon(userId));
	}
}
