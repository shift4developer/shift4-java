package com.shift4;

import com.shift4.request.CustomerListRequest;
import com.shift4.request.CustomerRequest;
import com.shift4.request.CustomerUpdateRequest;
import com.shift4.response.Customer;
import com.shift4.response.DeleteResponse;
import com.shift4.response.ListResponse;
import com.shift4.testdata.Emails;
import org.junit.jupiter.api.Test;

import static com.shift4.testdata.Customers.customer;
import static java.util.Collections.singletonMap;
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
        assertThat(created.getEmail()).isEqualTo(request.getEmail());
        assertThat(created.getDescription()).isEqualTo(request.getDescription());
        assertThat(created.getMetadata()).isEqualTo(request.getMetadata());
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
}