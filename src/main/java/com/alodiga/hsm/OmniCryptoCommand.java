/*     */ package com.alodiga.hsm;
/*     */ import java.io.PrintStream;
import java.net.Socket;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;

import com.alodiga.hsm.data.ConnectDB;
import com.alodiga.hsm.data.ExecuteSP;
import com.alodiga.hsm.exception.NotConnectionHSMException;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.UseParameters.ParametersConfig;
import com.alodiga.hsm.util.ConstantResponse;

/*     */ 
/*     */ public class OmniCryptoCommand
/*     */ {
/*  30 */   public static Connection conn = null;
/*  31 */   private static final String[] Commands = new String[50];
/*     */   
/*     */   static {
/*  34 */     Commands[0] = "JE";
/*  35 */     Commands[1] = "CC";
/*  36 */     Commands[2] = "CY";
/*  37 */     Commands[3] = "A0";
/*  38 */     Commands[4] = "A8";
/*  39 */     Commands[5] = "A6";
/*  40 */     Commands[6] = "EA";
/*  41 */     Commands[7] = "EC";
/*  42 */     Commands[8] = "KQ";
/*  43 */     Commands[9] = "DE";
/*  44 */     Commands[10] = "DG";
/*  45 */     Commands[11] = "IA";
/*  46 */     Commands[12] = "GC";
/*  47 */     Commands[13] = "B2";
/*  48 */     Commands[14] = "BU";
/*  49 */     Commands[15] = "NO";
/*  50 */     Commands[16] = "NC";
/*     */   }
/*     */   
/*     */   public static String ProcessRequest(int command, String requestmsg)
/*     */     throws Exception
/*     */   {
/*  56 */     String requestMessage = null;String responseMessage = null;
/*  57 */     switch (command)
/*     */     {
/*     */     case 0: 
/*  60 */       ConnectServer();
/*  62 */       String[] reqTransMFKSplit = requestmsg.split(",");
/*  63 */       String reqTransMFKTerminalID = "KWP_" + reqTransMFKSplit[1];
/*  64 */       String reqTransMFKPinBlock = reqTransMFKSplit[2];
/*  65 */       String reqTransMFKPan = reqTransMFKSplit[3];
/*     */       
/*  67 */       String[] dbInfoCryptoTransMfk = ExecuteSP.execGetCryptoForTranslatetoMFK(reqTransMFKTerminalID, conn).split(",");
/*  68 */       String dbcryptoTransMfk = dbInfoCryptoTransMfk[0];
/*  69 */       String dbcryptoLenghtTransMfk = dbInfoCryptoTransMfk[1];
/*  70 */       String dbpinBlockFormatTransMfk = dbInfoCryptoTransMfk[2];
/*  71 */       int dbAccntOffsetTransMfk = Integer.parseInt(dbInfoCryptoTransMfk[3]);
/*     */       
/*  73 */       String tAccountNumberTransMfk = reqTransMFKPan.substring(dbAccntOffsetTransMfk, dbAccntOffsetTransMfk + 12);
/*     */       
/*  75 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.transletePintoMFK(dbcryptoLenghtTransMfk, dbcryptoTransMfk, dbpinBlockFormatTransMfk, reqTransMFKPinBlock, tAccountNumberTransMfk));
/*  76 */       responseMessage = UnpackThalesCryptoCommand.unpacktransletePintoMFK(requestMessage);
/*     */       
/*  78 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/*  80 */       CloseConnection();
/*     */       
/*  82 */       break;
/*     */     
/*     */ 
/*     */     case 1: 
/*  86 */       ConnectServer();
/*     */       
/*  88 */       String[] reqTranslateSplit = requestmsg.split(",");
/*  89 */       String reqTerminalIDOrigen = "KWP_" + reqTranslateSplit[1];
/*  90 */       String reqTerminalIDDestino = "KWP_" + reqTranslateSplit[2];
/*  91 */       String reqTransPinBlock = reqTranslateSplit[3];
/*     */       
/*  93 */       String reqTranpinLenght = "12";
/*  94 */       String reqTransPan = reqTranslateSplit[5];
/*     */       
/*  96 */       String[] dbInfoCryptoTrans = ExecuteSP.execGetCryptoForTranslatetoKWP(reqTerminalIDOrigen, reqTerminalIDDestino, conn).split(",");
/*  97 */       String dbcryptoOrg = dbInfoCryptoTrans[0];
/*  98 */       String dbcryptoDest = dbInfoCryptoTrans[1];
/*  99 */       String dbcryptoLenghtOrg = dbInfoCryptoTrans[2];
/* 100 */       String dbcryptoLenghtDest = dbInfoCryptoTrans[3];
/* 101 */       String dbpinBlockOrg = dbInfoCryptoTrans[4];
/* 102 */       String dbpinBlockDest = dbInfoCryptoTrans[5];
/* 103 */       int dbAccntOffset = Integer.parseInt(dbInfoCryptoTrans[6]);
/*     */       
/* 105 */       String tAccountNumber = reqTransPan.substring(dbAccntOffset, dbAccntOffset + 12);
/*     */       
/* 107 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.translatePintoKWP(dbcryptoLenghtOrg, dbcryptoOrg, dbcryptoLenghtDest, dbcryptoDest, reqTranpinLenght, 
/* 108 */         dbpinBlockOrg, dbpinBlockDest, reqTransPinBlock, tAccountNumber));
/* 109 */       responseMessage = UnpackThalesCryptoCommand.unpacktranslatePintoKWP(requestMessage);
/*     */       
/* 111 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 113 */       CloseConnection();
/*     */       
/* 115 */       break;
/*     */     
/*     */     case 2: 
/* 118 */       ConnectServer();
/*     */       
/* 120 */       String[] reqVcvvSplit = requestmsg.split(",");
/* 121 */       String reqVcvv = reqVcvvSplit[1];
/* 122 */       String reqVcvvPan = reqVcvvSplit[2];
/* 123 */       String reqTypeOfCVV = reqVcvvSplit[3];
/* 124 */       String reqVcvvExpDate = reqVcvvSplit[4];
/* 125 */       String reqVcvvServiceCode = reqVcvvSplit[5];
/*     */       
/* 127 */       String[] dbInfoVcvvCrypto = ExecuteSP.execGetCryptoVisaCVV(reqTypeOfCVV, conn).split(",");
/* 128 */       String dbVcvvCrypto = dbInfoVcvvCrypto[0];
/* 129 */       String dbVcvvExpDateFormat = dbInfoVcvvCrypto[1];
/* 130 */       int dbVcvvAcctNmbOffset = Integer.parseInt(dbInfoVcvvCrypto[2]);
/*     */       
/* 132 */       String vCvvaccountNumber = reqVcvvPan.substring(dbVcvvAcctNmbOffset, dbVcvvAcctNmbOffset + 12);
/*     */       
/* 134 */       String expdatefinal = null;
/* 135 */       if (dbVcvvExpDateFormat.equals("MMYY")) {
/* 136 */         String expdatepart1 = reqVcvvExpDate.substring(0, 2);
/* 137 */         String expdatepart2 = reqVcvvExpDate.substring(2);
/* 138 */         expdatefinal = expdatepart2 + expdatepart1;
/*     */       }
/*     */       
/*     */ 
/* 142 */       if (reqTypeOfCVV.equals("3")) {
/* 143 */         reqVcvvServiceCode = "999";
/*     */       }
/*     */       
/* 146 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.verifyVISACvv(dbVcvvCrypto, reqVcvv, expdatefinal, reqVcvvServiceCode, reqVcvvServiceCode));
/* 147 */       responseMessage = UnpackThalesCryptoCommand.unpackverifyVISACvv(requestMessage);
/*     */       
/* 149 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 151 */       CloseConnection();
/*     */       
/* 153 */       break;
/*     */     
/*     */ 
/*     */     case 3: 
/* 157 */       String[] reqGkeySplit = requestmsg.split(",");
/* 158 */       String reqGkeyLenght = reqGkeySplit[1];
/* 159 */       String reqGkeyKeytype = reqGkeySplit[2];
/*     */       
/* 161 */       if ((reqGkeyKeytype.equals("KEK")) || (reqGkeyKeytype.equals("TPK")) || (reqGkeyKeytype.equals("KWP")) || (reqGkeyKeytype.equals("PVK")) || 
/* 162 */         (reqGkeyKeytype.equals("CAK")) || (reqGkeyKeytype.equals("CVK")))
/*     */       {
/*     */ 
/* 165 */         requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateKey(reqGkeyLenght, reqGkeyKeytype));
/* 166 */         responseMessage = UnpackThalesCryptoCommand.unpackGenerateKey(requestMessage);
/* 167 */         System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       }
/*     */       else
/*     */       {
/* 171 */         responseMessage = "Key Type Not Supported";
/* 172 */         System.out.println("Mensaje de Respuesta: " + responseMessage); }
/* 173 */       break;

