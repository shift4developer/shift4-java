package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class Billing {
	private String name;
	private Address address;
	private String vat;
	private String email;
	private String phone;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public String getName() {
		return name;
	}

	public Address getAddress() {
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

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
