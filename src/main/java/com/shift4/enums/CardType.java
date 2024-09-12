package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CardType {

	CREDIT_CARD("Credit Card"),
	DEBIT_CARD("Debit Card"),
	PREPAID_CARD("Prepaid Card"),
	UNKNOWN("Unknown"),

	/**
	 * Used when received value can't be mapped to this enumeration.
	 */
	UNRECOGNIZED("unrecognized");

	private final String value;

	CardType(String value) {
		this.value = value;
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static CardType fromValue(String value) {
		if (value == null) {
			return null;
		}
		for (CardType cardType : values()) {
			if (cardType.value.equalsIgnoreCase(value)) {
				return cardType;
			}
		}

		return UNRECOGNIZED;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
