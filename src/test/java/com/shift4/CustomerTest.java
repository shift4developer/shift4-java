package com.shift4;

import com.shift4.enums.ChargeStatus;
import com.shift4.request.ChargeRequest;
import com.shift4.response.Charge;
import org.junit.jupiter.api.Test;

import static com.shift4.testdata.Cards.successCard;
import static com.shift4.testdata.Charges.charge;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest extends AbstractShift4GatewayTest {

    @Test
    void shouldDeleteCustomer() {
        // given
        ChargeRequest request = charge().card(successCard());
        // when
        Charge charge = gateway.createCharge(request);
        // then
        assertThat(charge.getStatus()).isEqualTo(ChargeStatus.SUCCESSFUL);
        assertThat(charge.getAmount()).isEqualTo(request.getAmount());
        assertThat(charge.getCurrency()).isEqualTo(request.getCurrency());
        assertThat(charge.getFailureCode()).isNull();
    }
}