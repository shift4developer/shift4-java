package com.shift4.request;

public class RequestOptions {
    private String idempotencyKey;
    private String shift4Version;

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

    public RequestOptions shift4Version(String version) {
        this.shift4Version = version;
        return this;
    }

    public String getShift4Version() {
        return shift4Version;
    }
}