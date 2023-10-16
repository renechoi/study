package com.example.plainbatchpractice.application.dormant;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import com.example.plainbatchpractice.batch.ItemProcessor;
import com.example.plainbatchpractice.customer.Customer;

@Component
public class DormantBatchItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer item) {
        final boolean isDormantTarget = LocalDate.now()
                .minusDays(365)
                .isAfter(item.getLoginAt().toLocalDate());

        if (isDormantTarget) {
            item.setStatus(Customer.Status.DORMANT);
            return item;
        } else {
            return null;
        }

    }
}
