package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.AuthenticationFlow;
import com.shift4.enums.LiabilityShift;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class ThreeDSecureInfo {
	private Integer amount;
	private String currency;
	private boolean enrolled;
	private String liabilityShift;
	private String version;
	private String authenticationFlow;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public Integer getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public boolean isEnrolled() {
		return enrolled;
	}

	public LiabilityShift getLiabilityShift() {
		return LiabilityShift.fromValue(liabilityShift);
	}

	public String getLiabilityShiftAsString() {
		return liabilityShift;
	}

	public String getVersion() {
		return version;
	}

	public AuthenticationFlow getAuthenticationFlow() {
		return AuthenticationFlow.fromValue(authenticationFlow);
	}

	public String getAuthenticationFlowAsString() {
		return authenticationFlow;
	}

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
