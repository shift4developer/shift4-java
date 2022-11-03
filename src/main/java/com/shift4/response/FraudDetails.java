package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.FraudStatus;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class FraudDetails {

	private String status;
	private Integer score;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public FraudStatus getStatus() {
		return FraudStatus.fromValue(status);
	}

	public String getStatusAsString() {
		return status;
	}

	public Integer getScore() {
		return score;
	}

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
