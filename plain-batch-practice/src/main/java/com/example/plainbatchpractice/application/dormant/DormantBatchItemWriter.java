package com.example.plainbatchpractice.application.dormant;

import org.springframework.stereotype.Component;

import com.example.plainbatchpractice.EmailProvider;
import com.example.plainbatchpractice.batch.ItemWriter;
import com.example.plainbatchpractice.customer.Customer;
import com.example.plainbatchpractice.customer.CustomerRepository;

@Component
public class DormantBatchItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchItemWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void write(Customer item) {
        customerRepository.save(item);
        emailProvider.send(item.getEmail(), "휴먼전환 안내메일입니다.", "내용");
    }

}
