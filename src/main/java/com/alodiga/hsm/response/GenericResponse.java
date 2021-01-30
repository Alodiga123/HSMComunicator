package com.alodiga.hsm.response;

public class GenericResponse extends GeneralResponse {

	private String firmware;

	public GenericResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.firmware = name;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
}