/*     */     case 6: 
/* 196 */       ConnectServer();
/*     */       
/* 198 */       String[] reqIPinSplit = requestmsg.split(",");
/* 199 */       String reqIPinTerminalId = "KWP_" + reqIPinSplit[1];
/* 200 */       String ipinBlock = reqIPinSplit[2];
/* 201 */       String iPan = reqIPinSplit[3];
/* 202 */       String iOffset = reqIPinSplit[4];
/*     */       
/* 204 */       String[] dbInfoIPinCrypto = ExecuteSP.execGetCryptoFromTerminalID(reqIPinTerminalId, conn).split(",");
/* 205 */       String dbIPincryptoLenght = dbInfoIPinCrypto[0];
/* 206 */       String dbIPinCryptogram = dbInfoIPinCrypto[1];
/*     */       
/* 208 */       String[] dbInfoIPinPVKCrypto = ExecuteSP.execGetCryptoPvkIBM(conn).split(",");
/* 209 */       String cryptoIPinPVK = dbInfoIPinPVKCrypto[0];
/* 210 */       int iPinacctnmboffset = Integer.parseInt(dbInfoIPinPVKCrypto[1]);
/* 211 */       String cryptoIPinPinBlockFormat = dbInfoIPinPVKCrypto[2];
/* 212 */       String cryptoIPinMinLenght = dbInfoIPinPVKCrypto[3];
/* 213 */       String cryptoIPinMaxLenght = dbInfoIPinPVKCrypto[4];
/*     */       
/* 215 */       String iPinaccountNumber = iPan.substring(iPinacctnmboffset, iPinacctnmboffset + 12);
/*     */       
/* 217 */       String pinValidationDatapart1 = iPan.substring(0, 10);
/* 218 */       String pinValidationDatapart2 = iPan.substring(iPan.length() - 1);
/* 219 */       String pinValidationData = pinValidationDatapart1 + "N" + pinValidationDatapart2;
/*     */       
/* 221 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.verifyIBMPin(dbIPincryptoLenght, dbIPinCryptogram, cryptoIPinPVK, cryptoIPinMaxLenght, cryptoIPinMinLenght, 
/* 222 */         cryptoIPinPinBlockFormat, ipinBlock, iPinaccountNumber, "0123456789012345", pinValidationData, iOffset));
/* 223 */       responseMessage = UnpackThalesCryptoCommand.unpackverifyIBMPin(requestMessage);
/*     */       
/* 225 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 227 */       CloseConnection();
/*     */       
/* 229 */       break;
/*     */     
/*     */ 
/*     */     case 7: 
/* 233 */       ConnectServer();
/*     */       
/* 235 */       String[] reqVPinSplit = requestmsg.split(",");
/* 236 */       String reqVPinTerminalId = "KWP_" + reqVPinSplit[1];
/* 237 */       String pinBlock = reqVPinSplit[2];
/* 238 */       String vPan = reqVPinSplit[3];
/* 239 */       String vOffset = reqVPinSplit[4];
/*     */       
/* 241 */       String[] dbInfoVPinCrypto = ExecuteSP.execGetCryptoFromTerminalID(reqVPinTerminalId, conn).split(",");
/* 242 */       String dbvPincryptoLenght = dbInfoVPinCrypto[0];
/* 243 */       String dbvPinCryptogram = dbInfoVPinCrypto[1];
/*     */       
/* 245 */       String[] dbInfoVPinPVKCrypto = ExecuteSP.execGetCryptoPvkVisa(conn).split(",");
/* 246 */       String cryptoVPinPVK = dbInfoVPinPVKCrypto[0];
/* 247 */       String cryptoVPinPvki = dbInfoVPinPVKCrypto[1];
/* 248 */       int vPinacctnmboffset = Integer.parseInt(dbInfoVPinPVKCrypto[2]);
/* 249 */       String cryptoVPinPinBlockFormat = dbInfoVPinPVKCrypto[3];
/*     */       
/* 251 */       String vPinaccountNumber = vPan.substring(vPinacctnmboffset, vPinacctnmboffset + 12);
/*     */       
/* 253 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.verifyVISAPin(dbvPincryptoLenght, dbvPinCryptogram, cryptoVPinPVK, cryptoVPinPvki, vOffset, cryptoVPinPinBlockFormat, pinBlock, vPinaccountNumber));
/* 254 */       responseMessage = UnpackThalesCryptoCommand.unpackverifyVISAPin(requestMessage);
/*     */       
/* 256 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 258 */       CloseConnection();
/*     */       
/* 260 */       break;

