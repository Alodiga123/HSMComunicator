package com.alodiga.hsm.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantResponse {


	
	public static final Map<String, String> MESAGE_RESPONSE =  new HashMap<String,String>();
	

	public static final String SUCESSFULL_RESPONSE_CODE = "00";
	public static final String SUCESSFULL_RESPONSE_MESSAGE = "SUCCESSFULL";
	
	public static final String ERROR_RESPONSE_CODE = "99";
	public static final String ERROR_RESPONSE_MESSAGE = "ERROR GENERAL";
	
	
	public static final String HSM_NOT_AVAILABLE_ERROR_RESPONSE_CODE = "96";
	public static final String HSM_NOT_AVAILABLE_RROR_RESPONSE_MESSAGE = "HSM NOT AVAILABLE";
	
	public static final String FORMAT_ERROR_RESPONSE_CODE = "97";
	public static final String FORMAT_ERROR_RESPONSE_MESSAGE = "ERROR DE FORMATO";
	
	public static final String DUCUMENT_NUMBER_NOT_FOUND_CODE = "1001";
	public static final String DUCUMENT_NUMBER_NOT_FOUND_MESSAGE  = "DOCUMENT_NUMBER_NOT_FOUND";
	
	
	
	
	//MESAGGE EXCEPTION
	public static final String NOT_RESPONSE_HSM  = "NO RECEIVER RESPONSE FROM HSM";
	
	

	
}
