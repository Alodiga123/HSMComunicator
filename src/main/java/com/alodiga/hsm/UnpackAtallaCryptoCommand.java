/*     */ package com.alodiga.hsm;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ public class UnpackAtallaCryptoCommand
/*     */ {
/*   8 */   private static final String[] Commands = new String[50];
/*     */   private static final String Separator = "#";
/*     */   
/*  11 */   static { Commands[0] = "00";
/*  12 */     Commands[1] = "10";
/*  13 */     Commands[2] = "7E";
/*  14 */     Commands[3] = "00";
/*  15 */     Commands[4] = "00";
/*  16 */     Commands[5] = "00";
/*     */     
/*     */ 
/*  19 */     ErrorCodes = new String[50];
/*     */     
///*  21 */     ErrorCodes[0] = "00";
///*  22 */     ErrorCodes[1] = "10";
///*  23 */     ErrorCodes[2] = "7E";
///*  24 */     ErrorCodes[3] = "00";
///*  25 */     ErrorCodes[4] = "00";
///*  26 */     ErrorCodes[5] = "00";
/*     */   }
/*     */   
/*     */   private static final String[] ErrorCodes;
/*     */   public static String unpackEchoTestMessage(String responseCommand) {
/*  31 */     String[] UnpackCommand = responseCommand.split("#");
/*  32 */     String InstructionCommand = null;String CommandResult = null;String CommandoMessage = null;
/*     */     
/*  34 */     InstructionCommand = UnpackCommand[0].substring(1, 2);
/*  35 */     CommandResult = UnpackCommand[1];
/*  36 */     CommandoMessage = UnpackCommand[2];
/*     */     
/*  38 */     System.out.println("unpackCommand : " + CommandResult);
/*     */     
/*  40 */     return CommandResult;
/*     */   }
/*     */   
/*     */   public static String unpackGenerateCheckDigit(String responseCommand) {
/*  44 */     String[] UnpackCommand = responseCommand.split("#");
/*  45 */     String InstructionCommand = null;String CheckDigitMethod = null;String CheckDigit = null;
/*     */     
/*  47 */     InstructionCommand = UnpackCommand[0].substring(1, 3);
/*  48 */     System.out.println("Unpack InstructionCommand : " + InstructionCommand);
/*     */     
/*  50 */     if (InstructionCommand.equals("00")) {
/*  51 */       Framework.CryptoAssociated.failedResponseHsm = true;
/*  52 */       Framework.ImportCryptogram.failedResponseHsm = true;
/*  53 */       return unpackErrorResponse(responseCommand);
/*     */     }
/*  55 */     CheckDigitMethod = UnpackCommand[1];
/*  56 */     CheckDigit = UnpackCommand[2].substring(0, 4);
/*     */     
/*     */ 
/*  59 */     System.out.println("UnpackCheckDigit:" + CheckDigit);
/*  60 */     return CheckDigit;
/*     */   }
/*     */   
/*     */   public static String unpackGenerateWorkingKey(String responseCommand) {
/*  64 */     String[] UnpackCommand = responseCommand.split("#");
/*  65 */     String InstructionCommand = null;String CryptoUnderMFK = null;String CryptoUnderKEK = null;String CryptoCheckDigit = null;
/*     */     
/*  67 */     CryptoUnderMFK = UnpackCommand[1];
/*  68 */     CryptoUnderKEK = UnpackCommand[2];
/*  69 */     CryptoCheckDigit = UnpackCommand[3].substring(0, 4);
/*     */     
/*  71 */     System.out.println("unpackKey : " + CryptoUnderMFK);
/*  72 */     System.out.println("unpackCheckDigit : " + CryptoCheckDigit);
/*     */     
/*  74 */     String CryptAndCheckDigit = CryptoUnderMFK + "," + CryptoCheckDigit;
/*     */     
/*  76 */     return CryptAndCheckDigit;
/*     */   }
/*     */   
/*     */   public static String unpackExportKey(String responseCommand) {
/*  80 */     String[] UnpackCommand = responseCommand.split("#");
/*  81 */     String InstructionCommand = null;String CryptoUnderKEK = null;String CryptoCheckDigit = null;
/*     */     
/*  83 */     InstructionCommand = UnpackCommand[0].substring(1, 3);
/*  84 */     if (InstructionCommand.equals("00")) {
/*  85 */       Framework.CryptoAssociated.failedExportHsm = true;
/*  86 */       return unpackErrorResponse(responseCommand);
/*     */     }
/*     */     
/*  89 */     CryptoUnderKEK = UnpackCommand[1];
/*  90 */     CryptoCheckDigit = UnpackCommand[2].substring(0, 4);
/*     */     
/*     */ 
/*     */ 
/*  94 */     String CryptAndCheckDigit = CryptoUnderKEK + "," + CryptoCheckDigit;
/*     */     
/*  96 */     return CryptAndCheckDigit;
/*     */   }
/*     */   
/*     */   public static String unpackImportKey(String responseCommand) {
/* 100 */     String[] UnpackCommand = responseCommand.split("#");
/* 101 */     String InstructionCommand = null;String CryptoUnderKEK = null;String CryptoCheckDigit = null;
/*     */     
/* 103 */     InstructionCommand = UnpackCommand[0].substring(1, 3);
/* 104 */     if (InstructionCommand.equals("00")) {
/* 105 */       Framework.ImportCryptogram.failedImportHsm = true;
/* 106 */       return unpackErrorResponse(responseCommand);
/*     */     }
/* 108 */     CryptoUnderKEK = UnpackCommand[1];
/* 109 */     CryptoCheckDigit = UnpackCommand[2].substring(0, 4);
/*     */     
/*     */ 
/* 112 */     String CryptAndCheckDigit = CryptoUnderKEK + "," + CryptoCheckDigit;
/*     */     
/* 114 */     return CryptAndCheckDigit;
/*     */   }
/*     */   
/*     */   public static String unpackErrorResponse(String errorResponseCommnad) {
/* 118 */     String[] UnpackCommand = errorResponseCommnad.split("#");
/* 119 */     String InstructionCommand = null;String CommandResultXX = null;String CommandResultYY = null;
/*     */     
/* 121 */     InstructionCommand = UnpackCommand[0].substring(1, 2);
/* 122 */     CommandResultXX = UnpackCommand[1].substring(0, 2);
/* 123 */     CommandResultYY = UnpackCommand[1].substring(2, 4);
/*     */     
/* 125 */     String FinalCommandResult = null;
/*     */     
/* 127 */     if (CommandResultXX.equals("01"))
/* 128 */       FinalCommandResult = "Field Out Of Range";
/* 129 */     if (CommandResultXX.equals("02"))
/* 130 */       FinalCommandResult = "Invalid Character ";
/* 131 */     if (CommandResultXX.equals("03"))
/* 132 */       FinalCommandResult = "Value Out Of Range";
/* 133 */     if (CommandResultXX.equals("04"))
/* 134 */       FinalCommandResult = "Token Missing";
/* 135 */     if (CommandResultXX.equals("05"))
/* 136 */       FinalCommandResult = "Parity Error";
/* 137 */     if (CommandResultXX.equals("06"))
/* 138 */       FinalCommandResult = "Major Key Missing";
/* 139 */     if (CommandResultXX.equals("07"))
/* 140 */       FinalCommandResult = "Indexed Key Missing";
/* 141 */     if (CommandResultXX.equals("08"))
/* 142 */       FinalCommandResult = "Hardware Failure";
/* 143 */     if (CommandResultXX.equals("09"))
/* 144 */       FinalCommandResult = "Invalid Message Format";
/* 145 */     if (CommandResultXX.equals("10"))
/* 146 */       FinalCommandResult = "Index Table Full";
/* 147 */     if (CommandResultXX.equals("12"))
/* 148 */       FinalCommandResult = "PIN Type Not Supported";
/* 149 */     if (CommandResultXX.equals("13"))
/* 150 */       FinalCommandResult = "Invalid Message Length";
/* 151 */     if (CommandResultXX.equals("14"))
/* 152 */       FinalCommandResult = "Communication Error";
/* 153 */     if (CommandResultXX.equals("15"))
/* 154 */       FinalCommandResult = "Communication Timeout";
/* 155 */     if (CommandResultXX.equals("17"))
/* 156 */       FinalCommandResult = "MAC Error";
/* 157 */     if (CommandResultXX.equals("18"))
/* 158 */       FinalCommandResult = "Invalid MAC Continuation";
/* 159 */     if (CommandResultXX.equals("19"))
/* 160 */       FinalCommandResult = "Function Not Supported";
/* 161 */     if (CommandResultXX.equals("30"))
/* 162 */       FinalCommandResult = "Illegal Index Data";
/* 163 */     if (CommandResultXX.equals("31"))
/* 164 */       FinalCommandResult = "Serial Buffer Overflow";
/* 165 */     if (CommandResultXX.equals("32"))
/* 166 */       FinalCommandResult = "Critical Error";
/* 167 */     if (CommandResultXX.equals("33"))
/* 168 */       FinalCommandResult = "Data Ram Error";
/* 169 */     if (CommandResultXX.equals("34"))
/* 170 */       FinalCommandResult = "Invalid Token";
/* 171 */     if (CommandResultXX.equals("35"))
/* 172 */       FinalCommandResult = "Illegal Backup/Restore";
/* 173 */     if (CommandResultXX.equals("40"))
/* 174 */       FinalCommandResult = "Key will overwrite Diebold table";
/* 175 */     if (CommandResultXX.equals("41"))
/* 176 */       FinalCommandResult = "Diebold table will overwrite key";
/* 177 */     if (CommandResultXX.equals("42"))
/* 178 */       FinalCommandResult = "DES Data-In Time Out Error";
/* 179 */     if (CommandResultXX.equals("43"))
/* 180 */       FinalCommandResult = "DES Data-Out Time Out Error";
/* 181 */     if (CommandResultXX.equals("44"))
/* 182 */       FinalCommandResult = "Illegal, Double Keys";
/* 183 */     if (CommandResultXX.equals("45"))
/* 184 */       FinalCommandResult = "Illegal Transaction Counter, Zero Ones or More Than Ten Ones";
/* 185 */     if (CommandResultXX.equals("46"))
/* 186 */       FinalCommandResult = "Illegal, New KSN Same As Current";
/* 187 */     if (CommandResultXX.equals("47"))
/* 188 */       FinalCommandResult = "Invalid Mac";
/* 189 */     if (CommandResultXX.equals("48"))
/* 190 */       FinalCommandResult = "Invalid Key block";
/* 191 */     if (CommandResultXX.equals("49"))
/* 192 */       FinalCommandResult = "Invalid Login Or Password";
/* 193 */     if (CommandResultXX.equals("50"))
/* 194 */       FinalCommandResult = "User Not Logged In";
/* 195 */     if (CommandResultXX.equals("51"))
/* 196 */       FinalCommandResult = "Invalid Configuration";
/* 197 */     if (CommandResultXX.equals("52"))
/* 198 */       FinalCommandResult = "Function Not Found";
/* 199 */     if (CommandResultXX.equals("53"))
/* 200 */       FinalCommandResult = "Index Data Is Not A Des Key";
/* 201 */     if (CommandResultXX.equals("54"))
/* 202 */       FinalCommandResult = "Key Strength Is Greater Than MFK Strength";
/* 203 */     if (CommandResultXX.equals("55"))
/* 204 */       FinalCommandResult = "Cannot Change Permission On Production Port";
/* 205 */     if (CommandResultXX.equals("56"))
/* 206 */       FinalCommandResult = "Key Generation In Progress";
/* 207 */     if (CommandResultXX.equals("57"))
/* 208 */       FinalCommandResult = "Invalid Public Key Data";
/* 209 */     if (CommandResultXX.equals("58"))
/* 210 */       FinalCommandResult = "Invalid Private Key Data";
/* 211 */     if (CommandResultXX.equals("59"))
/* 212 */       FinalCommandResult = "Invalid Certificate Data";
/* 213 */     if (CommandResultXX.equals("60"))
/* 214 */       FinalCommandResult = "Invalid Certificate Request";
/* 215 */     if (CommandResultXX.equals("61"))
/* 216 */       FinalCommandResult = "No Certificates In PKCS #7 Data";
/* 217 */     if (CommandResultXX.equals("62"))
/* 218 */       FinalCommandResult = "No Names Match Specified Organization Name";
/* 219 */     if (CommandResultXX.equals("63"))
/* 220 */       FinalCommandResult = "Incorrect Key Type";
/* 221 */     if (CommandResultXX.equals("64"))
/* 222 */       FinalCommandResult = "Key Strength Greater Than Encrypting Key Strength";
/* 223 */     if (CommandResultXX.equals("73")) {
/* 224 */       FinalCommandResult = "Header Mismatch";
/*     */     }
/* 226 */     System.out.println("unpackCommand : " + CommandResultXX + "," + CommandResultYY);
/*     */     
/* 228 */     return FinalCommandResult;
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/UnpackAtallaCryptoCommand.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */