package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class RefundUpdateRequest {
    @JsonIgnore
    private String refundId;
    private Map<String, String> metadata;

    public String getRefundId() {
        return refundId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public RefundUpdateRequest refundId(String refundId) {
        this.refundId = refundId;
        return this;
    }

    public RefundUpdateRequest metadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }
}
