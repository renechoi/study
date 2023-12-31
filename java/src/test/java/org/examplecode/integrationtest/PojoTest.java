package org.examplecode.integrationtest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PojoTest {

	@Test
	public void 쿠폰생성() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, 10);

		then(coupon.isUsed()).isFalse();
		then(coupon.getAmount()).isEqualTo(amount);
		then(coupon.isExpiration()).isFalse();
	}

	@Test
	public void 쿠폰할인적용() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, 10);

		coupon.apply();
		then(coupon.isUsed()).isTrue();
	}

	@Test
	public void 쿠폰할인적용시_이미사용했을경우() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, 10);

		// 쿠폰생성시 쿠폰 사용 여부를 생성할 수 없어 apply() 두번 호출
		coupon.apply();

		thenThrownBy(() -> coupon.apply())
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	public void 쿠폰할인적용시_쿠폰기간만료했을경우() {
		final double amount = 10D;
		final Coupon coupon = buildCoupon(amount, -10);

		// 쿠폰생성시 쿠폰 사용 여부를 생성할 수 없어 apply() 두번 호출
		thenThrownBy(() -> coupon.apply())
			.isInstanceOf(IllegalStateException.class);
	}

	private Coupon buildCoupon(double amount, int daysToAdd) {
		return new Coupon(amount, LocalDate.now().plusDays(daysToAdd));
	}

	static class Coupon {

		public Coupon(double amount, LocalDate plusDays) {

		}

		public void apply() {

		}

		public boolean isUsed() {
			return false;
		}

		public boolean getAmount() {
			return false;
		}

		public boolean isExpiration() {
			return false;
		}
	}
}