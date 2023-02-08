package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shift4.enums.PaymentMethodType;
import com.shift4.response.Customer;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethodRequest {
	private String id;
	private String customerId;
	private PaymentMethodType type;
	private BillingRequest billing;
	private ApplePay applePay;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public PaymentMethodRequest() {
	}

	public PaymentMethodRequest(String id) {
		this.id = id;
	}

	public PaymentMethodRequest(PaymentMethodType type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public PaymentMethodType getType() {
		return type;
	}

	public BillingRequest getBilling() {
		return billing;
	}

	public ApplePay getApplePay() {
		return applePay;
	}

	public PaymentMethodRequest id(String id) {
		this.id = id;
		return this;
	}

	public PaymentMethodRequest customer(Customer customer) {
		return customerId(customer.getId());
	}

	public PaymentMethodRequest customerId(String customerId) {
		this.customerId = customerId;
		return this;
	}

	public PaymentMethodRequest type(PaymentMethodType type) {
		this.type = type;
		return this;
	}

	public PaymentMethodRequest billing(BillingRequest billing) {
		this.billing = billing;
		return this;
	}

	public PaymentMethodRequest applePay(ApplePay applePay) {
		this.applePay = applePay;
		return this;
	}

	@JsonAnyGetter
	private Map<String, Object> getOtherMap() {
		return other;
	}

	@JsonAnySetter
	public PaymentMethodRequest set(String name, Object value) {
		other.put(name, value);
		return this;
	}

	public static class ApplePay {
		private Object token;

		public ApplePay(Object token) {
			this.token = token;
		}

		public ApplePay() {
		}

		public ApplePay token(Object token) {
			this.token = token;
			return this;
		}

		@JsonIgnore
		private final Map<String, Object> other = new HashMap<>();


		@JsonAnyGetter
		private Map<String, Object> getOtherMap() {
			return other;
		}

		@JsonAnySetter
		public ApplePay set(String name, Object value) {
			other.put(name, value);
			return this;
		}
	}
}
