/*    */ package com.alodiga.hsm.operations;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.text.ParseException;
/*    */ import javax.swing.text.MaskFormatter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HexFormat
/*    */ {
/* 12 */   private static MaskFormatter Hexformat = null;
/*    */   
/*    */   public static TextKeyFormat getHexSingleFormat() throws IOException {
/* 15 */     return new TextKeyFormat("HexTextKey");
/*    */   }
/*    */   
/*    */   public static TextCheckDigit getHexSingleFormatCheckDigit() throws IOException {
/* 19 */     return new TextCheckDigit("HexCheckDigit");
/*    */   }
/*    */   
/*    */   public static TextKeyBlockFormat getHexKeyBlockFormat() throws IOException {
/* 23 */     return new TextKeyBlockFormat("HexKeyBlockFormat");
/*    */   }
/*    */   
/*    */   public static class TextCheckDigit {
/* 27 */     static MaskFormatter formattrypeCd = null;
/*    */     
/* 29 */     public TextCheckDigit(String format) { if (format.equals("HexCheckDigit"))
/*    */         try {
/* 31 */           formattrypeCd = new MaskFormatter("HHHH");
/*    */         }
/*    */         catch (ParseException e1) {
/* 34 */           e1.printStackTrace();
/*    */         }
/*    */     }
/*    */     
/*    */     public static MaskFormatter formattrypeCd() {
/* 39 */       formattrypeCd.setValidCharacters("0123456789abcdefABCDEF");
/* 40 */       return formattrypeCd;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public static class TextKeyFormat
/*    */   {
/* 47 */     static MaskFormatter formattrype = null;
/*    */     
/* 49 */     public TextKeyFormat(String format) { if (format.equals("HexTextKey"))
/*    */         try {
/* 51 */           formattrype = new MaskFormatter("HHHHHHHHHHHHHHHH");
/*    */         }
/*    */         catch (ParseException e1) {
/* 54 */           e1.printStackTrace();
/*    */         }
/*    */     }
/*    */     
/*    */     public static MaskFormatter formattrype() {
/* 59 */       formattrype.setValidCharacters("0123456789abcdefABCDEF");
/* 60 */       return formattrype;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public static class TextKeyBlockFormat
/*    */   {
/* 67 */     static MaskFormatter formatKeyBlock = null;
/*    */     
/* 69 */     public TextKeyBlockFormat(String format) { if (format.equals("HexKeyBlockFormat"))
/*    */         try {
/* 71 */           formatKeyBlock = new MaskFormatter("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
/*    */         }
/*    */         catch (ParseException e1) {
/* 74 */           e1.printStackTrace();
/*    */         }
/*    */     }
/*    */     
/*    */     public static MaskFormatter formatKeyBlock() {
/* 79 */       formatKeyBlock.setValidCharacters("0123456789abcdefABCDEF");
/* 80 */       return formatKeyBlock;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Operations/HexFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */