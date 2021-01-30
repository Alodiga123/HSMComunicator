package com.alodiga.hsm.response;

public class IBMOfSetResponse extends GeneralResponse {

	private String IBMoffset;

	public IBMOfSetResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.IBMoffset = name;
	}

	public String getIBMoffset() {
		return IBMoffset;
	}

	public void setIBMoffset(String iBMoffset) {
		IBMoffset = iBMoffset;
	}

}
