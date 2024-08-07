package com.shift4;

import com.shift4.request.RequestOptions;
import com.shift4.util.Base64;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

class Shift4GatewayHeadersFactory {

    Map<String, String> create(String secretKey) {
        return create(secretKey, null);
    }

    Map<String, String> create(String secretKey, RequestOptions requestOptions) {
        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization", "Basic " + Base64.encode((secretKey + ":").getBytes()));
        headers.put("Content-Type", "application/json");
        headers.put("User-Agent", "Shift4-Java/" + Shift4Utils.getBuildVersion()
                + " (Java/" + Shift4Utils.getJavaVersion() + ")");

        if (requestOptions != null && requestOptions.getIdempotencyKey() != null) {
            headers.put("Idempotency-Key", requestOptions.getIdempotencyKey());
        }

        return headers;
    }
}
