package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.shift4.enums.RefundReason;
import com.shift4.response.Charge;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class RefundRequest {

	private String chargeId;

	private Integer amount;

	private RefundReason reason;

	private Map<String, String> metadata;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public RefundRequest() {
	}

	public RefundRequest(String chargeId) {
		chargeId(chargeId);
	}

	public RefundRequest(Charge charge) {
		charge(charge);
	}

	public String getChargeId() {
		return chargeId;
	}

	public Integer getAmount() {
		return amount;
	}

	public RefundReason getReason() {
		return reason;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public RefundRequest chargeId(String chargeId) {
		this.chargeId = chargeId;
		return this;
	}

	public RefundRequest charge(Charge charge) {
		return chargeId(charge.getId());
	}

	public RefundRequest amount(Integer amount) {
		this.amount = amount;
		return this;
	}

	public RefundRequest reason(RefundReason reason) {
		this.reason = reason;
		return this;
	}

	public RefundRequest metadata(Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	@JsonAnyGetter
	private Map<String, Object> getOtherMap() {
		return other;
	}

	@JsonAnySetter
	public RefundRequest set(String name, Object value) {
		other.put(name, value);
		return this;
	}
}
