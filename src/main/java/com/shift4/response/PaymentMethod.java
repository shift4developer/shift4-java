package com.shift4.response;

import com.shift4.enums.PaymentMethodStatus;
import com.shift4.enums.PaymentMethodType;

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
}
