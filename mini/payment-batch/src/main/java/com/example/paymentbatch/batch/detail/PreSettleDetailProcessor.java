package com.example.paymentbatch.batch.detail;

import org.springframework.batch.item.ItemProcessor;

import com.example.paymentbatch.domain.ApiOrder;
import com.example.paymentbatch.domain.ServicePolicy;

public class PreSettleDetailProcessor implements ItemProcessor<ApiOrder, Key> {

    @Override
    public Key process(ApiOrder item) throws Exception {
        if(item.getState() == ApiOrder.State.FAIL)
            return null;

        final Long serviceId = ServicePolicy.findByUrl(item.getUrl())
                .getId();

        return new Key(
                item.getCustomerId(),
                serviceId
        );
    }

}
