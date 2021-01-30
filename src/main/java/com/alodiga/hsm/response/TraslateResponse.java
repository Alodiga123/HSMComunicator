package com.alodiga.hsm.response;

public class TraslateResponse extends GeneralResponse {

	private String traslateResponse;

	public TraslateResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.traslateResponse = name;
	}

	public String getTraslateResponse() {
		return traslateResponse;
	}

	public void setTraslateResponse(String traslateResponse) {
		this.traslateResponse = traslateResponse;
	}


}
