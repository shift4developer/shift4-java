package com.shift4;

import com.shift4.request.ChargeRequest;
import com.shift4.request.RefundRequest;
import com.shift4.request.RefundUpdateRequest;
import com.shift4.response.Charge;
import com.shift4.response.Refund;
import org.junit.jupiter.api.Test;

import static com.shift4.testdata.Cards.successCard;
import static com.shift4.testdata.Charges.charge;
import static com.shift4.testdata.Metadata.metadata;
import static org.assertj.core.api.Assertions.assertThat;

class RefundTest extends AbstractShift4GatewayTest {
    @Test
    void shouldCreateRefund() {
        // given
        ChargeRequest request = charge().card(successCard());
        Charge charge = gateway.createCharge(request);

        // when
        Refund refund = gateway.createRefund(new RefundRequest().charge(charge));
        Charge refundedCharge = gateway.retrieveCharge(charge.getId());

        // then
        assertThat(refundedCharge.getRefunded()).isTrue();
        assertThat(refund.getCharge()).isEqualTo(charge.getId());
    }

    @Test
    void shouldUpdateRefund() {
        // given
        ChargeRequest request = charge().card(successCard());
        Charge charge = gateway.createCharge(request);
        Refund refund = gateway.createRefund(new RefundRequest().charge(charge));

        // when
        RefundUpdateRequest refundUpdateRequest = new RefundUpdateRequest()
                .refundId(refund.getId())
                .metadata(metadata());
        Refund updatedRefund = gateway.updateRefund(refundUpdateRequest);

        // then
        assertThat(updatedRefund.getMetadata()).isEqualTo(metadata());
    }
}