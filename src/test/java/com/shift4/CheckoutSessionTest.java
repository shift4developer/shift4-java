package com.shift4;

import com.shift4.enums.CustomFieldPlacement;
import com.shift4.enums.Interval;
import com.shift4.request.Amount;
import com.shift4.request.CheckoutCustomField;
import com.shift4.request.CheckoutProductRequest;
import com.shift4.request.CheckoutSessionRequest;
import com.shift4.request.LineItemRequest;
import com.shift4.request.PlanRequest;
import com.shift4.request.ProductRequest;
import com.shift4.response.CheckoutSession;
import com.shift4.response.Customer;
import com.shift4.response.Plan;
import com.shift4.response.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shift4.testdata.Cards.successCard;
import static com.shift4.testdata.CheckoutSessions.checkoutSession;
import static com.shift4.testdata.CheckoutSessions.customField;
import static com.shift4.testdata.CheckoutSessions.lineItem;
import static com.shift4.testdata.CheckoutSessions.simpleProduct;
import static com.shift4.testdata.Customers.customer;
import static org.assertj.core.api.Assertions.assertThat;

class CheckoutSessionTest extends AbstractShift4GatewayTest {

    @Test
    void shouldCreateCheckoutSessionWithInlineProduct() {
        // given
        CheckoutProductRequest product = new CheckoutProductRequest()
                .name("Test Product")
                .amount(2999)
                .currency("USD");

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(product)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getLineItems().get(0).getProduct().getName())
                .isEqualTo("Test Product");
    }

    @Test
    void shouldReturnClientSecretWhenCreatingCheckoutSession() {
        // given
        CheckoutProductRequest product = new CheckoutProductRequest()
                .name("Test Product")
                .amount(1999)
                .currency("USD");

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(product)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getClientSecret()).isNotNull();
    }

    @Test
    void shouldCreateCheckoutSessionWithProductId() {
        // given
        Product createdProduct = gateway.createProduct(
                new ProductRequest("Test Product", "USD")
                        .amount(new Amount(2999))
        );

        CheckoutProductRequest productRef = new CheckoutProductRequest(createdProduct.getId());
        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(productRef)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getLineItems().get(0).getProduct().getId())
                .isEqualTo(createdProduct.getId());
    }

    @Test
    void shouldCreateCheckoutSessionWithCustomFields() {
        // given
        CheckoutCustomField companyField = new CheckoutCustomField()
                .key("company")
                .label("Company Name")
                .optional(false)
                .placement(CustomFieldPlacement.STANDARD);

        CheckoutSessionRequest request = checkoutSession()
                .customFields(Collections.singletonList(companyField));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getCustomFields()).hasSize(1);
        assertThat(session.getCustomFields().get(0).getKey()).isEqualTo("company");
        assertThat(session.getCustomFields().get(0).getLabel()).isEqualTo("Company Name");
    }

    @Test
    void shouldCreateCheckoutSessionWithMetadata() {
        // given
        Map<String, String> metadata = new HashMap<>();
        metadata.put("order_id", "12345");
        metadata.put("customer_tier", "premium");

        CheckoutSessionRequest request = checkoutSession()
                .metadata(metadata);

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getMetadata()).containsEntry("order_id", "12345");
        assertThat(session.getMetadata()).containsEntry("customer_tier", "premium");
    }

    @Test
    void shouldCreateCheckoutSessionWithMultipleLineItems() {
        // given
        CheckoutProductRequest product1 = simpleProduct().name("Product 1").amount(1000);
        CheckoutProductRequest product2 = simpleProduct().name("Product 2").amount(2000);

        List<LineItemRequest> lineItems = Arrays.asList(
                lineItem(product1),
                new LineItemRequest(product2, 2)
        );

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(lineItems);

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(2);
        assertThat(session.getLineItems().get(0).getProduct().getName()).isEqualTo("Product 1");
        assertThat(session.getLineItems().get(1).getProduct().getName()).isEqualTo("Product 2");
        assertThat(session.getLineItems().get(1).getQuantity()).isEqualTo(2);
    }

    @Test
    void shouldCreateCheckoutSessionWithCustomer() {
        // given
        Customer customer = gateway.createCustomer(customer(successCard()));

        CheckoutSessionRequest request = checkoutSession()
                .customer(customer);

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getCustomer()).isEqualTo(customer.getId());
    }

    @Test
    void shouldCreateCheckoutSessionWithLocaleAndCapture() {
        // given
        CheckoutSessionRequest request = checkoutSession()
                .locale("en")
                .capture(true)
                .collectBillingAddress(true)
                .collectShippingAddress(false);

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLocale()).isEqualTo("en");
        assertThat(session.getCapture()).isTrue();
        assertThat(session.getCollectBillingAddress()).isTrue();
        assertThat(session.getCollectShippingAddress()).isFalse();
    }

    @Test
    void shouldCreateCheckoutSessionWithDonation() {
        // given
        Amount donationAmount = new Amount()
                .options(Arrays.asList(500, 1000, 2000))
                .custom(100, 5000);

        CheckoutProductRequest product = new CheckoutProductRequest()
                .name("Donation")
                .amount(donationAmount)
                .currency("USD");

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(product)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getLineItems().get(0).getProduct().getName()).isEqualTo("Donation");
    }

    @Test
    void shouldCreateCheckoutSessionWithComplexScenario() {
        // given
        CheckoutProductRequest product = new CheckoutProductRequest()
                .name("Premium Subscription")
                .amount(2999)
                .currency("USD")
                .description("Monthly premium plan");

        CheckoutCustomField companyField = customField();

        Map<String, String> metadata = new HashMap<>();
        metadata.put("subscription_tier", "premium");

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(lineItem(product)))
                .collectBillingAddress(true)
                .locale("en")
                .capture(true)
                .customFields(Collections.singletonList(companyField))
                .metadata(metadata);

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getCollectBillingAddress()).isTrue();
        assertThat(session.getLocale()).isEqualTo("en");
        assertThat(session.getCapture()).isTrue();
        assertThat(session.getCustomFields()).hasSize(1);
        assertThat(session.getMetadata()).containsEntry("subscription_tier", "premium");
    }

    @Test
    void shouldCreateCheckoutSessionWithSubscriptionPlan() {
        // given - Create a plan first
        Plan plan = gateway.createPlan(
                new PlanRequest(2999, "USD", Interval.MONTH, "Monthly Subscription")
        );

        CheckoutProductRequest planProduct = new CheckoutProductRequest(plan.getId());
        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(planProduct)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getLineItems().get(0).getProduct().getId())
                .isEqualTo(plan.getId());
    }

    @Test
    void shouldCreateCheckoutSessionWithDonationOptionsOnly() {
        // given
        Amount donationAmount = new Amount()
                .options(Arrays.asList(500, 1000, 2000, 5000));

        CheckoutProductRequest product = new CheckoutProductRequest()
                .name("Charity Donation")
                .amount(donationAmount)
                .currency("USD");

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(product)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getLineItems().get(0).getProduct().getName())
                .isEqualTo("Charity Donation");
    }

    @Test
    void shouldCreateCheckoutSessionWithDonationCustomRangeOnly() {
        // given
        Amount donationAmount = new Amount()
                .custom(100, 10000);

        CheckoutProductRequest product = new CheckoutProductRequest()
                .name("Custom Donation")
                .amount(donationAmount)
                .currency("USD");

        CheckoutSessionRequest request = new CheckoutSessionRequest()
                .lineItems(Collections.singletonList(new LineItemRequest(product)));

        // when
        CheckoutSession session = gateway.createCheckoutSession(request);

        // then
        assertThat(session.getId()).isNotNull();
        assertThat(session.getLineItems()).hasSize(1);
        assertThat(session.getLineItems().get(0).getProduct().getName())
                .isEqualTo("Custom Donation");
    }
}
