package com.shift4;

import com.shift4.enums.PaymentMethodType;
import com.shift4.request.AddressRequest;
import com.shift4.request.BillingRequest;
import com.shift4.request.FraudCheckDataRequest;
import com.shift4.request.PaymentMethodRequest;
import com.shift4.response.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodIT extends AbstractShift4GatewayTest {

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

    @Test
    void shouldCreateGooglePayPaymentMethod() {
        // when
        PaymentMethod paymentMethod = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.GOOGLE_PAY)
                .googlePay(new PaymentMethodRequest.GooglePay("PAN_ONLY")));

        // then
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getId()).isNotNull();
        assertThat(paymentMethod.getType()).isEqualTo(PaymentMethodType.GOOGLE_PAY);
        PaymentMethod.GooglePay googlePay = paymentMethod.getGooglePay();
        assertThat(googlePay).isNotNull();
        assertThat(googlePay.getCardBrand()).isNotNull();
        assertThat(googlePay.getCardType()).isNotNull();
        assertThat(googlePay.getFirst6()).isNotNull();
        assertThat(googlePay.getLast4()).isNotNull();
        assertThat(paymentMethod.getFlow()).isNotNull();
        assertThat(paymentMethod.getFlow().getNextAction()).isNotNull();
    }

    @Test
    void shouldCreateSwishPaymentMethod() {
        // when
        PaymentMethod paymentMethod = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.SWISH)
                .billing(new BillingRequest().address(new AddressRequest().country("SE")).name("John Doe"))
                .swish(new PaymentMethodRequest.Swish(PaymentMethodRequest.Swish.SwishLinkMethod.QR_CODE)));

        // then
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getId()).isNotNull();
        assertThat(paymentMethod.getType()).isEqualTo(PaymentMethodType.SWISH);

        assertThat(paymentMethod.getFlow()).isNotNull();
        assertThat(paymentMethod.getFlow().getNextAction()).isNotNull();
    }

    @Test
    void shouldCreateBlikPaymentMethod() {
        // when
        PaymentMethod paymentMethod = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.BLIK)
                .billing(new BillingRequest().address(new AddressRequest().country("PL")).name("John Doe")));

        // then
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getId()).isNotNull();
        assertThat(paymentMethod.getType()).isEqualTo(PaymentMethodType.BLIK);

        assertThat(paymentMethod.getFlow()).isNotNull();
        assertThat(paymentMethod.getFlow().getNextAction()).isNotNull();
    }

    @Test
    void shouldCreateBlikWithCodePaymentMethod() {
        // when
        PaymentMethod paymentMethod = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.BLIK)
                .billing(new BillingRequest().address(new AddressRequest().country("PL")).name("John Doe"))
                .fraudCheckData(new FraudCheckDataRequest().userAgent("").ipAddress("127.0.0.1"))
                .blik(new PaymentMethodRequest.Blik().code("123456")));

        // then
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getId()).isNotNull();
        assertThat(paymentMethod.getType()).isEqualTo(PaymentMethodType.BLIK);

        assertThat(paymentMethod.getFlow()).isNotNull();
        assertThat(paymentMethod.getFlow().getNextAction()).isNotNull();
    }

    @Test
    void shouldCreateGooglePayWithThreeDSecurePaymentMethod() {
        // when
        PaymentMethod source = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.GOOGLE_PAY)
                .googlePay(new PaymentMethodRequest.GooglePay("CRYPTOGRAM_3DS")));
        PaymentMethod paymentMethod = gateway.createPaymentMethod(new PaymentMethodRequest(PaymentMethodType.THREE_D_SECURE)
                .source(source.getId())
                .googlePay(new PaymentMethodRequest.GooglePay("CRYPTOGRAM_3DS"))
                .threeDSecure(new PaymentMethodRequest.ThreeDSecure()
                        .currency("USD")
                        .amount(60))
        );

        // then
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getId()).isNotNull();
        assertThat(paymentMethod.getType()).isEqualTo(PaymentMethodType.THREE_D_SECURE);

        assertThat(paymentMethod.getThreeDSecure()).isNotNull();
        assertThat(paymentMethod.getThreeDSecure().getAmount()).isEqualTo(60);
        assertThat(paymentMethod.getThreeDSecure().getCurrency()).isEqualTo("USD");

        assertThat(paymentMethod.getSource()).isNotNull();
        assertThat(paymentMethod.getSource().getId()).isEqualTo(source.getId());

        assertThat(paymentMethod.getFlow()).isNotNull();
        assertThat(paymentMethod.getFlow().getNextAction()).isNotNull();
    }
}
