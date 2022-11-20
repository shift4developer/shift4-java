package com.shift4.testdata;


import com.shift4.request.CardRequest;
import com.shift4.request.CustomerRequest;

public abstract class Customers {

    private Customers() {
    }

    public static CustomerRequest customer() {
        return new CustomerRequest()
                .email(Emails.email());
    }

    public static CustomerRequest customer(CardRequest card) {
        return customer()
                .card(card);
    }
}
