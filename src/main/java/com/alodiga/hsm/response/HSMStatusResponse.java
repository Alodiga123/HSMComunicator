package com.alodiga.hsm.response;

public class HSMStatusResponse extends GeneralResponse {

	private String status;

	public HSMStatusResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.status = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
