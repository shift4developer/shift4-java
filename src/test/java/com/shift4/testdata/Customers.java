package com.shift4.testdata;


import com.shift4.request.CardRequest;
import com.shift4.request.CustomerRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public abstract class Customers {

    private Customers() {
    }

    public static CustomerRequest customer(CardRequest card) {
        return new CustomerRequest()
                .email("testEmail+shift4-java." + Instant.now().toEpochMilli() + RandomStringUtils.randomAlphanumeric(10) + "@dev.shift4.com")
                .card(card);
    }
}
