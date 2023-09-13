package com.shift4.response;

public final class AvsCheck {
	private String result;

	public AvsCheckResult getResult() {
		return AvsCheckResult.fromValue(result);
	}
}