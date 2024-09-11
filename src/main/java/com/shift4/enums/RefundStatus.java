package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RefundStatus {

    FAILED("failed"),
    PENDING("pending"),
    SUCCESSFUL("successful"),

    /**
     * Used when received value can't be mapped to this enumeration.
     */
    UNRECOGNIZED("unrecognized");

    private final String value;

    RefundStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static RefundStatus fromValue(@JsonProperty("value") String value) {
        if (value == null) {
            return null;
        }
        for (RefundStatus refundStatus : values()) {
            if (refundStatus.value.equalsIgnoreCase(value)) {
                return refundStatus;
            }
        }

        return UNRECOGNIZED;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
