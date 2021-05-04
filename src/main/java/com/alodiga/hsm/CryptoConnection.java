/*     */ package com.alodiga.hsm;
/*     */ import java.io.BufferedInputStream;
		   import java.util.logging.*;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;

import com.alodiga.hsm.exception.NotConnectionHSMException;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.UseParameters.HsmConfig;
import com.alodiga.hsm.operations.UseParameters.TypeHSM;
import com.alodiga.hsm.util.ConstantResponse;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CryptoConnection
/*     */ {
/*  36 */   private static String iphsm = null;
/*  37 */   private static String porthsm = null;
/*  38 */   private static String requestData = null;
/*  39 */   private static String responseData = null;
/*  40 */   private static String responseString = null;
/*  41 */   private static int headermessage = 0;
/*  42 */   static Socket hsm_socket = new Socket();
/*  43 */   private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
/*  44 */   public static FileOutputStream file_out = null;
/*     */   
/*     */   public static void connectHsm() {
/*     */     try {
/*  48 */       UseParameters.getHSMConfig();
/*  49 */       hsm_socket = new Socket(UseParameters.HsmConfig.iphsmCon(), UseParameters.HsmConfig.porthsmCon());
/*     */       
/*  51 */       if (hsm_socket.isConnected()) {
/*  52 */         System.out.println("Connection Established: " + iphsm + " On Port : " + porthsm);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  56 */       System.err.println("Connection Problem: " + iphsm + " On Port : " + porthsm + "Please Verity that HSM is Available");
/*     */     }
/*     */   }
/*     */   
/*     */   public static int verifyConnection() {
/*  61 */     if (hsm_socket.isConnected()) {
/*  62 */       System.out.println("Connection Established: " + iphsm + " On Port : " + porthsm);
/*  63 */       return 1;
/*     */     }
/*     */     
/*  66 */     System.err.println("Connection Problem: " + iphsm + " On Port : " + porthsm + "Please Verity that HSM is Available");
/*  67 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String sendAndReceiveToHSM(String message)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
				Logger.getLogger (CryptoConnection.class.getName()).log(Level.INFO, "entro");
	
/*  78 */       UseParameters.getHSMConfig();
				Logger.getLogger (CryptoConnection.class.getName()).log(Level.INFO, "obtiene configuracion");
/*  79 */       requestData = message;
/*  80 */       System.out.println("TEST MESSAGE :" + message);
/*  81 */       System.out.println(UseParameters.HsmConfig.iphsmCon());
/*  82 */       System.out.println(UseParameters.HsmConfig.porthsmCon());
				Logger.getLogger (CryptoConnection.class.getName()).log(Level.INFO, "ip:"+UseParameters.HsmConfig.iphsmCon()+"port:"+UseParameters.HsmConfig.porthsmCon());

/*  83 */       hsm_socket = new Socket(UseParameters.HsmConfig.iphsmCon(), UseParameters.HsmConfig.porthsmCon());
/*  78 */       UseParameters.getHSMConfig();
/*  79 */       requestData = message;
/*  88 */       DataOutputStream output = new DataOutputStream(new BufferedOutputStream(hsm_socket.getOutputStream()));
/*  89 */       DataInputStream inFromHsm = new DataInputStream(new BufferedInputStream(hsm_socket.getInputStream()));
/*     */       
/*     */ 
/*  92 */       UseParameters.getTypeHsm();
/*  93 */       int lenghtchar = requestData.length();
/*  94 */       System.out.println("0");
/*  95 */       if (UseParameters.TypeHSM.typeHsm().equals("Thales")) {
/*  96 */         System.out.println("1");
/*  97 */         if (UseParameters.HsmConfig.headermessage().equals("No Header")) {
/*  98 */           output.write(requestData.getBytes());
/*  99 */           output.flush();
/*     */         }
/* 101 */         if (UseParameters.HsmConfig.headermessage().equals("2 byte")) {
/* 102 */           System.out.println("2");
/* 103 */           if (lenghtchar <= 99) {
/* 104 */             System.out.println("3");
/* 105 */             output.writeByte(0);
/* 106 */             output.writeByte(lenghtchar);
/* 107 */             output.write(requestData.getBytes());
/* 108 */             output.flush();
/*     */           }
/*     */           else {
/* 111 */             System.out.println("4");
/* 112 */             output.writeByte(0);
/* 113 */             output.writeByte(lenghtchar);
/* 114 */             output.write(requestData.getBytes());
/* 115 */             output.flush();
/*     */           }
/*     */         }
/* 118 */         if (UseParameters.HsmConfig.headermessage().equals("4 byte char")) {
/* 119 */           System.out.println("5");
/* 120 */           if (lenghtchar <= 99) {
/* 121 */             System.out.println("6");
/* 122 */             output.writeChar(0);
/* 123 */             output.writeChar(lenghtchar);
/* 124 */             output.write(requestData.getBytes());
/* 125 */             output.flush();
/* 126 */           } else if (lenghtchar <= 999) {
/* 127 */             System.out.println("7");
/* 128 */             output.writeChar(0);
/* 129 */             output.writeChar(lenghtchar);
/* 130 */             output.write(requestData.getBytes());
/* 131 */             output.flush();
/* 132 */           } else if (lenghtchar <= 9999) {
/* 133 */             System.out.println("8");
/* 134 */             output.writeChar(0);
/* 135 */             output.writeChar(0);
/* 136 */             output.writeChar(0);
/* 137 */             output.writeChar(lenghtchar);
/* 138 */             output.write(requestData.getBytes());
/* 139 */             output.flush();
/*     */           }
/*     */           else
/*     */           {
/* 143 */             System.out.println("9");
/* 144 */             output.writeChar(lenghtchar);
/* 145 */             output.write(requestData.getBytes());
/* 146 */             output.flush();
/*     */           }
/*     */         }
/*     */       }
/* 150 */       if (UseParameters.getTypeHsm().equals("Atalla")) {
/* 151 */         System.out.println("10");
/* 152 */         output.write(String.valueOf(lenghtchar).getBytes());
/* 153 */         output.write(requestData.getBytes());
/* 154 */         output.flush();
/*     */       }

/* 166 */       if (UseParameters.HsmConfig.headermessage().equals("No Header"))
/* 167 */         headermessage = 0;
/* 168 */       if (UseParameters.HsmConfig.headermessage().equals("2 byte"))
/* 169 */         headermessage = 2;
/* 170 */       if (UseParameters.HsmConfig.headermessage().equals("4 byte char")) {
/* 171 */         headermessage = 4;
/*     */       }
/*     */       
/* 174 */       byte[] response_from_hsm = new byte['Ǵ'];
/* 175 */       int readed = inFromHsm.read(response_from_hsm);
/* 176 */       byte[] response_bytes = new byte[readed];
/*     */       
/*     */ 
/*     */ 
/* 180 */       response_bytes = ReadHSMResponse.getData(response_from_hsm, headermessage, readed);
/*     */       
/* 182 */       responseString = ReadHSMResponse.getString(response_bytes, ReadHSMResponse.Encoding.ASCII_8_BIT);
/*     */       
/* 184 */       System.out.println("respuesta HSM"+responseString);
/*     */       
/*     */ 
/* 187 */       loguearMensage(1, "\n\n## Message to HSM ##\n\n" + requestData);
/* 188 */       loguearMensage(2, "\n\n## Message from HSM ##\n\n" + responseData);
/*     */     }
/*     */     catch (Exception e) {
	 			System.out.println("Error: " +"no hay coneccion");
/* 191 */       System.out.println("Error: " + e.getMessage());
				throw new NotConnectionHSMException(ConstantResponse.NOT_RESPONSE_HSM);
/*     */     }
/* 193 */     hsm_socket.close();
/* 194 */     System.out.println(hsm_socket.isConnected());
/* 195 */     return responseString;
/*     */   }
/*     */   
/*     */   public static void loguearMensage(int tipo_mensaje, String lugar) {
/* 199 */     printlog(String.format("HSM Crypto Message", new Object[] { dateFormat.format(new Date()), lugar }));
/*     */   }
/*     */   
/*     */ 
/*     */   public static void printlog(String text)
/*     */   {
/* 205 */     SimpleDateFormat sdf = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss]");
/*     */     try
/*     */     {
/* 208 */       if (file_out == null) {
/* 209 */         File file = new File("/log");
/* 210 */         file.mkdirs();
/* 211 */         file_out = new FileOutputStream("/log/log_SKS.txt");
/*     */       }
/*     */       
/*     */ 
/* 215 */       file_out.write((sdf.format(new Date()) + " " + text + '\n').getBytes());
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {}
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/CryptoConnection.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */