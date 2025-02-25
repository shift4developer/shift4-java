package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditSourceOfFunds {
    DEBIT_CARD("debit_card"),
    CREDIT_CARD("credit_card"),
    PREPAID_CARD("prepaid_card"),
    CASH("cash"),
    DEPOSIT_ACCOUNT("deposit_account"),
    CREDIT_ACCOUNT("credit_account"),
    MOBILE_MONEY_ACCOUNT("mobile_money_account"),

    /**
     * Used when received value can't be mapped to this enumeration.
     */
    UNKNOWN("unknown");

    private final String value;

    CreditSourceOfFunds(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CreditSourceOfFunds fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (CreditSourceOfFunds creditSourceOfFunds : values()) {
            if (creditSourceOfFunds.value.equalsIgnoreCase(value)) {
                return creditSourceOfFunds;
            }
        }

        return UNKNOWN;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
