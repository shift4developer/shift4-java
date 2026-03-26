package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class CheckoutStaticFieldRequest {

    private String key;
    private String value;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public CheckoutStaticFieldRequest() {
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public CheckoutStaticFieldRequest key(String key) {
        this.key = key;
        return this;
    }

    public CheckoutStaticFieldRequest value(String value) {
        this.value = value;
        return this;
    }

    @JsonAnyGetter
    private Map<String, Object> getOtherMap() {
        return other;
    }

    @JsonAnySetter
    public CheckoutStaticFieldRequest set(String name, Object value) {
        other.put(name, value);
        return this;
    }
}
