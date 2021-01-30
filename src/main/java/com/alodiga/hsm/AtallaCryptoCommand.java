/*    */ package com.alodiga.hsm;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class AtallaCryptoCommand {
/*  6 */   private static final String[] Commands = new String[50];
/*    */   private static final String Separator = "#";
/*    */   
/*  9 */   static { Commands[0] = "00";
/* 10 */     Commands[1] = "10";
/* 11 */     Commands[2] = "7E";
/* 12 */     Commands[3] = "13";
/* 13 */     Commands[4] = "11";
/* 14 */     Commands[5] = "1A";
/* 15 */     Commands[6] = "11B";
/*    */   }
/*    */   
/*    */   public static String echoTestMessage()
/*    */   {
/* 20 */     String message = "<" + Commands[0] + "#" + "Message" + "#" + ">";
/* 21 */     System.out.println(message);
/* 22 */     return message;
/*    */   }
/*    */   
/*    */   public static String generateCheckDigit(String Cryptogram, String CryptoLength) {
/* 26 */     String LengtKey = null;
/* 27 */     if (CryptoLength.equals("Single")) {
/* 28 */       LengtKey = "S";
/*    */     }
/* 30 */     if (CryptoLength.equals("Double")) {
/* 31 */       LengtKey = "D";
/*    */     }
/* 33 */     if (CryptoLength.equals("Triple")) {
/* 34 */       LengtKey = "T";
/*    */     }
/*    */     
/* 37 */     String message = "<" + Commands[2] + "#" + LengtKey + "#" + "#" + Cryptogram + "#" + ">";
/* 38 */     System.out.println(message);
/* 39 */     return message;
/*    */   }
/*    */   
/*    */   public static String generateWorkingKey() {
/* 43 */     String message = "<" + Commands[1] + "#" + "1PUNE000" + "#" + "1KDNE000,887C4385D3561347D9F13E4C217C9838B95572BC437BECD4,A6985FFB12A292AB" + "#" + "D" + "#" + ">";
/*    */     
/* 45 */     return message;
/*    */   }
/*    */   
/*    */   public static String exportKey(String KEKCryptogram, String CryptoToExport, String formatexport) {
/* 49 */     String message = null;
/* 50 */     if (formatexport.equals("AKB")) {
/* 51 */       message = "<" + Commands[4] + "#" + "#" + KEKCryptogram + "#" + CryptoToExport + "#" + ">";
/*    */     }
/*    */     
/* 54 */     if (formatexport.equals("Non-AKB")) {
/* 55 */       message = "<" + Commands[5] + "#" + "0" + "#" + KEKCryptogram + "#" + CryptoToExport + "#" + ">";
/*    */     }
/* 57 */     return message;
/*    */   }
/*    */   
/*    */   public static String importKey(String KEKCryptogram, String CryptoToImport, String formatimport) {
/* 61 */     String message = null;
/* 62 */     if (formatimport.equals("AKB")) {
/* 63 */       message = "<" + Commands[3] + "#" + "#" + KEKCryptogram + "#" + CryptoToImport + "#" + ">";
/*    */     }
/*    */     
/* 66 */     if (formatimport.equals("Non-AKB")) {
/* 67 */       message = "<" + Commands[6] + "#" + "0" + "#" + CryptoToImport + "#" + KEKCryptogram + "#" + ">";
/*    */     }
/*    */     
/* 70 */     return message;
/*    */   }
/*    */   
/*    */   public static void main(String[] args)
/*    */   {
/* 75 */     System.out.println("Test");
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/AtallaCryptoCommand.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */