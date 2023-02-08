package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.CardBrand;
import com.shift4.enums.CardType;
import com.shift4.enums.PaymentMethodStatus;
import com.shift4.enums.PaymentMethodType;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethod {
    private String id;
    private String customerId;
    private String clientObjectId;
    private Long created;
    private String type;
    private String status;
    private boolean deleted = false;
    private Billing billing;
    private FraudCheckData fraudCheckData;
    private ApplePay applePay;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getClientObjectId() {
        return clientObjectId;
    }

    public Long getCreated() {
        return created;
    }

    public PaymentMethodType getType() {
        return PaymentMethodType.fromValue(type);
    }

    public String getTypeStr() {
        return type;
    }

    public PaymentMethodStatus getStatus() {
        return PaymentMethodStatus.fromValue(status);
    }

    public String getStatusStr() {
        return status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Billing getBilling() {
        return billing;
    }

    public FraudCheckData getFraudCheckData() {
        return fraudCheckData;
    }

    public ApplePay getApplePay() {
        return applePay;
    }

    public String get(String name) {
        return Shift4Utils.toStringNullSafe(other.get(name));
    }

    @JsonAnySetter
    private void set(String name, Object value) {
        other.put(name, value);
    }

    public static class ApplePay {
        private String cardBrand;
        private String cardType;
        private String first6;
        private String last4;
        private Integer amount;
        private String currency;

        @JsonIgnore
        private final Map<String, Object> other = new HashMap<>();

        public CardBrand getCardBrand() {
            return CardBrand.fromValue(cardBrand);
        }

        public String getCardBrandAsString() {
            return cardBrand;
        }

        public CardType getCardType() {
            return CardType.fromValue(cardType);
        }

        public String getCardTypeAsString() {
            return cardType;
        }

        public String getFirst6() {
            return first6;
        }

        public String getLast4() {
            return last4;
        }

        public Integer getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public String get(String name) {
            return Shift4Utils.toStringNullSafe(other.get(name));
        }

        @JsonAnySetter
        private void set(String name, Object value) {
            other.put(name, value);
        }
    }
}
