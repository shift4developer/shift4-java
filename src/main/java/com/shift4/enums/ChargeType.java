package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChargeType {
    FIRST_RECURRING("first_recurring"),
    SUBSEQUENT_RECURRING("subsequent_recurring"),
    MERCHANT_INITIATED("merchant_initiated"),
    CUSTOMER_INITIATED("customer_initiated"),

    /**
     * Used when received value can't be mapped to this enumeration.
     */
    UNRECOGNIZED("unrecognized"),
    ;

    private final String value;

    ChargeType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ChargeType fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (ChargeType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }

        return UNRECOGNIZED;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}