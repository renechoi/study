package com.example.concurrencystudy2.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponCreateProducer {
	private final KafkaTemplate<String, Long> kafkaTemplate;

	public void create(Long userId) {
		kafkaTemplate.send("coupon create", userId);
	}
}
