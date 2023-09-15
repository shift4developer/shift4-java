package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class FraudCheckData {

	private String ipAddress;
	private String ipCountry;
	private String email;
	private String userAgent;
	private String acceptLanguage;
	private String browserFingerprint;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public String getIpAddress() {
		return ipAddress;
	}

	public String getIpCountry() {
		return ipCountry;
	}

	public String getEmail() {
		return email;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public String getBrowserFingerprint() {
		return browserFingerprint;
	}

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
