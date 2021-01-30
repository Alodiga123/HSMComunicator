package com.alodiga.hsm.response;

public class ARPCResponse extends GeneralResponse {

	private String arpc;

	public ARPCResponse(String responseCode,String responseMessage, String name) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.arpc = name;
	}

	public String getArpc() {
		return arpc;
	}

	public void setArpc(String arpc) {
		this.arpc = arpc;
	}

	

}
