package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethodStatus {

	USED("used"),
	PENDING("pending"),
	CHARGEABLE("chargeable"),
	FAILED("failed"),

	/**
	 * Used when received value can't be mapped to this enumeration.
	 */
	UNRECOGNIZED("unrecognized");

	private final String value;

	PaymentMethodStatus(String value) {
		this.value = value;
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static PaymentMethodStatus fromValue(String value) {
		if (value == null) {
			return null;
		}
		for (PaymentMethodStatus type : values()) {
			if (type.value.equalsIgnoreCase(value)) {
				return type;
			}
		}

		return UNRECOGNIZED;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
