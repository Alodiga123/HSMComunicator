package com.alodiga.hsm.response;

public class VisaVerifyCvvResponse extends GeneralResponse {

	private String visaVerifyCvv;

	public VisaVerifyCvvResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.visaVerifyCvv = name;
	}

	public String getVisaVerifyCvv() {
		return visaVerifyCvv;
	}

	public void setVisaVerifyCvv(String visaVerifyCvv) {
		this.visaVerifyCvv = visaVerifyCvv;
	}
}
