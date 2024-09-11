package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
	AMOUNT_TOO_HIGH("amount_too_high"),
	AMOUNT_TOO_LOW("amount_too_low"),
	INVALID_NUMBER("invalid_number"),
	INVALID_EXPIRY_MONTH("invalid_expiry_month"),
	INVALID_EXPIRY_YEAR("invalid_expiry_year"),
	INVALID_CVC("invalid_cvc"),
	INCORRECT_CVC("incorrect_cvc"),
	INCORRECT_ZIP("incorrect_zip"),
	INCORRECT_ADDRESS("incorrect_address"),
	EXPIRED_CARD("expired_card"),
	INSUFFICIENT_FUNDS("insufficient_funds"),
	LOST_OR_STOLEN("lost_or_stolen"),
	SUSPECTED_FRAUD("suspected_fraud"),
	CARD_DECLINED("card_declined"),
	PROCESSING_ERROR("processing_error"),
	BLACKLISTED("blacklisted"),
	BLOCKED("blocked"),
	EXPIRED_TOKEN("expired_token"),
	LIMIT_EXCEEDED("limit_exceeded"),
	AUTHENTICATION_REQUIRED("authentication_required"),
	DO_NOT_TRY_AGAIN("do_not_try_again"),
	CURRENCY_NOT_SUPPORTED("currency_not_supported"),
	BRAND_NOT_SUPPORTED("brand_not_supported"),
	PAYMENT_METHOD_DECLINED("payment_method_declined"),
	INVALID_ACCOUNT("invalid_account"),

	/**
	 * Used when received value can't be mapped to this enumeration.
	 */
	UNRECOGNIZED("unrecognized");

	private final String value;

	ErrorCode(String value) {
		this.value = value;
	}

	@JsonCreator
	public static ErrorCode fromValue(@JsonProperty("value") String value) {
		if (value == null) {
			return null;
		}
		for (ErrorCode errorCode : values()) {
			if (errorCode.value.equalsIgnoreCase(value)) {
				return errorCode;
			}
		}

		return UNRECOGNIZED;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
