package com.shift4.testdata;


import com.shift4.request.CardRequest;

public abstract class Cards {

    private Cards() {
    }

    public static CardRequest successCard() {
        return new CardRequest()
                .number("4242424242424242")
                .expYear(Dates.getNextYearString())
                .expMonth("12")
                .cvc("123");
    }

    public static CardRequest incorrectCvcCard() {
        return new CardRequest()
                .number("4024007134364842")
                .expYear(Dates.getNextYearString())
                .expMonth("12")
                .cvc("123");
    }

}
