package com.shift4;

import com.shift4.enums.PaymentMethodType;
import com.shift4.request.PaymentMethodRequest;
import com.shift4.response.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodIT extends AbstractShift4GatewayTest{

    @Test
    void shouldCreatePaymentMethodFromToken() {
        // given
        Map<String, String> testToken = singletonMap("data", "TEST_TOKEN:50EUR");
        // when
        PaymentMethod paymentMethod = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.APPLE_PAY)
                .applePay(new PaymentMethodRequest.ApplePay(testToken)));
        // then
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getId()).isNotNull();
        PaymentMethod.ApplePay applePay = paymentMethod.getApplePay();
        assertThat(applePay).isNotNull();
        assertThat(applePay.getAmount()).isEqualTo(50);
        assertThat(applePay.getCurrency()).isEqualTo("EUR");
        assertThat(applePay.getCardBrand()).isNotNull();
        assertThat(applePay.getCardType()).isNotNull();
        assertThat(applePay.getFirst6()).isNotNull();
        assertThat(applePay.getLast4()).isNotNull();

    }
}
