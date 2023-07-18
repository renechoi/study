package com.example.concurrencystudy2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	public Coupon() {
	}

	public Coupon(Long id, Long userId) {
		this.id = id;
		this.userId = userId;
	}

	public Coupon(Long userId) {
		this.userId = userId;
	}
}