/*     */     case 9: 
/* 271 */       ConnectServer();       
/* 273 */       String[] reqOffIBMSplit = requestmsg.split(",");
/* 274 */       String pinIbmUnderLMK = reqOffIBMSplit[1];
/* 275 */       String panIbmNumb = reqOffIBMSplit[2];
/*     */       
/* 277 */       String[] dbInfoIbmPvkCrypto = ExecuteSP.execGetCryptoPvkIBM(conn).split(",");
/* 278 */       String cryptoIbmPVK = dbInfoIbmPvkCrypto[0];
/* 279 */       String cryptoIbmPvkLenght = dbInfoIbmPvkCrypto[1];
/* 280 */       String cryptoIbmminPinLenght = dbInfoIbmPvkCrypto[2];
/* 281 */       int acctIbmoffset = Integer.parseInt(dbInfoIbmPvkCrypto[3]);
/*     */       
/* 283 */       String accountIbmNumber = panIbmNumb.substring(acctIbmoffset, acctIbmoffset + 12);
/*     */       
/* 285 */       String pinValidationDataIbmpart1 = panIbmNumb.substring(0, 10);
/* 286 */       String pinValidationDataIbmpart2 = panIbmNumb.substring(panIbmNumb.length() - 1);
/* 287 */       String pinValidationDataIbm = pinValidationDataIbmpart1 + "N" + pinValidationDataIbmpart2;
/*     */       
/* 289 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateIBMOffset(cryptoIbmPvkLenght, cryptoIbmPVK, pinIbmUnderLMK, cryptoIbmminPinLenght, 
/* 290 */         "0123456789012345", pinValidationDataIbm, accountIbmNumber));
/* 291 */       responseMessage = UnpackThalesCryptoCommand.unpackgenerateIBMOffset(requestMessage);
/*     */       
/* 293 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 295 */       CloseConnection();
/*     */       
/* 297 */       break;
/*     */     
/*     */ 
/*     */     case 10: 
/* 301 */       ConnectServer();
/* 303 */       String[] reqOffVisaSplit = requestmsg.split(",");
/* 304 */       String pinUnderLMK = reqOffVisaSplit[1];
/* 305 */       String panNumb = reqOffVisaSplit[2];
/*     */       
/* 307 */       String[] dbInfoPvkCrypto = ExecuteSP.execGetCryptoPvkVisa(conn).split(",");
/* 308 */       String cryptoPVK = dbInfoPvkCrypto[0];
/* 309 */       String cryptoPvki = dbInfoPvkCrypto[1];
/* 310 */       int acctnmboffset = Integer.parseInt(dbInfoPvkCrypto[2]);
/*     */       
/* 312 */       String accountNumber = panNumb.substring(acctnmboffset, acctnmboffset + 12);
/*     */       
/* 314 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateVISAOffset(cryptoPVK, pinUnderLMK, cryptoPvki, accountNumber));
/* 315 */       responseMessage = UnpackThalesCryptoCommand.unpackgenerateVISAOffset(requestMessage);
/* 317 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/* 319 */       CloseConnection();
/*     */       
/* 321 */       break;
/*     */     
/*     */ 
/*     */     case 11: 
/* 325 */       ConnectServer();
/*     */       
/* 327 */       String[] reqPinSplit = requestmsg.split(",");
/* 328 */       String reqPinTerminalId = "KEK_" + reqPinSplit[1];
/*     */       
/* 330 */       String[] dbInfoKekCrypto = ExecuteSP.execGetCryptoFromTerminalID(reqPinTerminalId, conn).split(",");
/* 331 */       String cryptoKek = dbInfoKekCrypto[1];
/*     */       
/* 333 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.pinMngt(cryptoKek));
/* 334 */       responseMessage = UnpackThalesCryptoCommand.unpackpinMngt(requestMessage);
/*     */       
/* 336 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 338 */       CloseConnection();
/*     */       
/* 340 */       break;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     case 13: 
/* 351 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.echoTestMessage());
/* 352 */       responseMessage = UnpackThalesCryptoCommand.unpackechoTestMessage(requestMessage);
/*     */       
/* 354 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 356 */       break;
/*     */     
/*     */ 
/*     */     case 14: 
/* 360 */       ConnectServer();
/*     */       
/* 362 */       String[] reqCheckDigitSplit = requestmsg.split(",");
/* 363 */       String reqCheckDigitTerminalId = "KWP_" + reqCheckDigitSplit[1];
/*     */       
/* 365 */       System.out.println("Terminal ID: " + reqCheckDigitTerminalId);
/*     */       
/* 367 */       String[] dbInfoCrypto = ExecuteSP.execGetCryptoFromTerminalID(reqCheckDigitTerminalId, conn).split(",");
/* 368 */       String dbcryptoLenght = dbInfoCrypto[0];
/* 369 */       String dbCryptogram = dbInfoCrypto[1];
/* 370 */       String dbKeyType = dbInfoCrypto[2];
/* 371 */       System.out.println("Lenght BD: " + dbcryptoLenght);
/* 372 */       System.out.println("Crypto BD: " + dbCryptogram);
/* 373 */       System.out.println("Key Type BD: " + dbKeyType);
/*     */       
/* 375 */       requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateCheckDigit(dbCryptogram, dbcryptoLenght, dbKeyType));
/* 376 */       responseMessage = UnpackThalesCryptoCommand.unpackGenerateCheckDigit(requestMessage);
/*     */       
/* 378 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
/*     */       
/* 380 */       CloseConnection();
/*     */       
/* 382 */       break;
/*     */     
/*     */ 
/*     */     case 15: 

	try {
		requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.statusCommand());
	} catch (Exception e) {
		 throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
	}

