package com.shift4;

import com.shift4.request.ChargeRequest;
import com.shift4.request.CreatedFilter;
import com.shift4.request.PayoutListRequest;
import com.shift4.response.Charge;
import com.shift4.response.ListResponse;
import com.shift4.response.Payout;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.shift4.testdata.Cards.successCard;
import static com.shift4.testdata.Charges.charge;
import static org.assertj.core.api.Assertions.assertThat;

public class PayoutsTest extends AbstractShift4GatewayTest {

    @Test
    void shouldCreatePayout() {
        // given
        ChargeRequest request = charge().card(successCard()).currency("EUR");
        Charge charge = gateway.createCharge(request);

        // when
        Payout payout = gateway.createPayout();

        // then
        assertThat(payout.getCurrency()).isEqualTo("EUR");

    }

    @Test
    void shouldListPayouts() {
        // given
        ChargeRequest request = charge().card(successCard()).currency("EUR");
        Charge charge = gateway.createCharge(request);

        // when
        Payout payout = gateway.createPayout();
        ListResponse<Payout> payouts = gateway.listPayouts(new PayoutListRequest().created(new CreatedFilter().gte(payout.getCreated())));

        // then
        Optional<Payout> foundPayout = payouts.getList().stream().filter(
                p -> p.getId().equals(payout.getId())
        ).findFirst();
        assertThat(foundPayout.isPresent());
        assertThat(foundPayout.get().getCurrency()).isEqualTo("EUR");

    }
}
