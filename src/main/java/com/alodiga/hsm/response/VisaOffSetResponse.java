package com.alodiga.hsm.response;

public class VisaOffSetResponse extends GeneralResponse {

	private String visaVerifyCvv;

	public VisaOffSetResponse(String responseCode,String responseMessage, String name) {
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
