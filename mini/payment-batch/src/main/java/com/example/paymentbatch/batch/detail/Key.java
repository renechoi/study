package com.example.paymentbatch.batch.detail;

import java.io.Serializable;

record Key(Long customerId, Long serviceId) implements Serializable {
}
