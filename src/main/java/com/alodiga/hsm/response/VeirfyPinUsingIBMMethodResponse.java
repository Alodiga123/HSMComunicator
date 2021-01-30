package com.alodiga.hsm.response;

public class VeirfyPinUsingIBMMethodResponse extends GeneralResponse {

	private String verificationValue;

	public VeirfyPinUsingIBMMethodResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.verificationValue = name;
	}

	public String getVerificationValue() {
		return verificationValue;
	}

	public void setVerificationValue(String verificationValue) {
		this.verificationValue = verificationValue;
	}

}