/* 387 */       responseMessage = UnpackThalesCryptoCommand.unpackstatusCommand(requestMessage);
/* 389 */       System.out.println("Mensaje de Respuesta: " + responseMessage);
			    System.out.println("requestMessage: " + requestMessage);  
/* 391 */       break;
/*     */     
/*     */ 
			/*     */ case 16:
				/* 395 */ try {
					 requestMessage = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.firmwareCommand());
				} catch (Exception e) {
					throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
				}
				/*     */
/* 398 */       responseMessage = UnpackThalesCryptoCommand.unpackfirmwareCommand(requestMessage);
/*     */       System.out.println("Mensaje de Respuesta: " + responseMessage);
                System.out.println("requestMessage: " + responseMessage);


/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 409 */     return responseMessage;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean ConnectServer()
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 418 */       UseParameters.getParametersConfig();
/* 419 */       String ipsrv = UseParameters.ParametersConfig.ipsrv();
/* 420 */       String portsrv = UseParameters.ParametersConfig.portsrv();
/*     */       
/* 422 */       String user = "OmniKeyService";
/* 423 */       String password = new String("*OmniKey*Service");
/*     */       
/* 425 */       ConnectDB con = new ConnectDB(user, password, "OmniKey", ipsrv, portsrv);
/*     */       try
/*     */       {
/* 428 */         ConnectDB.setLoginTimeout(15);
/* 429 */         conn = ConnectDB.GetConnection();
/*     */       }
/*     */       catch (SQLException e1)
/*     */       {
/* 433 */         System.out.println(e1.getMessage());
/* 434 */         e1.printStackTrace();
/*     */       }
/*     */       
/* 437 */       System.out.println(user + password + ipsrv + portsrv);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 442 */       if (conn != null)
/*     */       {
/*     */ 
/* 445 */         String role = ExecuteSP.execIsServicemember(user, conn);
/* 446 */         System.out.println(role);
/*     */         
/*     */ 
/* 449 */         if (role.equals("0"))
/*     */         {
/* 451 */           System.out.println("User don't have Permission");
/*     */           
/*     */ 
/* 454 */           ConnectDB.cerrar(conn);
/* 455 */           return false;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 461 */         return true;
/*     */       }
/*     */       
/* 464 */       return false;
/*     */ 
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */ 
/* 470 */       System.out.println(e.getMessage());
/* 471 */       e.printStackTrace(); }
/* 472 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean CloseConnection()
/*     */     throws Exception
/*     */   {
/* 479 */     ConnectDB.cerrar(conn);
/* 480 */     if (conn.isClosed()) {
/* 481 */       System.out.println("Connection closed.");
/* 482 */       return false;
/*     */     }
/* 484 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/OmniCryptoCommand.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */