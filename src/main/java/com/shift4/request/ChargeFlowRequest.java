package com.shift4.request;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargeFlowRequest {
	private String returnUrl;

	public String getReturnUrl() {
		return returnUrl;
	}

	public ChargeFlowRequest returnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
		return this;
	}
}
