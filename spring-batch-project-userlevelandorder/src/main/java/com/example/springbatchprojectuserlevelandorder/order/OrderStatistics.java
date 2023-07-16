package com.example.springbatchprojectuserlevelandorder.order;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 합산된 금액을 담을 수 있는 클래스
 */
@Getter
public class OrderStatistics {

    private String amount;

    private LocalDate date;

    @Builder
    private OrderStatistics(String amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }
}