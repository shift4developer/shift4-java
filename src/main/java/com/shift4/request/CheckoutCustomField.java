package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.shift4.enums.CustomFieldPlacement;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a custom field for checkout sessions.
 * <p>
 * Note: Maximum 3 custom fields per checkout session. Label length is limited to 20 characters.
 */
@JsonInclude(Include.NON_NULL)
public class CheckoutCustomField {

    private String key;
    private String label;
    private Map<String, String> labelTranslations;
    private Boolean optional;
    private CustomFieldPlacement placement;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public CheckoutCustomField() {
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public Map<String, String> getLabelTranslations() {
        return labelTranslations;
    }

    public Boolean getOptional() {
        return optional;
    }

    public CustomFieldPlacement getPlacement() {
        return placement;
    }

    public CheckoutCustomField key(String key) {
        this.key = key;
        return this;
    }

    public CheckoutCustomField label(String label) {
        this.label = label;
        return this;
    }

    public CheckoutCustomField labelTranslations(Map<String, String> labelTranslations) {
        this.labelTranslations = labelTranslations;
        return this;
    }

    public CheckoutCustomField optional(Boolean optional) {
        this.optional = optional;
        return this;
    }

    public CheckoutCustomField placement(CustomFieldPlacement placement) {
        this.placement = placement;
        return this;
    }

    @JsonAnyGetter
    private Map<String, Object> getOtherMap() {
        return other;
    }

    @JsonAnySetter
    public CheckoutCustomField set(String name, Object value) {
        other.put(name, value);
        return this;
    }
}
