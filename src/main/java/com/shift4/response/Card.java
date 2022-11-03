package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.CardBrand;
import com.shift4.enums.CardType;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class Card {

	private String id;
	private Long created;
	private boolean deleted = false;

	private String first6;
	private String last4;
	private String fingerprint;

	private String expMonth;
	private String expYear;

	private String cardholderName;
	private String customerId;
	private String brand;
	private String type;

	private String country;

	private String addressLine1;
	private String addressLine2;
	private String addressCity;
	private String addressState;
	private String addressZip;
	private String addressCountry;

	private FraudCheckData fraudCheckData;
	private String issuer;

	private String merchantAccountId;

	private FastCredit fastCredit;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public String getId() {
		return id;
	}

	public Long getCreated() {
		return created;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public String getFirst6() {
		return first6;
	}

	public String getLast4() {
		return last4;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public CardBrand getBrand() {
		return CardBrand.fromValue(brand);
	}

	public String getBrandAsString() {
		return brand;
	}

	public CardType getType() {
		return CardType.fromValue(type);
	}

	public String getTypeAsString() {
		return type;
	}

	public String getCountry() {
		return country;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public FraudCheckData getFraudCheckData() {
		return fraudCheckData;
	}

	public String getIssuer() {
		return issuer;
	}

	public String getMerchantAccountId() {
		return merchantAccountId;
	}

	public FastCredit getFastCredit() {
		return fastCredit;
	}

	public String get(String name) {
		if ("merchantAccountId".equals(name)) {
			return merchantAccountId;
		}

		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
