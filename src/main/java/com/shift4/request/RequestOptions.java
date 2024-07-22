package com.shift4.request;

public class RequestOptions {
    private String idempotencyKey;

    public static RequestOptions withIdempotencyKey(String idempotencyKey) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.idempotencyKey = idempotencyKey;
        return requestOptions;
    }

    public RequestOptions() {
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public RequestOptions idempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        return this;
    }
}