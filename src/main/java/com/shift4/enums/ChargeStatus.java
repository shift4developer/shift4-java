package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChargeStatus {

    SUCCESSFUL("successful"),
    PENDING("pending"),
    FAILED("failed"),

    /**
     * Used when received value can't be mapped to this enumeration.
     */
    UNRECOGNIZED("unrecognized"),
    ;

    private final String value;

    ChargeStatus(String value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ChargeStatus fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (ChargeStatus type : values()) {
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
