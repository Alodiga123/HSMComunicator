package com.alodiga.hsm.response;

public class GenerateKeyResponse extends GeneralResponse {

	private String verificationDigit;
	private String header;
	private String keyValue;
	
	
	
	

	public GenerateKeyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerateKeyResponse(String responseCode,String responseMessage,String response,String lenghtKey) {
		super();
		
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.verificationDigit = response.substring(response.length()-8);
		this.header = response.substring(0,4) ;
	      if (lenghtKey.equals("Single")) {
	    	  this.keyValue = response.substring(response.length()-24,response.length()-8);
	      }
	      if (lenghtKey.equals("Double")) {
	    	  this.keyValue = response.substring(response.length()-40,response.length()-8);
	      }

	      if (lenghtKey.equals("Triple")) {
	    	  this.keyValue = response.substring(response.length()-57,response.length()-8);
	      }

	}
	
	public GenerateKeyResponse(String responseCode,String responseMessage) {
		super();

		this.responseCode = responseCode;
		this.responseMessage = responseMessage;

	}


	public String getVerificationDigit() {
		return verificationDigit;
	}

	public void setVerificationDigit(String verificationDigit) {
		this.verificationDigit = verificationDigit;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public String toString() {
		return "GenerateKeyResponse [verificationDigit=" + verificationDigit + ", header=" + header + ", keyValue="
				+ keyValue + "]";
	}



}
