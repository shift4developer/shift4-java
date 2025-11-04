package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.ErrorCode;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class Credit {
	private String id;
	private Long created;
	private Integer amount;
	private String currency;
	private String description;
	private Card card;
	private String customerId;
	private ThreeDSecureInfo threeDSecureInfo;
	private Boolean fast;
	private Receiver receiver;
	private String merchantAccountId;
    private Boolean disputed;

	private Map<String, String> metadata;

	private String failureCode;
	private String failureIssuerDeclineCode;
	private String failureMessage;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public String getId() {
		return id;
	}

	public Long getCreated() {
		return created;
	}

	public Integer getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDescription() {
		return description;
	}

	public Card getCard() {
		return card;
	}

	public String getCustomerId() {
		return customerId;
	}

	public ThreeDSecureInfo getThreeDSecureInfo() {
		return threeDSecureInfo;
	}

	public Boolean getFast() {
		return fast;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public String getMerchantAccountId() {
		return merchantAccountId;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public ErrorCode getFailureCode() {
		return ErrorCode.fromValue(failureCode);
	}

	public String getFailureCodeAsString() {
		return failureCode;
	}

	public String getFailureIssuerDeclineCode() {
		return failureIssuerDeclineCode;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

    public Boolean getDisputed() {
        return disputed;
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
