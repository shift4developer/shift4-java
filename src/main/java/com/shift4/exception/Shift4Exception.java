package com.shift4.exception;

import com.shift4.enums.ErrorCode;
import com.shift4.enums.ErrorType;
import com.shift4.response.ErrorResponse;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class Shift4Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String type;
	private final String code;
	private final String issuerDeclineCode;
	private final String chargeId;
	private final String creditId;
	private final String blacklistRuleId;
	private final String alertRuleId;
	private final String alertId;
	private final Map<String, Object> other;

	public Shift4Exception(
			String message, String type, String code, String issuerDeclineCode,
			String chargeId, String creditId, String blacklistRuleId, String alertRuleId, String alertId,
			Map<String, Object> other
	) {
		super(message);

		this.type = type;
		this.code = code;
		this.issuerDeclineCode = issuerDeclineCode;
		this.chargeId = chargeId;
		this.creditId = creditId;
		this.blacklistRuleId = blacklistRuleId;
		this.alertRuleId = alertRuleId;
		this.alertId = alertId;
		this.other = new HashMap<>(other);
	}

	public Shift4Exception(ErrorResponse error) {
		this(
				error.getMessage(), error.getTypeAsString(), error.getCodeAsString(), error.getIssuerDeclineCode(),
				error.getChargeId(), error.getCreditId(), error.getBlacklistRuleId(), error.getAlertRuleId(), error.getAlertId(),
				error.getOther()
		);
	}


	public ErrorType getType() {
		return ErrorType.fromValue(type);
	}

	public String getTypeAsString() {
		return type;
	}

	public ErrorCode getCode() {
		return ErrorCode.fromValue(code);
	}

	public String getCodeAsString() {
		return code;
	}

	public String getIssuerDeclineCode() {
		return issuerDeclineCode;
	}

	public String getChargeId() {
		return chargeId;
	}

	public String getCreditId() {
		return creditId;
	}

	public String getBlacklistRuleId() {
		return blacklistRuleId;
	}

	public String getAlertRuleId() {
		return alertRuleId;
	}

	public String getAlertId() {
		return alertId;
	}

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}
}
