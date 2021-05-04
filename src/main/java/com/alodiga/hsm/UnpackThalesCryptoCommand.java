/*     */
package com.alodiga.hsm;

/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;

import com.alodiga.hsm.exception.NotConnectionHSMException;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.UseParameters.HeaderThalesMsg;
import com.alodiga.hsm.util.Constant;
import com.alodiga.hsm.util.ConstantResponse;

/*     */ public class UnpackThalesCryptoCommand
/*     */ {
	/* 11 */ private static final String[] Commands = new String[50];
	/*     */ private static final String Separator = "#";
	/*     */
	/* 14 */ static {
				 Commands[0] = "B2";
		/* 15 */ Commands[1] = "CC";
		/* 16 */ Commands[2] = "JE";
		/* 17 */ Commands[3] = "CY";
		/* 18 */ Commands[4] = "A0";
		/* 19 */ Commands[5] = "A8";
		/* 20 */ Commands[6] = "A6";
		/* 21 */ Commands[7] = "EA";
		/* 22 */ Commands[8] = "EC";
		/* 23 */ Commands[9] = "KQ";
		/* 24 */ Commands[10] = "DE";
		/* 25 */ Commands[11] = "DG";
		/* 26 */ Commands[12] = "IA";
		/* 27 */ Commands[13] = "BU";
		/* 28 */ Commands[14] = "GC";
		/* 29 */ Commands[15] = "NO";
				 Commands[16] = "A0";
		/*     */
		/*     */
		/* 32 */ ErrorCodes = new String[50];
		/*     */
///*  34 */     ErrorCodes[0] = "00";
///*  35 */     ErrorCodes[1] = "10";
///*  36 */     ErrorCodes[2] = "7E";
///*  37 */     ErrorCodes[3] = "00";
///*  38 */     ErrorCodes[4] = "00";
///*  39 */     ErrorCodes[5] = "00";
		/*     */ }

	public static String unpackfirmwareCommand(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String mfkCheckvalue = null;
		String firmwareVersion = null;
		String respcommand = null;
		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);

		}

		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			mfkCheckvalue = restofMsg.substring(4, 20);
			firmwareVersion = restofMsg.substring(20);
			System.out.println(respcommand);
			System.out.println(responsecode);
			System.out.println(mfkCheckvalue);
			System.out.println(firmwareVersion);
			System.out.println("unpackCommand : " + commandResult);
		} else {
			firmwareVersion = unpackErrorResponse(responseCommand);
		}
		return firmwareVersion;
	}

	public static String unpackstatusCommand(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		try {
			String restofMsg = unpackMsgHeader(responseCommand);
			respcommand = restofMsg.substring(0, 2);
			responsecode = restofMsg.substring(2, 4);

			if (responsecode.equals("00")) {
				commandResult = restofMsg.substring(2, 4);
			} else {
				commandResult = unpackErrorResponse(responseCommand);
			}
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		return commandResult;
	}

	public static String unpackGenerateKey(String responseCommand) throws NotConnectionHSMException {
		String MsgHeader = null;
		String responsecode = null;
		String respcommand = null;
		String commandResult = null;
		String keyencryptedKEKResult = null;
		try {
			String restofMsg = unpackMsgHeader(responseCommand);
			respcommand = restofMsg.substring(0, 2);
			responsecode = restofMsg.substring(2, 4);
			if (responsecode.equals("00")) {
				commandResult = restofMsg.substring(4);
				System.out.println("unpackResultKey : " + commandResult);
			} else {
				commandResult = unpackErrorResponse(responseCommand);
			}
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);

		}
		return commandResult;
	}

	public static String unpacktransletePintoMFK(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;
		try {
			String restofMsg = unpackMsgHeader(responseCommand);
			respcommand = restofMsg.substring(0, 2);
			responsecode = restofMsg.substring(2, 4);

			if (responsecode.equals("00")) {
				commandResult = restofMsg.substring(4);
			} else {
				commandResult = unpackErrorResponse(responseCommand);
			}
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);

		}
		return commandResult;
	}

	public static String unpacktranslatePintoKWP(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String rspPinLenght = null;
		String rspdestpinblock = null;
		String rspdestpinblockformat = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}

		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			rspPinLenght = restofMsg.substring(4, 6);
			rspdestpinblock = restofMsg.substring(6, 22);
			rspdestpinblockformat = restofMsg.substring(22, 24);

			commandResult = rspPinLenght + "," + rspdestpinblock + "," + rspdestpinblockformat;
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}

	public static String unpackverifyVISACvv(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(4);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}

	public static String unpackverifyIBMPin(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
			respcommand = restofMsg.substring(0, 2);
			responsecode = restofMsg.substring(2, 4);
			if (responsecode.equals("00")) {
				commandResult = restofMsg.substring(2, 4);
			} else {
				commandResult = unpackErrorResponse(responseCommand);
			}
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}

		return commandResult;
	}

	public static String unpackverifyVISAPin(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(2, 4);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}

	public static String unpackgenerateIBMOffset(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(4);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}

	public static String unpackgenerateVISAOffset(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(4);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}

	public static String unpackpinMngt(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(4);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}

		return commandResult;
	}

	public static String unpackechoTestMessage(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);

		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(4);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}

	public static String unpackGenerateCheckDigit(String responseCommand) throws NotConnectionHSMException {
		String responsecode = null;
		String commandResult = null;
		String respcommand = null;

		String restofMsg;
		try {
			restofMsg = unpackMsgHeader(responseCommand);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		respcommand = restofMsg.substring(0, 2);
		responsecode = restofMsg.substring(2, 4);
		if (responsecode.equals("00")) {
			commandResult = restofMsg.substring(4, 8);
		} else {
			commandResult = unpackErrorResponse(responseCommand);
		}
		return commandResult;
	}
	
	public static String unpackGenerateAKeyCheckValue (String responseCommand) throws NotConnectionHSMException {
		String MsgHeader = null;
		String responsecode = null;
		String respcommand = null;
		String commandResult = null;
		String keyencryptedKEKResult = null;
		try {
			String restofMsg = unpackMsgHeader(responseCommand);
			respcommand = restofMsg.substring(0, 2);
			responsecode = restofMsg.substring(2, 4);
			if (responsecode.equals("00")) {
				commandResult = restofMsg;
				System.out.println("unpackResultKey : " + commandResult);
			} else {
				commandResult = unpackErrorResponse(responseCommand);
			}
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);

		}
		return commandResult;
	}


	private static final String[] ErrorCodes;

	public static String unpackMsgHeader(String responseCommand) throws NotConnectionHSMException {
		String restofMsg = null;
		String MsgHeader = null;
		String parameterHeader = null;
		try {
			UseParameters.getHeaderThalesMsg();
			parameterHeader = UseParameters.HeaderThalesMsg.headerthalesmsg();
			System.out.println("Header red from file " + parameterHeader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			MsgHeader = responseCommand.substring(0, Integer.parseInt(parameterHeader));
		} catch (NullPointerException e) {
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		System.out.println(MsgHeader);
		restofMsg = responseCommand.substring(Integer.parseInt(parameterHeader));
		System.out.println(restofMsg);

		return restofMsg;
	}

	/*     */ public static String unpackErrorResponse(String errorResponseCommnad) throws NotConnectionHSMException
	/*     */ {
		/* 331 */ String restofMsg;
		try {
			restofMsg = unpackMsgHeader(errorResponseCommnad);
		} catch (NotConnectionHSMException e) {
			e.printStackTrace();
			throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
		}
		/*     */
		/* 333 */ String InstructionCommand = null;
		String CommandResultXX = null;
		String CommandResultYY = null;
		/*     */
		/* 335 */ InstructionCommand = restofMsg.substring(0, 2);
		/* 336 */ CommandResultXX = restofMsg.substring(2, 4);
		/*     */
		/*     */
		/* 339 */ String FinalCommandResult = null;
		/*     */
		/* 341 */ if (CommandResultXX.equals("01"))
			/* 342 */ FinalCommandResult = "Verification failure or warning of imported key parity error";
		/* 343 */ if (CommandResultXX.equals("02")) {
			/* 344 */ FinalCommandResult = "Key inappropriate length for algorithm";
			/*     */ }
		/* 347 */ if (CommandResultXX.equals("04"))
			/* 348 */ FinalCommandResult = "Invalid key type code";
		/* 349 */ if (CommandResultXX.equals("05")) {
			/* 350 */ FinalCommandResult = "Invalid key length flag";
			/*     */ }
		/* 359 */ if (CommandResultXX.equals("10"))
			/* 360 */ FinalCommandResult = "Source key parity error";
		/* 361 */ if (CommandResultXX.equals("11"))
			/* 362 */ FinalCommandResult = "Destination key parity error or key all zeros";
		/* 363 */ if (CommandResultXX.equals("12"))
			/* 364 */ FinalCommandResult = "Contents of user storage not available. Reset, power-down or overwrite";
		/* 365 */ if (CommandResultXX.equals("13"))
			/* 366 */ FinalCommandResult = "Master Key parity error";
		/* 367 */ if (CommandResultXX.equals("14"))
			/* 368 */ FinalCommandResult = "PIN encrypted under LMK pair 02-03 is invalid";
		/* 369 */ if (CommandResultXX.equals("15"))
			/* 370 */ FinalCommandResult = "Invalid input data (invalid format, invalid characters, or not enough data provided)";
		/* 371 */ if (CommandResultXX.equals("16"))
			/* 372 */ FinalCommandResult = "Console or printer not ready or not connected";
		/* 373 */ if (CommandResultXX.equals("17"))
			/* 374 */ FinalCommandResult = "HSM not in the Authorised state, or not enabled for clear PIN output, or both";
		/* 375 */ if (CommandResultXX.equals("18"))
			/* 376 */ FinalCommandResult = "Document format definition not loaded";
		/* 377 */ if (CommandResultXX.equals("19"))
			/* 378 */ FinalCommandResult = "Specified Diebold Table is invalid.";
		/* 379 */ if (CommandResultXX.equals("20"))
			/* 380 */ FinalCommandResult = "PIN block does not contain valid values";
		/* 381 */ if (CommandResultXX.equals("21"))
			/* 382 */ FinalCommandResult = "Invalid index value, or index/block count would cause an overflow condition";
		/* 383 */ if (CommandResultXX.equals("22"))
			/* 384 */ FinalCommandResult = "Invalid account number";
		/* 385 */ if (CommandResultXX.equals("23"))
			/* 386 */ FinalCommandResult = "Invalid PIN block format code";
		/* 387 */ if (CommandResultXX.equals("24"))
			/* 388 */ FinalCommandResult = "PIN is fewer than 4 or more than 12 digits in length";
		/* 389 */ if (CommandResultXX.equals("25"))
			/* 390 */ FinalCommandResult = "Decimalisation table error";
		/* 391 */ if (CommandResultXX.equals("26"))
			/* 392 */ FinalCommandResult = "Invalid key scheme";
		/* 393 */ if (CommandResultXX.equals("27"))
			/* 394 */ FinalCommandResult = "Incompatible key length";
		/* 395 */ if (CommandResultXX.equals("28"))
			/* 396 */ FinalCommandResult = "Invalid key type";
		/* 397 */ if (CommandResultXX.equals("29"))
			/* 398 */ FinalCommandResult = "Key function not permitted";
		/* 399 */ if (CommandResultXX.equals("30"))
			/* 400 */ FinalCommandResult = "Invalid reference number";
		/* 401 */ if (CommandResultXX.equals("31"))
			/* 402 */ FinalCommandResult = "Insufficient solicitation entries for batch";
		/* 403 */ if (CommandResultXX.equals("33"))
			/* 404 */ FinalCommandResult = "LMK key change storage is corrupted";
		/* 405 */ if (CommandResultXX.equals("40"))
			/* 406 */ FinalCommandResult = "Invalid checksum";
		/* 407 */ if (CommandResultXX.equals("41"))
			/* 408 */ FinalCommandResult = "Internal hardware/software error: bad RAM, invalid error codes, etc";
		/* 409 */ if (CommandResultXX.equals("42"))
			/* 410 */ FinalCommandResult = "DES failure";
		/* 411 */ if (CommandResultXX.equals("49"))
			/* 412 */ FinalCommandResult = "Secret key error, report to supervisor";
		/* 413 */ if (CommandResultXX.equals("51"))
			/* 414 */ FinalCommandResult = "Invalid message header";
		/* 415 */ if (CommandResultXX.equals("65"))
			/* 416 */ FinalCommandResult = "Transaction Key Scheme set to None";
		/* 417 */ if (CommandResultXX.equals("67"))
			/* 418 */ FinalCommandResult = "Command not licenced";
		/* 419 */ if (CommandResultXX.equals("68"))
			/* 420 */ FinalCommandResult = "Command has been disabled";
		/* 421 */ if (CommandResultXX.equals("69"))
			/* 422 */ FinalCommandResult = "PIN block has been disabled";
		/* 423 */ if (CommandResultXX.equals("74"))
			/* 424 */ FinalCommandResult = "Invalid digest info syntax (no hash mode only)";
		/* 425 */ if (CommandResultXX.equals("75"))
			/* 426 */ FinalCommandResult = "Single length key masquerading as double or triple length key";
		/* 427 */ if (CommandResultXX.equals("77"))
			/* 428 */ FinalCommandResult = "Clear data block error";
		/* 429 */ if (CommandResultXX.equals("78"))
			/* 430 */ FinalCommandResult = "Secret key length error";
		/* 431 */ if (CommandResultXX.equals("79"))
			/* 432 */ FinalCommandResult = "Hash algorithm object identifier error";
		/* 433 */ if (CommandResultXX.equals("80"))
			/* 434 */ FinalCommandResult = "Data length error. The amount of MAC data (or other data) is greater than or less than the expected amount";
		/* 435 */ if (CommandResultXX.equals("81"))
			/* 436 */ FinalCommandResult = "Invalid certificate header";
		/* 437 */ if (CommandResultXX.equals("82"))
			/* 438 */ FinalCommandResult = "Invalid check value length";
		/* 439 */ if (CommandResultXX.equals("83"))
			/* 440 */ FinalCommandResult = "Key block format error";
		/* 441 */ if (CommandResultXX.equals("84"))
			/* 442 */ FinalCommandResult = "Key block check value error";
		/* 443 */ if (CommandResultXX.equals("85"))
			/* 444 */ FinalCommandResult = "Invalid OAEP Mask Generation Function";
		/* 445 */ if (CommandResultXX.equals("86"))
			/* 446 */ FinalCommandResult = "Invalid OAEP MGF Hash Function";
		/* 447 */ if (CommandResultXX.equals("87"))
			/* 448 */ FinalCommandResult = "OAEP Parameter Error";
		/* 449 */ if (CommandResultXX.equals("90"))
			/* 450 */ FinalCommandResult = "Data parity error in the request message received by the HSM.";
		/* 451 */ if (CommandResultXX.equals("91"))
			/* 452 */ FinalCommandResult = "Longitudinal Redundancy Check (LRC) character does not match the value computed over the input data (when the HSM has received a transparent async packet)";
		/* 453 */ if (CommandResultXX.equals("91"))
			/* 454 */ FinalCommandResult = "The Count value (for the Command/Data field) is not between limits, or is not correct(when the HSM has received a transparent async packet)";
		if (CommandResultXX.equals("92")) {
			FinalCommandResult = "The Count value (for the Command/Data field) is not between limits, or is not correct (when the HSM has received a transparent async packet)";
		}

		System.out.println("unpackCommand : " + CommandResultXX + "," + CommandResultYY);

		return CommandResultXX + "," + FinalCommandResult;
	}
}

/*
 * Location:
 * /home/usuario/Escritorio/OmniSocket.jar!/HSM/UnpackThalesCryptoCommand.class
 * Java compiler version: 7 (51.0) JD-Core Version: 0.7.1
 */