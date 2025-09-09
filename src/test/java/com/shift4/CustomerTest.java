package com.shift4;

import com.shift4.request.*;
import com.shift4.response.Customer;
import com.shift4.response.DeleteResponse;
import com.shift4.response.ListResponse;
import com.shift4.testdata.Emails;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static com.shift4.testdata.Customers.customer;
import static java.util.Collections.singletonMap;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest extends AbstractShift4GatewayTest {

    @Test
    void shouldCreateCustomer() {
        // given
        CustomerRequest request = customer()
                .description("Some customer description")
                .metadata(singletonMap("test-key", "Test value"));
        // when
        Customer created = gateway.createCustomer(request);
        // then
        assertThat(created.isDeleted()).isFalse();
        assertThat(created.getId()).isNotBlank();
        assertThat(created.getEmail()).isEqualToIgnoringCase(request.getEmail());
        assertThat(created.getDescription()).isEqualTo(request.getDescription());
        assertThat(created.getMetadata()).isEqualTo(request.getMetadata());
        assertThat(created.getBilling()).isNull();
    }

    @Test
    void shouldUpdateCreateCustomer() {
        // given
        Customer created = gateway.createCustomer(customer());
        // when
        CustomerUpdateRequest request = new CustomerUpdateRequest(created)
                .description("Some customer description")
                .metadata(singletonMap("test-key", "Test value"));
        Customer updated = gateway.updateCustomer(request);
        // then
        assertThat(updated.isDeleted())
                .isFalse();
        assertThat(updated.getId())
                .isNotBlank()
                .isEqualTo(created.getId());
        assertThat(updated.getEmail())
                .isNotBlank()
                .isEqualTo(created.getEmail());
        assertThat(updated.getDescription())
                .isEqualTo(request.getDescription())
                .isNotEqualTo(created.getDescription());
        assertThat(updated.getMetadata())
                .isEqualTo(request.getMetadata())
                .isNotEqualTo(created.getMetadata());
    }

    @Test
    void shouldRetrieveCustomer() {
        // given
        Customer created = gateway.createCustomer(customer());
        // when
        Customer retrieved = gateway.retrieveCustomer(created.getId());
        // then
        assertThat(created).usingRecursiveComparison().isEqualTo(retrieved);
    }

    @Test
    void shouldDeleteCustomer() {
        // given
        Customer created = gateway.createCustomer(customer());
        // when
        DeleteResponse deleteResponse = gateway.deleteCustomer(created.getId());
        Customer deleted = gateway.retrieveCustomer(created.getId());
        // then
        assertThat(created.isDeleted()).isFalse();
        assertThat(deleteResponse.getId()).isEqualTo(created.getId());
        assertThat(deleted.isDeleted()).isTrue();
    }

    @Test
    void shouldListCustomers() {
        // given
        String email = Emails.email();
        Customer customer1 = gateway.createCustomer(new CustomerRequest().email(email));
        Customer customer2 = gateway.createCustomer(new CustomerRequest().email(email));
        Customer customer3 = gateway.createCustomer(new CustomerRequest().email(email));
        // when
        ListResponse<Customer> response = gateway.listCustomers(new CustomerListRequest().email(email));
        // then
        assertThat(response.getList())
                .extracting(Customer::getId)
                .containsExactly(customer3.getId(), customer2.getId(), customer1.getId());
    }

    @Test
    void shouldCreateAndRetrieveCustomerWithBillingAddress() {
        //given
        BillingRequest billingRequest = new BillingRequest()
                .name(randomAlphabetic(10))
                .email(Emails.email())
                .vat(randomAlphabetic(10))
                .phone(randomNumeric(9))
                .address(new AddressRequest()
                        .line1(randomAlphabetic(10))
                        .line2(randomAlphabetic(10))
                        .state(randomAlphabetic(10))
                        .zip(randomNumeric(5))
                        .city(randomAlphabetic(10))
                        .country("SE"));

        // when
        Customer customer = gateway.createCustomer(new CustomerRequest()
                .email(Emails.email())
                .billing(billingRequest));

        // then
        assertThat(customer.getBilling().getEmail()).isEqualTo(billingRequest.getEmail());
        assertThat(customer.getBilling().getName()).isEqualTo(billingRequest.getName());
        assertThat(customer.getBilling().getVat()).isEqualTo(billingRequest.getVat());
        assertThat(customer.getBilling().getPhone()).isEqualTo(billingRequest.getPhone());
        assertThat(customer.getBilling().getAddress().getLine1()).isEqualTo(billingRequest.getAddress().getLine1());
        assertThat(customer.getBilling().getAddress().getLine2()).isEqualTo(billingRequest.getAddress().getLine2());
        assertThat(customer.getBilling().getAddress().getState()).isEqualTo(billingRequest.getAddress().getState());
        assertThat(customer.getBilling().getAddress().getZip()).isEqualTo(billingRequest.getAddress().getZip());
        assertThat(customer.getBilling().getAddress().getCity()).isEqualTo(billingRequest.getAddress().getCity());
        assertThat(customer.getBilling().getAddress().getCountry()).isEqualTo(billingRequest.getAddress().getCountry());

        // when
        Customer retrieved = gateway.retrieveCustomer(customer.getId());

        // then
        assertThat(retrieved.getBilling().getEmail()).isEqualTo(billingRequest.getEmail());
        assertThat(retrieved.getBilling().getName()).isEqualTo(billingRequest.getName());
        assertThat(retrieved.getBilling().getVat()).isEqualTo(billingRequest.getVat());
        assertThat(retrieved.getBilling().getPhone()).isEqualTo(billingRequest.getPhone());
        assertThat(retrieved.getBilling().getAddress().getLine1()).isEqualTo(billingRequest.getAddress().getLine1());
        assertThat(retrieved.getBilling().getAddress().getLine2()).isEqualTo(billingRequest.getAddress().getLine2());
        assertThat(retrieved.getBilling().getAddress().getState()).isEqualTo(billingRequest.getAddress().getState());
        assertThat(retrieved.getBilling().getAddress().getZip()).isEqualTo(billingRequest.getAddress().getZip());
        assertThat(retrieved.getBilling().getAddress().getCity()).isEqualTo(billingRequest.getAddress().getCity());
        assertThat(retrieved.getBilling().getAddress().getCountry()).isEqualTo(billingRequest.getAddress().getCountry());
    }

    @Test
    void shouldUpdateBillingAddress() {
        //given
        BillingRequest billingRequest = new BillingRequest()
                .name(randomAlphabetic(10))
                .email(Emails.email())
                .vat(randomAlphabetic(10))
                .phone(randomNumeric(9))
                .address(new AddressRequest()
                        .line1(randomAlphabetic(10))
                        .line2(randomAlphabetic(10))
                        .state(randomAlphabetic(10))
                        .zip(randomNumeric(5))
                        .city(randomAlphabetic(10))
                        .country("SE"));

        Customer customer = gateway.createCustomer(new CustomerRequest()
                .email(Emails.email())
                .billing(billingRequest));

        BillingRequest updatedBillingRequest = new BillingRequest()
                .name(randomAlphabetic(10))
                .email(Emails.email())
                .vat(randomAlphabetic(10))
                .phone(randomNumeric(9))
                .address(new AddressRequest()
                        .line1(randomAlphabetic(10))
                        .line2(randomAlphabetic(10))
                        .state(randomAlphabetic(10))
                        .zip(randomNumeric(5))
                        .city(randomAlphabetic(10))
                        .country("SE"));

        //when
        Customer updated = gateway.updateCustomer(new CustomerUpdateRequest()
                .customer(customer)
                .billing(updatedBillingRequest));

        //then
        assertThat(updated.getBilling().getEmail()).isEqualTo(updatedBillingRequest.getEmail());
        assertThat(updated.getBilling().getName()).isEqualTo(updatedBillingRequest.getName());
        assertThat(updated.getBilling().getVat()).isEqualTo(updatedBillingRequest.getVat());
        assertThat(updated.getBilling().getPhone()).isEqualTo(updatedBillingRequest.getPhone());
        assertThat(updated.getBilling().getAddress().getLine1()).isEqualTo(updatedBillingRequest.getAddress().getLine1());
        assertThat(updated.getBilling().getAddress().getLine2()).isEqualTo(updatedBillingRequest.getAddress().getLine2());
        assertThat(updated.getBilling().getAddress().getState()).isEqualTo(updatedBillingRequest.getAddress().getState());
        assertThat(updated.getBilling().getAddress().getZip()).isEqualTo(updatedBillingRequest.getAddress().getZip());
        assertThat(updated.getBilling().getAddress().getCity()).isEqualTo(updatedBillingRequest.getAddress().getCity());
        assertThat(updated.getBilling().getAddress().getCountry()).isEqualTo(updatedBillingRequest.getAddress().getCountry());

        // when
        Customer retrieved = gateway.retrieveCustomer(customer.getId());

        // then
        assertThat(retrieved.getBilling().getEmail()).isEqualTo(updatedBillingRequest.getEmail());
        assertThat(retrieved.getBilling().getName()).isEqualTo(updatedBillingRequest.getName());
        assertThat(retrieved.getBilling().getVat()).isEqualTo(updatedBillingRequest.getVat());
        assertThat(retrieved.getBilling().getPhone()).isEqualTo(updatedBillingRequest.getPhone());
        assertThat(retrieved.getBilling().getAddress().getLine1()).isEqualTo(updatedBillingRequest.getAddress().getLine1());
        assertThat(retrieved.getBilling().getAddress().getLine2()).isEqualTo(updatedBillingRequest.getAddress().getLine2());
        assertThat(retrieved.getBilling().getAddress().getState()).isEqualTo(updatedBillingRequest.getAddress().getState());
        assertThat(retrieved.getBilling().getAddress().getZip()).isEqualTo(updatedBillingRequest.getAddress().getZip());
        assertThat(retrieved.getBilling().getAddress().getCity()).isEqualTo(updatedBillingRequest.getAddress().getCity());
        assertThat(retrieved.getBilling().getAddress().getCountry()).isEqualTo(updatedBillingRequest.getAddress().getCountry());
    }
}