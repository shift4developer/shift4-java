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
	private ThreeDSecure threeDSecure;
	private GooglePay googlePay;
	private String source;

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

	public ThreeDSecure getThreeDSecure() {
		return threeDSecure;
	}

	public GooglePay getGooglePay() {
		return googlePay;
	}

	public String getSource() {
		return source;
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

	public PaymentMethodRequest threeDSecure(ThreeDSecure threeDSecure) {
		this.threeDSecure = threeDSecure;
		return this;
	}

	public PaymentMethodRequest googlePay(GooglePay googlePay) {
		this.googlePay = googlePay;
		return this;
	}

	public PaymentMethodRequest source(String source) {
		this.source = source;
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

	public static class ThreeDSecure {
		private String currency;
		private Integer amount;


		public ThreeDSecure(String currency, Integer amount) {
			this.currency = currency;
			this.amount = amount;
		}

		public ThreeDSecure() {
		}

		public ThreeDSecure currency(String currency) {
			this.currency = currency;
			return this;
		}

		public ThreeDSecure amount(Integer amount) {
			this.amount = amount;
			return this;
		}

		@JsonIgnore
		private final Map<String, Object> other = new HashMap<>();

		@JsonAnyGetter
		private Map<String, Object> getOtherMap() {
			return other;
		}

		@JsonAnySetter
		public ThreeDSecure set(String name, Object value) {
			other.put(name, value);
			return this;
		}
	}

	public static class GooglePay {
		private String token;

		public GooglePay(String token) {
			this.token = token;
		}

		public GooglePay() {
		}

		public GooglePay token(String token) {
			this.token = token;
			return this;
		}

		public String getToken() {
			return token;
		}

		@JsonIgnore
		private final Map<String, Object> other = new HashMap<>();


		@JsonAnyGetter
		private Map<String, Object> getOtherMap() {
			return other;
		}

		@JsonAnySetter
		public GooglePay set(String name, Object value) {
			other.put(name, value);
			return this;
		}

	}



}
