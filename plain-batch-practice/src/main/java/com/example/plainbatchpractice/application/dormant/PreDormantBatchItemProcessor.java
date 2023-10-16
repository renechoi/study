package com.example.plainbatchpractice.application.dormant;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import com.example.plainbatchpractice.batch.ItemProcessor;
import com.example.plainbatchpractice.customer.Customer;

@Component
public class PreDormantBatchItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) {

        final LocalDate targetDate = LocalDate.now()
                .minusDays(365)
                .plusDays(7);

        if (targetDate.equals(customer.getLoginAt().toLocalDate())) {
            return customer;
        } else {
            return null;
        }

    }

}
