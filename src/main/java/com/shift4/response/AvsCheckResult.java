package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AvsCheckResult {
	FULL_MATCH("full_match"),
	PARTIAL_MATCH("partial_match"),
	NO_MATCH("no_match"),
	NOT_PROVIDED("not_provided"),
	UNAVAILABLE("unavailable");

	private final String value;

	AvsCheckResult(String value) {
		this.value = value;
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static AvsCheckResult fromValue(String value) {
		if (value == null) {
			return null;
		}
		for (AvsCheckResult type : values()) {
			if (type.value.equalsIgnoreCase(value)) {
				return type;
			}
		}

		return UNAVAILABLE;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}