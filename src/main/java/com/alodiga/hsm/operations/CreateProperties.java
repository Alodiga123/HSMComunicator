/*    */ package com.alodiga.hsm.operations;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class CreateProperties
/*    */ {
/*    */   public static UseParameters.ParametersConfig getParametersConfig() throws IOException
/*    */   {
/* 11 */     Properties properties = new Properties();
/* 12 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 13 */     properties.load(data_entry);
/* 14 */     data_entry.close();
/* 15 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Operations/CreateProperties.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */