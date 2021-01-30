package com.alodiga.hsm.response;

public class CheckDigitValueResponse extends GeneralResponse {

	private String value;

	public CheckDigitValueResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.value = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
