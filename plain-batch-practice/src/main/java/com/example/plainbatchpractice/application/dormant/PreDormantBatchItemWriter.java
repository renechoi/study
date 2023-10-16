package com.example.plainbatchpractice.application.dormant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.plainbatchpractice.EmailProvider;
import com.example.plainbatchpractice.batch.ItemWriter;
import com.example.plainbatchpractice.customer.Customer;

@Component
public class PreDormantBatchItemWriter implements ItemWriter<Customer> {

    private final EmailProvider emailProvider;

    @Autowired
    public PreDormantBatchItemWriter() {
        this.emailProvider = new EmailProvider.Fake();
    }

    public PreDormantBatchItemWriter(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }

    @Override
    public void write(Customer customer) {
        emailProvider.send(
                customer.getEmail(),
                "곧 휴면계정으로 전환이 됩니다.",
                "휴면계정으로 사용되기를 원치 않으신다면 1주일 내에 로그인을 해주세요."
        );
    }

}
