package com.shift4.response;

import com.shift4.enums.ChargeFlowActionType;

public class ChargeFlow {

	private ChargeFlowActionType nextAction;
	private Redirect redirect;
	private String returnUrl;
	private QrCode qrCode;

	public ChargeFlowActionType getNextAction() {
		return nextAction;
	}

	public Redirect getRedirect() {
		return redirect;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public QrCode getQrCode() {
		return qrCode;
	}

	public ChargeFlow nextAction(ChargeFlowActionType nextAction) {
		this.nextAction = nextAction;
		return this;
	}

	public ChargeFlow redirect(Redirect redirect) {
		this.redirect = redirect;
		return this;
	}

	public ChargeFlow returnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
		return this;
	}

	public ChargeFlow qrCode(QrCode qrCode) {
		this.qrCode = qrCode;
		return this;
	}

	public static class Redirect {
		private String redirectUrl;

		public String getRedirectUrl() {
			return redirectUrl;
		}

		public Redirect redirectUrl(String redirectUrl) {
			this.redirectUrl = redirectUrl;
			return this;
		}
	}

	public static class QrCode {
		private String imgSrc;

		public String getImgSrc() {
			return imgSrc;
		}

		public QrCode imgSrc(String imgSrc) {
			this.imgSrc = imgSrc;
			return this;
		}
	}
}
