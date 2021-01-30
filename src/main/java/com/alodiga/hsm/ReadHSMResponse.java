/*    */ package com.alodiga.hsm;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ 
/*    */ public class ReadHSMResponse
/*    */ {
/*    */   public static class Encoding
/*    */   {
/*    */     public String getName()
/*    */     {
/* 11 */       return this.name;
/*    */     }
/*    */     
/*    */     public String toString()
/*    */     {
/* 16 */       return "encoding." + this.name;
/*    */     }
/*    */     
/* 19 */     public static final Encoding ASCII = new Encoding("ASCII");
/* 20 */     public static final Encoding ASCII_8_BIT = new Encoding("ISO8859_1");
/* 21 */     public static final Encoding EBCDIC = new Encoding("EBCDIC");
/*    */     
/*    */     private String name;
/*    */     
/*    */     private Encoding(String name)
/*    */     {
/* 27 */       this.name = name;
/*    */     }
/*    */   }
/*    */   
/*    */   public static byte[] getData(byte[] data, int offset, int length)
/*    */   {
/* 33 */     byte[] temp = new byte[length];
/* 34 */     System.arraycopy(data, offset, temp, 0, length);
/* 35 */     return temp;
/*    */   }
/*    */   
/*    */   public static byte[] encode(byte[] input, Encoding in, Encoding out)
/*    */   {
/* 40 */     byte[] retfalse = new byte[0];
/* 41 */     if (in == out)
/* 42 */       return input;
/* 43 */     short[] map = null;
/* 44 */     short[] ascii_to_ebcdic = null;
/* 45 */     short[] ebcdic_to_ascii = null;
/* 46 */     if ((in == Encoding.ASCII_8_BIT) && (out == Encoding.EBCDIC)) {
/* 47 */       map = ascii_to_ebcdic;
/*    */     }
/* 49 */     else if ((in == Encoding.ASCII) && (out == Encoding.EBCDIC)) {
/* 50 */       map = ascii_to_ebcdic;
/*    */     }
/* 52 */     else if ((in == Encoding.EBCDIC) && (out == Encoding.ASCII_8_BIT)) {
/* 53 */       map = ebcdic_to_ascii;
/*    */     }
/* 55 */     else if ((in == Encoding.EBCDIC) && (out == Encoding.ASCII))
/* 56 */       map = ebcdic_to_ascii;
/* 57 */     if (map != null)
/*    */     {
/* 59 */       int len = input.length;
/* 60 */       byte[] ret = new byte[len];
/* 61 */       for (int i = 0; i < len; i++) {
/* 62 */         ret[i] = ((byte)map[(input[i] & 0xFF)]);
/*    */       }
/* 64 */       return ret;
/*    */     }
/* 66 */     return retfalse;
/*    */   }
/*    */   
/*    */   public static String getString(byte[] input, Encoding encoding)
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 72 */     if (encoding == Encoding.EBCDIC) {
/* 73 */       return getString(encode(input, Encoding.EBCDIC, Encoding.ASCII_8_BIT), Encoding.ASCII_8_BIT);
/*    */     }
/* 75 */     if (encoding == Encoding.ASCII_8_BIT)
/* 76 */       return new String(input, "ISO-8859-1");
/* 77 */     if (encoding == Encoding.ASCII)
/* 78 */       return new String(input, encoding.getName());
/* 79 */     return new String(input, encoding.getName());
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/ReadHSMResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */