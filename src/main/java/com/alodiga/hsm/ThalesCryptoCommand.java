/*     */ package com.alodiga.hsm;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class ThalesCryptoCommand
/*     */ {
/*   7 */   private static final String[] Commands = new String[50];
/*     */   private static final String MsgHeader = "00003000";
/*     */   private static final String[] KeyTypes;
/*     */   
/*     */   static {
/*  12 */     Commands[0] = "B2";
/*  13 */     Commands[1] = "CC";
/*  14 */     Commands[2] = "JE";
/*  15 */     Commands[3] = "CY";
/*  16 */     Commands[4] = "A0";
/*  17 */     Commands[5] = "A8";
/*  18 */     Commands[6] = "A6";
/*  19 */     Commands[7] = "EA";
/*  20 */     Commands[8] = "EC";
/*  21 */     Commands[9] = "KQ";
/*  22 */     Commands[10] = "DE";
/*  23 */     Commands[11] = "DG";
/*  24 */     Commands[12] = "IA";
/*  25 */     Commands[13] = "BU";
/*  26 */     Commands[14] = "GC";
/*  27 */     Commands[15] = "NO";
/*  28 */     Commands[16] = "NC";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  33 */     KeyTypes = new String[50];
/*     */     
/*  35 */     KeyTypes[0] = "000";
/*  36 */     KeyTypes[1] = "001";
/*  37 */     KeyTypes[2] = "002";
/*  38 */     KeyTypes[3] = "402";
/*  39 */     KeyTypes[4] = "109";
/*     */     
/*     */ 
/*     */ 
/*  43 */     KeySchemes = new String[50];
/*     */     
///*  45 */     KeySchemes[0] = "Z";
///*  46 */     KeySchemes[1] = "U";
///*  47 */     KeySchemes[2] = "T";
///*  48 */     KeySchemes[3] = "X";
///*  49 */     KeySchemes[4] = "Y";
/*     */     
/*     */ 
/*     */ 
/*  53 */     PinBlockFormat = new String[50];
/*     */     
///*  55 */     PinBlockFormat[0] = "1";
///*  56 */     PinBlockFormat[1] = "2";
///*  57 */     PinBlockFormat[2] = "5";
///*  58 */     PinBlockFormat[3] = "8";
///*  59 */     PinBlockFormat[4] = "11";
///*  60 */     PinBlockFormat[4] = "13";
/*     */   }
/*     */   
/*     */   public static String firmwareCommand()
/*     */   {
/*  65 */     String message = "00003000" + Commands[16];
/*  66 */     System.out.println(message);
/*  67 */     return message;
/*     */   }
/*     */   
/*     */   public static String statusCommand() {
/*  71 */     String message = "00003000" + Commands[15] + "00";
/*  72 */     System.out.println(message);
/*  73 */     return message;
/*     */   }
/*     */   
/*     */   public static String echoTestMessage() {
/*  77 */     String message = "00003000" + Commands[0] + "001B" + "111010110111100110100010101";
/*  78 */     System.out.println(message);
/*  79 */     return message;
/*     */   }
/*     */   
/*     */   public static String generateCheckDigit(String Cryptogram, String CryptoLength, String Keytype) {
/*  83 */     String LengtKey = null;
/*  84 */     String keytypecode = null;
/*     */     
/*  86 */     LengtKey = obtainlenghtkey(CryptoLength.trim());
/*  87 */     String lenghtcommand = null;
/*     */     
/*  89 */     if (LengtKey.equals("U")) {
/*  90 */       lenghtcommand = "1";
/*     */     }
/*  92 */     if (LengtKey.equals("Z")) {
/*  93 */       lenghtcommand = "0";
/*     */     }
/*  95 */     if (LengtKey.equals("T")) {
/*  96 */       lenghtcommand = "2";
/*     */     }
/*     */     
/*  99 */     if (Keytype.equals("KEK")) {
/* 100 */       keytypecode = KeyTypes[0];
/*     */     }
/* 102 */     if (Keytype.equals("TPK")) {
/* 103 */       keytypecode = KeyTypes[2];
/*     */     }
/* 105 */     if (Keytype.equals("KWP")) {
/* 106 */       keytypecode = KeyTypes[1];
/*     */     }
/* 108 */     if (Keytype.equals("PVK")) {
/* 109 */       keytypecode = KeyTypes[2];
/*     */     }
/* 111 */     if (Keytype.equals("CVK")) {
/* 112 */       keytypecode = KeyTypes[3];
/*     */     }
/* 114 */     if (Keytype.equals("CAK")) {
/* 115 */       keytypecode = KeyTypes[4];
/*     */     }
/*     */     
/* 118 */     String message = "00003000" + Commands[13] + "FF" + lenghtcommand + LengtKey + Cryptogram + ";" + keytypecode + ";" + "0" + "0" + "1";
/* 119 */     System.out.println(message);
/* 120 */     return message;
/*     */   }
/*     */   
/*     */   public static String generateKey(String CryptoLength, String Keytype) {
/* 124 */     String keytypecode = null;
/* 125 */     String lenghtcommand = null;
/*     */     
/* 127 */     String LengtKey = obtainlenghtkey(CryptoLength);
/*     */     
/* 129 */     if (LengtKey.equals("U")) {
/* 130 */       lenghtcommand = "1";
/*     */     }
/* 132 */     if (LengtKey.equals("Z")) {
/* 133 */       lenghtcommand = "0";
/*     */     }
/* 135 */     if (LengtKey.equals("T")) {
/* 136 */       lenghtcommand = "2";
/*     */     }
/*     */     
/* 139 */     if (Keytype.equals("KEK")) {
/* 140 */       keytypecode = KeyTypes[0];
/*     */     }
/* 142 */     if (Keytype.equals("TPK")) {
/* 143 */       keytypecode = KeyTypes[2];
/*     */     }
/* 145 */     if (Keytype.equals("KWP")) {
/* 146 */       keytypecode = KeyTypes[1];
/*     */     }
/* 148 */     if (Keytype.equals("PVK")) {
/* 149 */       keytypecode = KeyTypes[2];
/*     */     }
/* 151 */     if (Keytype.equals("CVK")) {
/* 152 */       keytypecode = KeyTypes[3];
/*     */     }
/* 154 */     if (Keytype.equals("CAK")) {
/* 155 */       keytypecode = KeyTypes[4];
/*     */     }
/*     */     
/*     */ 
/* 159 */     String message = "00003000" + Commands[4] + "0" + keytypecode + LengtKey;
/*     */     
/* 161 */     System.out.println("Comando generate key: " + message);
/* 162 */     return message;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String transletePintoMFK(String lenghtCryptoKWP, String cryptoKWP, String pinBlockFormat, String pinBlock, String accountNumber)
/*     */   {
/* 168 */     String Lengtkwp = null;
/* 169 */     Lengtkwp = obtainlenghtkey(lenghtCryptoKWP);
/*     */     
/* 171 */     String message = "00003000" + Commands[2] + Lengtkwp + cryptoKWP + pinBlock + pinBlockFormat + accountNumber;
/* 172 */     return message;
/*     */   }
/*     */   
/*     */   public static String translatePintoKWP(String lenghtCryptoKWPOrg, String cryptoKWPOrg, String lenghtCryptoKWPDest, String cryptoKWPDest, String pinLenght, String orgPinBlockFormat, String DestPinBlockFormat, String pinBlock, String accountNumber)
/*     */   {
/* 177 */     String Lengtkwporg = null;
/* 178 */     Lengtkwporg = obtainlenghtkey(lenghtCryptoKWPOrg);
/* 179 */     String Lengtkwpdest = obtainlenghtkey(lenghtCryptoKWPDest);
/*     */     
/* 181 */     String message = "00003000" + Commands[1] + Lengtkwporg + cryptoKWPOrg + Lengtkwpdest + cryptoKWPDest + pinLenght + pinBlock + orgPinBlockFormat + DestPinBlockFormat + accountNumber;
/* 182 */     return message;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String verifyVISACvv(String cryptoCVK, String valueCVV, String expdate, String serviceCode, String accountNumber)
/*     */   {
/* 188 */     String message = "00003000" + Commands[3] + cryptoCVK + valueCVV + accountNumber + expdate + serviceCode;
/* 189 */     return message;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final String[] KeySchemes;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final String[] PinBlockFormat;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String verifyIBMPin(String lenghtCryptoKWP, String cryptoKWP, String cryptoPVK, String maxPinLenght, String minPinLengt, String pinBlockFormat, String pinBlock, String accountNumber, String decimalizationTable, String pinValidationData, String pinOffset)
/*     */   {
/* 222 */     String Lengtkwp = null;
/* 223 */     Lengtkwp = obtainlenghtkey(lenghtCryptoKWP);
/*     */     
/*     */ 
/* 226 */     String message = "00003000" + Commands[7] + Lengtkwp + cryptoKWP + cryptoPVK + maxPinLenght + pinBlock + pinBlockFormat + minPinLengt + accountNumber + 
/* 227 */       decimalizationTable + pinValidationData + pinOffset;
/* 228 */     return message;
/*     */   }
/*     */   
/*     */   public static String verifyVISAPin(String lenghtCryptoKWP, String cryptoKWP, String cryptoPVK, String pvki, String pvv, String pinBlockFormat, String pinBlock, String accountNumber)
/*     */   {
/* 233 */     String Lengtkwp = null;
/* 234 */     Lengtkwp = obtainlenghtkey(lenghtCryptoKWP);
/*     */     
/* 236 */     String message = "00003000" + Commands[8] + Lengtkwp + cryptoKWP + cryptoPVK + pinBlock + pinBlockFormat + accountNumber + pvki + pvv;
/* 237 */     return message;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String generateIBMOffset(String crytpoPvkLenght, String cryptoPVK, String pinUnderLMK, String minPinLengt, String decimalizationTable, String pinValidationData, String accountNumber)
/*     */   {
/* 252 */     String lenghtPvk = obtainlenghtkey(crytpoPvkLenght);
/*     */     
/* 254 */     String message = "00003000" + Commands[10] + cryptoPVK + pinUnderLMK + minPinLengt + accountNumber + decimalizationTable + pinValidationData;
/* 255 */     return message;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String generateVISAOffset(String cryptoPVK, String pinUnderLMK, String pvki, String accountNumber)
/*     */   {
/* 261 */     String message = "00003000" + Commands[11] + cryptoPVK + pinUnderLMK + accountNumber + pvki;
/* 262 */     return message;
/*     */   }
/*     */   
/*     */   public static String pinMngt(String cryptoKEK)
/*     */   {
/* 267 */     String message = "00003000" + Commands[12] + cryptoKEK;
/* 268 */     return message;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String obtainlenghtkey(String originalLenght)
/*     */   {
/* 280 */     String resultLenght = null;
/* 281 */     System.out.println(originalLenght);
/* 282 */     if (originalLenght.equals("Single")) {
/* 283 */       resultLenght = KeySchemes[0];
/* 284 */       System.out.println("Single" + resultLenght);
/*     */     }
/* 286 */     if (originalLenght.equals("Double")) {
/* 287 */       resultLenght = KeySchemes[1];
/* 288 */       System.out.println("Double" + resultLenght);
/*     */     }
/* 290 */     if (originalLenght.equals("Triple")) {
/* 291 */       resultLenght = KeySchemes[2];
/* 292 */       System.out.println("Triple" + resultLenght);
/*     */     }
/* 294 */     System.out.println("Resultado Final KeyScheme: " + resultLenght);
/* 295 */     return resultLenght;
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 300 */     System.out.println("Test");
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/ThalesCryptoCommand.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */