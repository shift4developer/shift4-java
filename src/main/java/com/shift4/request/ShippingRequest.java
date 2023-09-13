package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingRequest {
	private String name;
	private AddressRequest address;
	private String phone;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public ShippingRequest() {
	}

	public ShippingRequest(String name, AddressRequest address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public AddressRequest getAddress() {
		return address;
	}

  public String getPhone() {
      return phone;
  }

	public ShippingRequest name(String name) {
		this.name = name;
		return this;
	}

	public ShippingRequest address(AddressRequest address) {
		this.address = address;
		return this;
	}

  public ShippingRequest phone(String phone) {
      this.phone = phone;
      return this;
  }

	@JsonAnyGetter
	private Map<String, Object> getOtherMap() {
		return other;
	}

	@JsonAnySetter
	public ShippingRequest set(String name, Object value) {
		other.put(name, value);
		return this;
	}
}
