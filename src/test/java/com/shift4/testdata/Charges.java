package com.shift4.testdata;


import com.shift4.request.ChargeRequest;

public abstract class Charges {

    private Charges() {
    }

    public static ChargeRequest charge() {
        return new ChargeRequest(100_00, "USD");
    }
}
