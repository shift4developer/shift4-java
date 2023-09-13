package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingRequest {
	private String name;
	private AddressRequest address;
	private String vat;
	private String email;
	private String phone;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public BillingRequest() {
	}

	public BillingRequest(String name, AddressRequest address, String vat) {
		this.name = name;
		this.address = address;
		this.vat = vat;
	}

	public String getName() {
		return name;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public String getVat() {
		return vat;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public BillingRequest name(String name) {
		this.name = name;
		return this;
	}

	public BillingRequest address(AddressRequest address) {
		this.address = address;
		return this;
	}

	public BillingRequest vat(String vat) {
		this.vat = vat;
		return this;
	}

	public BillingRequest email(String email) {
		this.email = email;
		return this;
	}

	public BillingRequest phone(String phone) {
		this.phone = phone;
		return this;
	}

	@JsonAnyGetter
	private Map<String, Object> getOtherMap() {
		return other;
	}

	@JsonAnySetter
	public BillingRequest set(String name, Object value) {
		other.put(name, value);
		return this;
	}
}
