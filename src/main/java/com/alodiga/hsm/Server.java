/*     */ package com.alodiga.hsm;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class Server
/*     */ {
/*     */   ConnectionManager connectionManager;
/*     */   Hashtable services;
/*     */   ThreadGroup threadGroup;
/*     */   PrintWriter logStream;
/*     */   
/*     */   public static void main(String[] args) {

/*     */ 
/*  38 */       Server s = new Server(System.out, 10);
/*     */       

/*     */    
/*     */   }
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
/*     */   public Server(OutputStream logStream, int maxConnections)
/*     */   {
				System.out.println("iniciando servidor");
/*  81 */     setLogStream(logStream);
/*  82 */     log("Starting server");
/*  83 */     this.threadGroup = new ThreadGroup("Server");
/*  84 */     this.connectionManager = new ConnectionManager(this.threadGroup, maxConnections);
/*  85 */     this.connectionManager.start();
/*  86 */     this.services = new Hashtable();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogStream(OutputStream out)
/*     */   {
/*  94 */     if (out != null) this.logStream = new PrintWriter(new OutputStreamWriter(out)); else {
/*  95 */       this.logStream = null;
/*     */     }
/*     */   }
/*     */   
/*     */   protected synchronized void log(String s) {
/* 100 */     if (this.logStream != null) {
/* 101 */       this.logStream.println("[" + new Date() + "] " + s);
/* 102 */       this.logStream.flush();
/*     */     }
/*     */   }
/*     */   
/* 106 */   protected void log(Object o) { log(o.toString()); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addService(Service service, int port)
/*     */     throws IOException
/*     */   {
/* 114 */     Integer key = new Integer(port);
/*     */     
/* 116 */     if (this.services.get(key) != null) {
/* 117 */       throw new IllegalArgumentException("Port " + port + " already in use.");
/*     */     }
/* 119 */     Listener listener = new Listener(this.threadGroup, port, service);
/*     */     
/* 121 */     this.services.put(key, listener);
/*     */     
/* 123 */     log("Starting service " + service.getClass().getName() + 
/* 124 */       " on port " + port);
/*     */     
/* 126 */     listener.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeService(int port)
/*     */   {
/* 135 */     Integer key = new Integer(port);
/*     */     
/* 137 */     Listener listener = (Listener)this.services.get(key);
/* 138 */     if (listener == null) { return;
/*     */     }
/* 140 */     listener.pleaseStop();
/*     */     
/* 142 */     this.services.remove(key);
/*     */     
/* 144 */     log("Stopping service " + listener.service.getClass().getName() + 
/* 145 */       " on port " + port);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public class Listener
/*     */     extends Thread
/*     */   {
/*     */     ServerSocket listen_socket;
/*     */     
/*     */ 
/*     */     int port;
/*     */     
/*     */     Server.Service service;
/*     */     
/* 160 */     boolean stop = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Listener(ThreadGroup group, int port, Server.Service service)
/*     */       throws IOException
/*     */     {
/* 170 */       super("Listener:" + port);
/* 171 */       this.listen_socket = new ServerSocket(port);
/*     */       
/* 173 */       this.listen_socket.setSoTimeout(600000);
/* 174 */       this.port = port;
/* 175 */       this.service = service;
/*     */     }
/*     */     
/*     */     public void pleaseStop()
/*     */     {
/* 180 */       this.stop = true;
/* 181 */       interrupt();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void run()
/*     */     {
/* 190 */       while (!this.stop) {
/*     */         try {
/* 192 */           Socket client = this.listen_socket.accept();
/* 193 */           Server.this.connectionManager.addConnection(client, this.service);
/*     */         }
/*     */         catch (InterruptedIOException localInterruptedIOException) {}catch (IOException e) {
/* 196 */           Server.this.log(e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public class ConnectionManager
/*     */     extends Thread
/*     */   {
/*     */     int maxConnections;
/*     */     
/*     */ 
/*     */     Vector connections;
/*     */     
/*     */ 
/*     */ 
/*     */     public ConnectionManager(ThreadGroup group, int maxConnections)
/*     */     {
/* 217 */       super("ConnectionManager");
/* 218 */       setDaemon(true);
/* 219 */       this.maxConnections = maxConnections;
/* 220 */       this.connections = new Vector(maxConnections);
/* 221 */       Server.this.log("Starting connection manager.  Max connections: " + maxConnections);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     synchronized void addConnection(Socket s, Server.Service service)
/*     */     {
/* 233 */       if (this.connections.size() >= this.maxConnections) {
/*     */         try {
/* 235 */           PrintWriter out = new PrintWriter(s.getOutputStream());
/*     */           
/* 237 */           out.println("Connection refused; server has reached maximum number of clients.");
/*     */           
/* 239 */           out.flush();
/*     */           
/* 241 */           s.close();
/*     */           
/* 243 */           Server.this.log("Connection refused to " + s.getInetAddress().getHostAddress() + 
/* 244 */             ":" + s.getPort() + ": max connections reached.");
/* 245 */         } catch (IOException e) { Server.this.log(e);
/*     */         }
/*     */       }
/*     */       else {
/* 249 */         Server.Connection c = new Server.Connection(s, service);
/*     */         
/* 251 */         this.connections.addElement(c);
/*     */         
/* 253 */         Server.this.log("Connected to " + s.getInetAddress().getHostAddress() + 
/* 254 */           ":" + s.getPort() + " on port " + s.getLocalPort() + 
/* 255 */           " for service " + service.getClass().getName());
/*     */         
/* 257 */         c.start();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized void endConnection()
/*     */     {
/* 266 */       notify();
/*     */     }
/*     */     
/* 269 */     public synchronized void setMaxConnections(int max) { this.maxConnections = max; }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized void printConnections(PrintWriter out)
/*     */     {
/* 276 */       for (int i = 0; i < this.connections.size(); i++) {
/* 277 */         Server.Connection c = (Server.Connection)this.connections.elementAt(i);
/* 278 */         out.println("CONNECTED TO " + 
/* 279 */           c.client.getInetAddress().getHostAddress() + ":" + 
/* 280 */           c.client.getPort() + " ON PORT " + c.client.getLocalPort() + 
/* 281 */           " FOR SERVICE " + c.service.getClass().getName());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void run()
/*     */     {
/*     */       for (;;)
/*     */       {
/* 296 */         for (int i = 0; i < this.connections.size(); i++) {
/* 297 */           Server.Connection c = (Server.Connection)this.connections.elementAt(i);
/* 298 */           if (!c.isAlive()) {
/* 299 */             this.connections.removeElementAt(i);
/* 300 */             Server.this.log("Connection to " + c.client.getInetAddress().getHostAddress() + 
/* 301 */               ":" + c.client.getPort() + " closed.");
/*     */           }
/*     */         }
/*     */         try
/*     */         {
/* 306 */           synchronized (this) { wait();
/*     */           }
/*     */         }
/*     */         catch (InterruptedException localInterruptedException) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public class Connection
/*     */     extends Thread
/*     */   {
/*     */     Socket client;
/*     */     
/*     */ 
/*     */ 
/*     */     Server.Service service;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Connection(Socket client, Server.Service service)
/*     */     {
/* 332 */       super();
/* 333 */       this.client = client;
/* 334 */       this.service = service;
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public void run()
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 52	HSM/Server$Connection:client	Ljava/net/Socket;
/*     */       //   4: invokevirtual 62	java/net/Socket:getInputStream	()Ljava/io/InputStream;
/*     */       //   7: astore_1
/*     */       //   8: aload_0
/*     */       //   9: getfield 52	HSM/Server$Connection:client	Ljava/net/Socket;
/*     */       //   12: invokevirtual 66	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
/*     */       //   15: astore_2
/*     */       //   16: aload_0
/*     */       //   17: getfield 54	HSM/Server$Connection:service	LHSM/Server$Service;
/*     */       //   20: aload_1
/*     */       //   21: aload_2
/*     */       //   22: invokeinterface 70 3 0
/*     */       //   27: goto +38 -> 65
/*     */       //   30: astore_1
/*     */       //   31: aload_0
/*     */       //   32: getfield 14	HSM/Server$Connection:this$0	LHSM/Server;
/*     */       //   35: aload_1
/*     */       //   36: invokevirtual 76	HSM/Server:log	(Ljava/lang/Object;)V
/*     */       //   39: aload_0
/*     */       //   40: getfield 14	HSM/Server$Connection:this$0	LHSM/Server;
/*     */       //   43: getfield 82	HSM/Server:connectionManager	LHSM/Server$ConnectionManager;
/*     */       //   46: invokevirtual 86	HSM/Server$ConnectionManager:endConnection	()V
/*     */       //   49: goto +26 -> 75
/*     */       //   52: astore_3
/*     */       //   53: aload_0
/*     */       //   54: getfield 14	HSM/Server$Connection:this$0	LHSM/Server;
/*     */       //   57: getfield 82	HSM/Server:connectionManager	LHSM/Server$ConnectionManager;
/*     */       //   60: invokevirtual 86	HSM/Server$ConnectionManager:endConnection	()V
/*     */       //   63: aload_3
/*     */       //   64: athrow
/*     */       //   65: aload_0
/*     */       //   66: getfield 14	HSM/Server$Connection:this$0	LHSM/Server;
/*     */       //   69: getfield 82	HSM/Server:connectionManager	LHSM/Server$ConnectionManager;
/*     */       //   72: invokevirtual 86	HSM/Server$ConnectionManager:endConnection	()V
/*     */       //   75: return
/*     */       // Line number table:
/*     */       //   Java source line #353	-> byte code offset #0
/*     */       //   Java source line #354	-> byte code offset #8
/*     */       //   Java source line #355	-> byte code offset #16
/*     */       //   Java source line #356	-> byte code offset #27
/*     */       //   Java source line #357	-> byte code offset #30
/*     */       //   Java source line #358	-> byte code offset #39
/*     */       //   Java source line #359	-> byte code offset #75
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	76	0	this	Connection
/*     */       //   7	14	1	in	InputStream
/*     */       //   30	6	1	e	IOException
/*     */       //   15	7	2	out	OutputStream
/*     */       //   52	12	3	localObject	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	27	30	java/io/IOException
/*     */       //   0	39	52	finally
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Time
/*     */     implements Server.Service
/*     */   {
/*     */     public void serve(InputStream i, OutputStream o)
/*     */       throws IOException
/*     */     {
/* 392 */       PrintWriter out = new PrintWriter(new OutputStreamWriter(o));
/* 393 */       out.println(new Date());
/* 394 */       out.close();
/* 395 */       i.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Reverse
/*     */     implements Server.Service
/*     */   {
/*     */     public void serve(InputStream i, OutputStream o)
/*     */       throws IOException
/*     */     {
/* 407 */       BufferedReader in = new BufferedReader(new InputStreamReader(i));
/* 408 */       PrintWriter out = 
/* 409 */         new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
/* 410 */       out.println("Welcome to the line reversal server.");
/* 411 */       out.println("Enter lines.  End with a '.' on a line by itself");
/*     */       for (;;) {
/* 413 */         out.print("> ");
/* 414 */         out.flush();
/* 415 */         String line = in.readLine();
/* 416 */         if ((line == null) || (line.equals("."))) break;
/* 417 */         for (int j = line.length() - 1; j >= 0; j--)
/* 418 */           out.print(line.charAt(j));
/* 419 */         out.println();
/*     */       }
/* 421 */       out.close();
/* 422 */       in.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class HTTPMirror
/*     */     implements Server.Service
/*     */   {
/*     */     public void serve(InputStream i, OutputStream o)
/*     */       throws IOException
/*     */     {
/* 433 */       BufferedReader in = new BufferedReader(new InputStreamReader(i));
/* 434 */       PrintWriter out = new PrintWriter(new OutputStreamWriter(o));
/* 435 */       out.println("HTTP/1.0 200 ");
/* 436 */       out.println("Content-Type: text/plain");
/* 437 */       out.println();
/*     */       String line;
/* 439 */       while ((line = in.readLine()) != null) { 
	 
/* 440 */         if (line.length() == 0) break;
/* 441 */         out.println(line);
/*     */       }
/* 443 */       out.close();
/* 444 */       in.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class UniqueID
/*     */     implements Server.Service
/*     */   {
/* 455 */     public int id = 0;
/* 456 */     public synchronized int nextId() { return this.id++; }
/*     */     
/* 458 */     public void serve(InputStream i, OutputStream o) throws IOException { PrintWriter out = new PrintWriter(new OutputStreamWriter(o));
/* 459 */       out.println("You are client #: " + nextId());
/* 460 */       out.close();
/* 461 */       i.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Control
/*     */     implements Server.Service
/*     */   {
/*     */     Server server;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     String password;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 487 */     boolean connected = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Control(Server server, String password)
/*     */     {
/* 496 */       this.server = server;
/* 497 */       this.password = password;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void serve(InputStream i, OutputStream o)
/*     */       throws IOException
/*     */     {
/* 508 */       BufferedReader in = new BufferedReader(new InputStreamReader(i));
/* 509 */       PrintWriter out = new PrintWriter(new OutputStreamWriter(o));
/*     */       
/* 511 */       boolean authorized = false;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 517 */       synchronized (this) {
/* 518 */         if (this.connected) {
/* 519 */           out.println("ONLY ONE CONTROL CONNECTION ALLOWED AT A TIME.");
/* 520 */           out.close();
/* 521 */           return;
/*     */         }
/* 523 */         this.connected = true;
/*     */       }
/*     */       for (;;)
/*     */       {
/* 527 */         out.print("> ");
/* 528 */         out.flush();
/* 529 */         String line = in.readLine();
/* 530 */         if (line == null)
/*     */           break;
/*     */         try {
/* 533 */           StringTokenizer t = new StringTokenizer(line);
/* 534 */           if (t.hasMoreTokens())
/*     */           {
/* 536 */             String command = t.nextToken().toLowerCase();
/*     */             
/*     */ 
/* 539 */             if (command.equals("password")) {
/* 540 */               String p = t.nextToken();
/* 541 */               if (p.equals(this.password)) {
/* 542 */                 out.println("OK");
/* 543 */                 authorized = true;
/*     */               } else {
/* 545 */                 out.println("INVALID PASSWORD");
/*     */               }
/* 547 */             } else if (command.equals("add"))
/*     */             {
/* 549 */               if (!authorized) { out.println("PASSWORD REQUIRED");
/*     */               }
/*     */               else
/*     */               {
/* 553 */                 String serviceName = t.nextToken();
/* 554 */                 Class serviceClass = Class.forName(serviceName);
/*     */                 Server.Service service = null;
/*     */                 try {
/* 556 */                    service = (Server.Service)serviceClass.newInstance();
/*     */                 } catch (NoSuchMethodError e) { 
/* 558 */                   throw new IllegalArgumentException("Service must have a no-argument constructor");
/*     */                 }

/* 561 */                 int port = Integer.parseInt(t.nextToken());
/*     */                 
/* 563 */                 this.server.addService(service, port);
/* 564 */                 out.println("SERVICE ADDED");
/*     */               }
/*     */             }
/* 567 */             else if (command.equals("remove")) {
/* 568 */               if (!authorized) { out.println("PASSWORD REQUIRED");
/*     */               } else {
/* 570 */                 int port = Integer.parseInt(t.nextToken());
/* 571 */                 this.server.removeService(port);
/* 572 */                 out.println("SERVICE REMOVED");
/*     */               }
/*     */             }
/* 575 */             else if (command.equals("max")) {
/* 576 */               if (!authorized) { out.println("PASSWORD REQUIRED");
/*     */               } else {
/* 578 */                 int max = Integer.parseInt(t.nextToken());
/* 579 */                 this.server.connectionManager.setMaxConnections(max);
/* 580 */                 out.println("MAX CONNECTIONS CHANGED");
/*     */               }
/*     */             }
/* 583 */             else if (command.equals("status")) {
/* 584 */               if (!authorized) { out.println("PASSWORD REQUIRED");
/*     */               }
/*     */               else {
/* 587 */                 Enumeration keys = this.server.services.keys();
/* 588 */                 while (keys.hasMoreElements()) {
/* 589 */                   Integer port = (Integer)keys.nextElement();
/* 590 */                   Server.Listener listener = (Server.Listener)this.server.services.get(port);
/* 591 */                   out.println("SERVICE " + listener.service.getClass().getName() + 
/* 592 */                     " ON PORT " + port);
/*     */                 }
/*     */                 
/* 595 */                 this.server.connectionManager.printConnections(out);
/*     */                 
/* 597 */                 out.println("MAX CONNECTIONS: " + 
/* 598 */                   this.server.connectionManager.maxConnections);
/*     */               }
/*     */             }
/* 601 */             else if (command.equals("help"))
/*     */             {
/* 603 */               out.println("COMMANDS:\n\tpassword <password>\n\tadd <service> <port>\n\tremove <port>\n\tmax <max-connections>\n\tstatus\n\thelp\n\tquit");
/*     */ 
/*     */ 
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/*     */ 
/*     */ 
/* 612 */               if (command.equals("quit")) break;
/* 613 */               out.println("UNRECOGNIZED COMMAND");
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 618 */           out.println("EXCEPTION WHILE PARSING OR EXECUTING COMMAND:");
/* 619 */           out.println(e);
/*     */         }
/*     */       }
/*     */       
/*     */       String line;
/*     */       
/* 625 */       out.close();
/* 626 */       in.close();
/* 627 */       this.connected = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract interface Service
/*     */   {
/*     */     public abstract void serve(InputStream paramInputStream, OutputStream paramOutputStream)
/*     */       throws IOException;
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/HSM/Server.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */