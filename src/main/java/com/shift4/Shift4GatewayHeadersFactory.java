package com.shift4;

import com.shift4.request.RequestOptions;
import com.shift4.util.Base64;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

class Shift4GatewayHeadersFactory {

    Map<String, String> create(String secretKey, String merchantId) {
        return create(secretKey, merchantId, null);
    }

    Map<String, String> create(String secretKey, String merchantId, RequestOptions requestOptions) {
        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization", "Basic " + Base64.encode((secretKey + ":").getBytes()));
        headers.put("Content-Type", "application/json");
        headers.put("User-Agent", "Shift4-Java/" + Shift4Utils.getBuildVersion()
                + " (Java/" + Shift4Utils.getJavaVersion() + ")");

        if (requestOptions != null) {
            if (requestOptions.getIdempotencyKey() != null) {
                headers.put("Idempotency-Key", requestOptions.getIdempotencyKey());
            }
            if (requestOptions.getShift4Version() != null) {
                headers.put("Shift4-Version", requestOptions.getShift4Version());
            }
        }

        if (merchantId != null) {
            headers.put("Shift4-Merchant", merchantId);
        }

        return headers;
    }
}
