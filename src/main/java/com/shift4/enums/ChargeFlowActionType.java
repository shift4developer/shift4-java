package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChargeFlowActionType {
    APP_REDIRECT("app_redirect"),
    QR_CODE("qr_code"),
    MOBILE_APP_CONFIRMATION("mobile_app_confirmation"),
    REDIRECT("redirect"),
    WAIT("wait"),
    NONE("none"),
    UNRECOGNIZED("unrecognized");

    private final String value;

    ChargeFlowActionType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ChargeFlowActionType fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (ChargeFlowActionType type : values()) {
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
