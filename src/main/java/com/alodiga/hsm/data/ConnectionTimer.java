/*    */ package com.alodiga.hsm.data;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConnectionTimer
/*    */ {
/*    */   public static Timer StartTimer()
/*    */   {
/* 16 */     return new Timer("START");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static Timer StopTimer()
/*    */   {
/* 23 */     return new Timer("STOP");
/*    */   }
/*    */   
/*    */ 
/*    */   public static class Timer
/*    */   {
/* 29 */     static String FinalTimer = null;
/* 30 */     Scanner input = new Scanner(System.in);
/* 31 */     int endtimer = 1;
/*    */     
/*    */     public Timer(String starttimer) {
/* 34 */       int interval = 15;
/* 35 */       long startTime = System.currentTimeMillis();
/* 36 */       long endTime = System.currentTimeMillis() + interval * 1000;
/* 37 */       if (starttimer.equals("START")) {
/* 38 */         while (System.currentTimeMillis() < endTime)
/*    */         {
/* 40 */           if ((endTime - System.currentTimeMillis()) % 1000L == 0L) {
/* 41 */             System.out.println((endTime - System.currentTimeMillis()) / 1000L + " seconds remaining.");
/* 42 */             this.endtimer = ((int)(endTime - System.currentTimeMillis()) / 1000);
/*    */           }
/*    */         }
/*    */       }
/* 46 */       starttimer.equals("STOP");
/*    */       
/*    */ 
/*    */ 
/* 50 */       System.out.println("Stopped." + endTime);
/* 51 */       if (this.endtimer == 0) {
/* 52 */         FinalTimer = "CLOSE";
/*    */       }
/*    */     }
/*    */     
/*    */     public static String FinalTimer()
/*    */     {
/* 58 */       return FinalTimer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Databases/ConnectionTimer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */