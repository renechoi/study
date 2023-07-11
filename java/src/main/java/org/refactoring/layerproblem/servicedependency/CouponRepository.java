package org.refactoring.layerproblem.servicedependency;


import org.springframework.data.jpa.repository.JpaRepository;

interface CouponRepository extends JpaRepository<Coupon, Long> {

}