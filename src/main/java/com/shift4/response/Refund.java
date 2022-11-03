package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.RefundReason;
import com.shift4.enums.RefundStatus;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class Refund {

    private String id;
    private Long created;
    private Integer amount;
    private String currency;
    private String charge;
    private String reason;
    private String status;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public String getId() {
        return id;
    }

    public Long getCreated() {
        return created;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCharge() {
        return charge;
    }

    public RefundReason getReason() {
        return RefundReason.fromValue(reason);
    }

    public String getReasonAsString() {
        return reason;
    }

    public RefundStatus getStatus() {
        return RefundStatus.fromValue(status);
    }

    public String getStatusAsString() {
        return status;
    }

    public String get(String name) {
        return Shift4Utils.toStringNullSafe(other.get(name));
    }

    @JsonAnySetter
    private void set(String name, Object value) {
        other.put(name, value);
    }
}
