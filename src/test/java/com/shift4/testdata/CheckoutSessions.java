package com.shift4.testdata;


import com.shift4.request.Amount;
import com.shift4.request.CheckoutCustomFieldRequest;
import com.shift4.request.CheckoutSessionRequest;
import com.shift4.request.CheckoutStaticFieldRequest;
import com.shift4.request.LineItemRequest;
import com.shift4.request.ProductRequest;

import java.util.Collections;
import java.util.List;

public abstract class CheckoutSessions {

    private CheckoutSessions() {
    }

    public static CheckoutSessionRequest checkoutSession() {
        return new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(lineItem(simpleProduct())));
    }

    public static LineItemRequest lineItem(ProductRequest product) {
        return new LineItemRequest(product, 1);
    }

    public static ProductRequest simpleProduct() {
        return new ProductRequest()
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

    public static CheckoutStaticFieldRequest staticField() {
        return new CheckoutStaticFieldRequest()
                .key("order_id")
                .value("ORD-12345");
    }

    public static CheckoutStaticFieldRequest staticField(String key, String value) {
        return new CheckoutStaticFieldRequest()
                .key(key)
                .value(value);
    }

    public static ProductRequest subscriptionProduct(String planId) {
        return new ProductRequest(planId);
    }

    public static ProductRequest donationProductWithOptions(List<Integer> options) {
        return new ProductRequest()
                .name("Donation")
                .amount(new Amount().options(options))
                .currency("USD");
    }

    public static ProductRequest donationProductWithCustomRange(Integer min, Integer max) {
        return new ProductRequest()
                .name("Donation")
                .amount(new Amount().custom(min, max))
                .currency("USD");
    }
}
