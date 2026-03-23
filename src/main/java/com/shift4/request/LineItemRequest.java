package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a line item request.
 */
@JsonInclude(Include.NON_NULL)
public class LineItemRequest {

    private CheckoutProductRequest product;
    private Integer quantity;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public LineItemRequest() {
    }

    public LineItemRequest(CheckoutProductRequest product) {
        this.product = product;
    }

    public LineItemRequest(CheckoutProductRequest product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CheckoutProductRequest getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LineItemRequest product(CheckoutProductRequest product) {
        this.product = product;
        return this;
    }

    public LineItemRequest quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @JsonAnyGetter
    private Map<String, Object> getOtherMap() {
        return other;
    }

    @JsonAnySetter
    public LineItemRequest set(String name, Object value) {
        other.put(name, value);
        return this;
    }
}
