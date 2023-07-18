package com.example.concurrencystudy2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concurrencystudy2.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
