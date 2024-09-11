package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethodType {

	APPLE_PAY("apple_pay"),
	ALIPAY("alipay"),
	BANCONTACT("bancontact"),
	BITPAY("bitpay"),
	BLIK("blik"),
	BOLETO("boleto"),
	EPS("eps"),
	ESTONIANBANKS("estonianbanks"),
	IDEAL("ideal"),
	LATVIANBANKS("latvianbanks"),
	LITHUANIANBANKS("lithuanianbanks"),
	MAXIMA("maxima"),
	MULTIBANCO("multibanco"),
	MYBANK("mybank"),
	P24("p24"),
	PAYPOST("paypost"),
	PAYSAFECARD("paysafecard"),
	PAYSAFECASH("paysafecash"),
	PAYSERA("paysera"),
	PAYU("payu"),
	PERLAS("perlas"),
	SKRILL("skrill"),
	TRUSTLY("trustly"),
	UNIONPAY("unionpay"),
	VERKKOPANKKI("verkkopankki"),
	WECHATPAY("wechatpay"),
	GOOGLE_PAY("google_pay"),
	THREE_D_SECURE("three_d_secure"),

	/**
	 * Used when received value can't be mapped to this enumeration.
	 */
	UNRECOGNIZED("unrecognized");

	private final String value;

	PaymentMethodType(String value) {
		this.value = value;
	}

	@JsonCreator
	public static PaymentMethodType fromValue(@JsonProperty("value") String value) {
		if (value == null) {
			return null;
		}
		for (PaymentMethodType type : values()) {
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
