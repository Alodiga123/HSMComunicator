package com.alodiga.hsm.services;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alodiga.hsm.OmniCryptoCommand;
import com.alodiga.hsm.exception.NotConnectionHSMException;
import com.alodiga.hsm.response.ARPCResponse;
import com.alodiga.hsm.response.CheckDigitValueResponse;
import com.alodiga.hsm.response.GenericResponse;
import com.alodiga.hsm.response.HSMStatusResponse;
import com.alodiga.hsm.response.IBMOfSetResponse;
import com.alodiga.hsm.response.TraslateResponse;
import com.alodiga.hsm.response.VeirfyPinUsingIBMMethodResponse;
import com.alodiga.hsm.response.VeirfyPinUsingVISAMethodResponse;
import com.alodiga.hsm.response.VisaVerifyCvvResponse;
import com.alodiga.hsm.util.Constant;
import com.alodiga.hsm.util.ConstantResponse;



@RestController
public class HSMComunicatorAPI {
	
	
	@RequestMapping(value="/getHSMFirmware", method=RequestMethod.POST)
    public GenericResponse getHsmFinware(){
		String firmwareResponse = "";
		try {
			firmwareResponse = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.GET_HSMFIRMWARE_OPERATOR), null); 			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new GenericResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		}catch (NotConnectionHSMException e) {
			e.printStackTrace();
			return new GenericResponse(ConstantResponse.HSM_NOT_AVAILABLE_ERROR_RESPONSE_CODE,ConstantResponse.HSM_NOT_AVAILABLE_RROR_RESPONSE_MESSAGE,"");
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new GenericResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,firmwareResponse.toString().trim());
    }
	
	@RequestMapping(value="/getHSMStatus", method=RequestMethod.POST)
    public HSMStatusResponse getHSMStatus(){
		String hsmStatusResponse = "";
		try {
			hsmStatusResponse = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.GET_HSM_STATUS_OPERATOR), null); 			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new HSMStatusResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			return new HSMStatusResponse(ConstantResponse.HSM_NOT_AVAILABLE_ERROR_RESPONSE_CODE,ConstantResponse.HSM_NOT_AVAILABLE_RROR_RESPONSE_MESSAGE,"");
		}catch (Exception e) {
			e.printStackTrace();
			return new HSMStatusResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new HSMStatusResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,hsmStatusResponse.toString().trim());
    }
	
	@RequestMapping(value="/validateQRQC", method=RequestMethod.POST)
    public ARPCResponse validateQRQC(
    		@RequestParam(required = true) String institutionId,
    		@RequestParam(required = true) String oPMode,
    		@RequestParam(required = true) String schemeEMV,
    		@RequestParam(required = true) String pan,
    		@RequestParam(required = true) String seqNumber,
    		@RequestParam(required = true) String atc,
    		@RequestParam(required = true) String unpredictableNumber,
    		@RequestParam(required = true) String transactionData,
    		@RequestParam(required = true) String arqc){
		StringBuilder requestHSM = new StringBuilder();
		try {
			
			requestHSM.append(institutionId);
			requestHSM.append(",");
			requestHSM.append(oPMode);
			requestHSM.append(",");
			requestHSM.append(schemeEMV);
			requestHSM.append(",");
			requestHSM.append(pan);
			requestHSM.append(",");
			requestHSM.append(seqNumber);
			requestHSM.append(",");
			requestHSM.append(atc);
			requestHSM.append(",");
			requestHSM.append(unpredictableNumber);
			requestHSM.append(",");
			requestHSM.append(transactionData);
			requestHSM.append(",");
			requestHSM.append(arqc);

			//TODO: Implementar según especificación que haga llegar alvaro
			//firmwareResponse = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.VALIDATE_ARQC), requestHSM.toString()); 			

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ARPCResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new ARPCResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new ARPCResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,requestHSM.toString());
    }
	
	
	
	@RequestMapping(value="/verifyVisaCVV", method=RequestMethod.POST)
    public VisaVerifyCvvResponse verifyVisaCVV(
    		@RequestParam(required = true) String cvv,
    		@RequestParam(required = true) String pan,
    		@RequestParam(required = true) String cvvType,
    		@RequestParam(required = true) String expirationDate,
    		@RequestParam(required = true) String servicesCode,
    		@RequestParam(required = true) String institutionId){
		StringBuilder requestHSM = new StringBuilder();
		try {
			
			requestHSM.append(cvv);
			requestHSM.append(",");
			requestHSM.append(pan);
			requestHSM.append(",");
			requestHSM.append(cvvType);
			requestHSM.append(",");
			requestHSM.append(expirationDate);
			requestHSM.append(",");
			requestHSM.append(servicesCode);
			requestHSM.append(",");
			requestHSM.append(institutionId);
			
			
			//TODO:PREGUNTAR A ALVARO POR QUE BUSCA PEGARSE A LA BASE DE DATOS
			//String response  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.VERIFY_VISA_CVV), requestHSM.toString()); 			

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new VisaVerifyCvvResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new VisaVerifyCvvResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new VisaVerifyCvvResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,requestHSM.toString());
    }
	
	
	@RequestMapping(value="/generateIBMPinOffSet", method=RequestMethod.POST)
    public IBMOfSetResponse generateIBMPinOffSet(
    		@RequestParam(required = true) String pinELMK,
    		@RequestParam(required = true) String pan,
    		@RequestParam(required = true) String institutionId,
    		@RequestParam(required = true) String typeOffProduct){
		StringBuilder requestHSM = new StringBuilder();
		String offSetResponse;
		try {
			requestHSM.append(pinELMK);
			requestHSM.append(",");
			requestHSM.append(pan);
			requestHSM.append(",");
			requestHSM.append(institutionId);
			requestHSM.append(",");
			requestHSM.append(typeOffProduct);
			//TODO:Comentar a Alvaro que falta una base de datos
			 offSetResponse  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.GENERATE_IBM_PIN_OFF_SET), requestHSM.toString()); 			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new IBMOfSetResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new IBMOfSetResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new IBMOfSetResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,"0570");
    }
	
	@RequestMapping(value="/translatePINFromKWPtoMFK", method=RequestMethod.POST)
    public TraslateResponse translatePINFromKWPtoMFK(
    		@RequestParam(required = true) String terminalId,
    		@RequestParam(required = true) String pinBlock,
    		@RequestParam(required = true) String pan){
		StringBuilder requestHSM = new StringBuilder();
		String traslateResponse;
		try {
			
			requestHSM.append(terminalId);
			requestHSM.append(",");
			requestHSM.append(pinBlock);
			requestHSM.append(",");
			requestHSM.append(pan);

			traslateResponse  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.TRASLATE_PIN_FROM_KWP_TO_MFK), requestHSM.toString()); 			

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new TraslateResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new TraslateResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new TraslateResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,"141234FFFFFFFFFF");
    }
	
	
	@RequestMapping(value="/generateVISAOffSet", method=RequestMethod.POST)
    public VisaVerifyCvvResponse generateVISAOffSet(
    		@RequestParam(required = true) String pin,
    		@RequestParam(required = true) String pan){
		StringBuilder requestHSM = new StringBuilder();
		String offSetResponse;
		try {
			requestHSM.append("10");
			requestHSM.append(",");
			requestHSM.append(pin);
			requestHSM.append(",");
			requestHSM.append(pan);
			requestHSM.append(",");
			
			//TODO:Comentar a Alvaro que falta una base de datos
			//offSetResponse  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.GENERATE_VISA_OFF_SET), requestHSM.toString()); 			

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new VisaVerifyCvvResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new VisaVerifyCvvResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new VisaVerifyCvvResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,"0570");
    }
	
	
	@RequestMapping(value="/generateCheckDigitValue", method=RequestMethod.POST)
    public CheckDigitValueResponse generateCheckDigitValue(
    		@RequestParam(required = true) String terminalId){
		StringBuilder requestHSM = new StringBuilder();
		String checkdigit = "37";
		try {
			requestHSM.append("14");
			requestHSM.append(",");
			requestHSM.append(terminalId);
			//TODO:Comentar a Alvaro que falta una base de datos
			//checkdigit  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.GENERATE_A_KEY_CHECK_VERIFICATION), requestHSM.toString()); 			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new CheckDigitValueResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new CheckDigitValueResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new CheckDigitValueResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,checkdigit);
    }
	
	
	@RequestMapping(value="/verifyPINUsingVISAMethod", method=RequestMethod.POST)
    public VeirfyPinUsingVISAMethodResponse verifyPINUsingVISAMethod(
    		@RequestParam(required = true) String terminalId,
    		@RequestParam(required = true) String pinBlock,
    		@RequestParam(required = true) String pan,
    		@RequestParam(required = true) String Offset
    		){
		StringBuilder requestHSM = new StringBuilder();
		String resultValidatePin = "37";
		try {
			requestHSM.append("07");
			requestHSM.append(",");
			requestHSM.append(terminalId);
			requestHSM.append(",");
			requestHSM.append(pinBlock);
			requestHSM.append(",");
			requestHSM.append(pan);
			requestHSM.append(",");
			requestHSM.append(Offset);
			//TODO:Comentar a Alvaro que falta una base de datos
			//resultValidatePin  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.VERIFY_PIN_USING_VISA_METHOD), requestHSM.toString());
			resultValidatePin = "00";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new VeirfyPinUsingVISAMethodResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new VeirfyPinUsingVISAMethodResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new VeirfyPinUsingVISAMethodResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,resultValidatePin);
    }
	
	
	@RequestMapping(value="/verifyPINUsingIBMMethod", method=RequestMethod.POST)
    public VeirfyPinUsingIBMMethodResponse verifyPINUsingIBMMethod(
    		@RequestParam(required = true) String terminalId,
    		@RequestParam(required = true) String pinBlock,
    		@RequestParam(required = true) String pan,
    		@RequestParam(required = true) String Offset
    		){
		StringBuilder requestHSM = new StringBuilder();
		String resultValidatePin = "37";
		try {
			requestHSM.append("06");
			requestHSM.append(",");
			requestHSM.append(terminalId);
			requestHSM.append(",");
			requestHSM.append(pinBlock);
			requestHSM.append(",");
			requestHSM.append(pan);
			requestHSM.append(",");
			requestHSM.append(Offset);
			//TODO:Comentar a Alvaro que falta una base de datos
			//resultValidatePin  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.VERIFY_PIN_USING_IBM_METHOD), requestHSM.toString());
			resultValidatePin = "00";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new VeirfyPinUsingIBMMethodResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new VeirfyPinUsingIBMMethodResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new VeirfyPinUsingIBMMethodResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,resultValidatePin);
    }
	
	
	@RequestMapping(value="/generateKey", method=RequestMethod.POST)
    public VeirfyPinUsingIBMMethodResponse generateKey(
    		@RequestParam(required = true) String keyLong,
    		@RequestParam(required = true) String TypeKey){
		StringBuilder requestHSM = new StringBuilder();
		String resultValidatePin = "37";
		try {
			requestHSM.append("03");
			requestHSM.append(",");
			requestHSM.append(keyLong);
			requestHSM.append(",");
			requestHSM.append(TypeKey);

			//TODO:Comentar a Alvaro que falta una base de datos
			resultValidatePin  = OmniCryptoCommand.ProcessRequest(Integer.parseInt(Constant.GENERATE_KEY), requestHSM.toString());

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new VeirfyPinUsingIBMMethodResponse(ConstantResponse.FORMAT_ERROR_RESPONSE_CODE,ConstantResponse.FORMAT_ERROR_RESPONSE_MESSAGE,null);	
		} catch (Exception e) {
			e.printStackTrace();
			return new VeirfyPinUsingIBMMethodResponse(ConstantResponse.ERROR_RESPONSE_CODE,ConstantResponse.ERROR_RESPONSE_MESSAGE,null);
		}
        return new VeirfyPinUsingIBMMethodResponse(ConstantResponse.SUCESSFULL_RESPONSE_CODE,ConstantResponse.SUCESSFULL_RESPONSE_MESSAGE,resultValidatePin);
    }
	
	
	

}