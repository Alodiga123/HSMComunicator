/*    */ package com.alodiga.hsm.operations;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ 
/*    */ public class SystemDate {
/*    */   public static String GenerateDate() {
/*  7 */     java.util.Date now = new java.util.Date();
/*  8 */     DateFormat df2 = DateFormat.getDateInstance(2);
/*  9 */     String s2 = df2.format(now);
/* 10 */     return s2;
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Operations/SystemDate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */