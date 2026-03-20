package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a product reference for checkout session line items.
 * <p>
 * This is a flexible product type that can represent:
 * <ul>
 *   <li>Existing product by ID: set only the id field (e.g., "product_xxx")</li>
 *   <li>Existing plan by ID: set only the id field (e.g., "plan_xxx")</li>
 *   <li>Inline product: set name, amount, currency (and optionally description, taxes)</li>
 * </ul>
 */
@JsonInclude(Include.NON_NULL)
public class CheckoutProductRequest {

    private String id;
    private String name;
    private String description;
    private Amount amount;
    private String currency;
    private List<TaxRequest> taxes;
    private String merchantAccountId;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public CheckoutProductRequest() {
    }

    public CheckoutProductRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public List<TaxRequest> getTaxes() {
        return taxes;
    }

    public String getMerchantAccountId() {
        return merchantAccountId;
    }

    public CheckoutProductRequest id(String id) {
        this.id = id;
        return this;
    }

    public CheckoutProductRequest name(String name) {
        this.name = name;
        return this;
    }

    public CheckoutProductRequest description(String description) {
        this.description = description;
        return this;
    }

    public CheckoutProductRequest amount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public CheckoutProductRequest amount(Integer value) {
        this.amount = new Amount(value);
        return this;
    }

    public CheckoutProductRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public CheckoutProductRequest taxes(List<TaxRequest> taxes) {
        this.taxes = taxes;
        return this;
    }

    public CheckoutProductRequest merchantAccountId(String merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
        return this;
    }

    @JsonAnyGetter
    private Map<String, Object> getOtherMap() {
        return other;
    }

    @JsonAnySetter
    public CheckoutProductRequest set(String name, Object value) {
        other.put(name, value);
        return this;
    }
}
