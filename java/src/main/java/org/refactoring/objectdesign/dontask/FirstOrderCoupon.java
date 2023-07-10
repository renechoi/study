package org.refactoring.objectdesign.dontask;

import java.time.LocalDate;

public class FirstOrderCoupon {

	/**
	 * 좋은 패턴
	 * 묻지 말고 시켜라. 쿠폰 객체의 apply() 메서드를 통해서 묻지 말고 쿠폰을 적용하고 있습니다.
	 */
	public void apply(final long couponId) {
		if (canIssued()) {
			final Coupon coupon = getCoupon(couponId);
			coupon.apply();
		}
	}

	// 실제는 데이터베이스 조회..
	private Coupon getCoupon(final Long id) {
		return new Coupon(1000, LocalDate.now().plusDays(3));
	}

	private boolean canIssued() {
		return true;
	}
}