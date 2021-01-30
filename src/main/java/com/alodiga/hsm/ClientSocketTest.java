/*    */ package com.alodiga.hsm;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public class ClientSocketTest
/*    */ {
/*    */   public static void main(String[] argv) throws Exception
/*    */   {
/* 10 */     BufferedReader inFromUser = new BufferedReader(new java.io.InputStreamReader(System.in));
/*    */     
/* 12 */     java.net.Socket clientSocket = new java.net.Socket("192.168.5.78", 14250);
/* 13 */     DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
/* 14 */     BufferedReader inFromServer = new BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
/*    */     
/* 16 */     String sentence = "16";
/* 17 */     String sentence2 = "15";
/* 18 */     String sentence3 = "14,TEMP1";
/* 19 */     String sentence4 = "03,Double,KEK";
/* 20 */     String sentence5 = "01,T080-01,TEST INSTITUTION,D0B574299DAC5236,4,4988619002216583";
/* 21 */     outToServer.writeBytes(sentence + '\n');
/* 22 */     outToServer.writeBytes(sentence2 + '\n');
/* 23 */     outToServer.writeBytes(sentence3 + '\n');
/* 24 */     outToServer.writeBytes(sentence4 + '\n');
/* 25 */     outToServer.writeBytes(sentence5 + '\n');
/* 26 */     String modifiedSentence = inFromServer.readLine();
/* 27 */     String modifiedSentence2 = inFromServer.readLine();
/* 28 */     String modifiedSentence3 = inFromServer.readLine();
/* 29 */     String modifiedSentence4 = inFromServer.readLine();
/* 30 */     String modifiedSentence5 = inFromServer.readLine();
/* 31 */     System.out.println(modifiedSentence);
/* 32 */     System.out.println(modifiedSentence2);
/* 33 */     System.out.println(modifiedSentence3);
/* 34 */     System.out.println(modifiedSentence4);
/* 35 */     System.out.println(modifiedSentence5);
/* 36 */     clientSocket.close();
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/ClientSocketTest.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */