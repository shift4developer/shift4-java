package com.shift4.testdata;


import com.shift4.request.Amount;
import com.shift4.request.CheckoutCustomFieldRequest;
import com.shift4.request.CheckoutProductRequest;
import com.shift4.request.CheckoutSessionRequest;
import com.shift4.request.LineItemRequest;

import java.util.Collections;
import java.util.List;

public abstract class CheckoutSessions {

    private CheckoutSessions() {
    }

    public static CheckoutSessionRequest checkoutSession() {
        return new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(lineItem(simpleProduct())));
    }

    public static LineItemRequest lineItem(CheckoutProductRequest product) {
        return new LineItemRequest(product, 1);
    }

    public static CheckoutProductRequest simpleProduct() {
        return new CheckoutProductRequest()
                .name("Test Product")
                .amount(1000)
                .currency("USD");
    }

    public static CheckoutCustomFieldRequest customField() {
        return new CheckoutCustomFieldRequest()
                .key("company")
                .label("Company")
                .optional(false);
    }

    public static CheckoutProductRequest subscriptionProduct(String planId) {
        return new CheckoutProductRequest(planId);
    }

    public static CheckoutProductRequest donationProductWithOptions(List<Integer> options) {
        return new CheckoutProductRequest()
                .name("Donation")
                .amount(new Amount().options(options))
                .currency("USD");
    }

    public static CheckoutProductRequest donationProductWithCustomRange(Integer min, Integer max) {
        return new CheckoutProductRequest()
                .name("Donation")
                .amount(new Amount().custom(min, max))
                .currency("USD");
    }
}
