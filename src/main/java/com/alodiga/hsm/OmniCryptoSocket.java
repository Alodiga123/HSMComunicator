/*     */ package com.alodiga.hsm;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.Vector;

/*     */ 
/*     */ public class OmniCryptoSocket
/*     */   implements Runnable
/*     */ {
/*     */   Socket linkto;
/*     */   PrintWriter printOut;
/*     */   BufferedReader in;
/*     */   OutputStreamWriter out;
/*     */   int id;
/*     */   String from_name;
/*     */   static Vector connectiontable;
/*  47 */   static int nextid = 1;
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  53 */     ServerSocket ss = null;
/*  54 */     Socket s = null;
/*  55 */     connectiontable = new Vector();
/*     */     try
/*     */     {
/*  58 */       System.out.println("Listening");
/*  59 */       ss = new ServerSocket(14250);
/*  60 */       while ((s = ss.accept()) != null)
/*     */       {
/*     */         OmniCryptoSocket now;
/*     */         
/*  64 */         Thread current = new Thread(now = new OmniCryptoSocket(s));
/*  65 */         current.setDaemon(true);
/*  66 */         connectiontable.addElement(now);
/*  67 */         current.start();
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  71 */       System.out.println(e);
/*  72 */       System.out.println("Unable to Connect");
/*     */     }
/*     */   }

/*     */   OmniCryptoSocket(Socket from)
/*     */   {
/*  80 */     this.id = (nextid++);
/*  81 */     this.linkto = from;
/*  82 */     InetAddress source = this.linkto.getInetAddress();
/*  83 */     this.from_name = source.getHostName();
/*     */     try {
/*  85 */       this.printOut = new PrintWriter(new OutputStreamWriter(
/*  86 */         this.linkto.getOutputStream()));
/*  87 */       this.in = new BufferedReader(new InputStreamReader(
/*  88 */         this.linkto.getInputStream()));
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/*  97 */     String line = null;
/*     */     for (;;)
/*     */     {
/* 100 */       System.out.println("Connected");
/* 101 */       boolean done = false;
/* 105 */       if (line == null) {
/*     */         try {
/* 107 */           this.printOut.print(" ");
/* 108 */           this.printOut.flush();
/* 109 */           line = this.in.readLine();
/*     */         } catch (Exception e) {
/* 111 */           System.out.println(e);
/* 112 */           done = true;
/*     */         }
/*     */       }
/*     */       try
/*     */       {
/* 121 */         if (line.startsWith("exit")) done = true;
/*     */       }
/*     */       catch (Exception e) {
/* 124 */         done = true;
/* 125 */         line = "[exiting]";
/*     */       }
/*     */       
/* 128 */       if ((line.startsWith("00")) || (line.startsWith("01")) || (line.startsWith("02")) || (line.startsWith("03")) || (line.startsWith("04")) || 
/* 129 */         (line.startsWith("05")) || (line.startsWith("06")) || (line.startsWith("07")) || (line.startsWith("08")) || 
/* 130 */         (line.startsWith("09")) || (line.startsWith("10")) || (line.startsWith("11")) || (line.startsWith("12")) || 
/* 131 */         (line.startsWith("13")) || (line.startsWith("14")) || (line.startsWith("15")) || (line.startsWith("16")))
/*     */       {
/* 133 */         String command = line.substring(0, 2);
/*     */         
/*     */ 
/*     */ 
/*     */         try
/*     */         {
/* 139 */           System.out.println("Comando Recivido: " + line);
/* 140 */           this.printOut.println(OmniCryptoCommand.ProcessRequest(Integer.parseInt(command), line));
/*     */         } catch (Exception e) {
/* 142 */           this.printOut.println("Z2,Please Verify the input data");
/*     */           
/* 144 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 149 */         this.printOut.println("Z1,Invalid Parameter");
/*     */       }
/*     */ 
/* 172 */       if (done) {
/* 173 */         connectiontable.removeElement(this);
/*     */         try {
/* 175 */           this.printOut.close();
/* 176 */           this.in.close();
/* 177 */           this.linkto.close();
/*     */         }
/*     */         catch (Exception localException1) {}
/*     */       }
/* 181 */       line = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/OmniCryptoSocket.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */