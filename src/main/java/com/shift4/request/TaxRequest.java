package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a reference to an existing tax.
 */
@JsonInclude(Include.NON_NULL)
public class TaxRequest {

    private String id;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public TaxRequest() {
    }

    public TaxRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public TaxRequest id(String id) {
        this.id = id;
        return this;
    }

    @JsonAnyGetter
    private Map<String, Object> getOtherMap() {
        return other;
    }

    @JsonAnySetter
    public TaxRequest set(String name, Object value) {
        other.put(name, value);
        return this;
    }
}
