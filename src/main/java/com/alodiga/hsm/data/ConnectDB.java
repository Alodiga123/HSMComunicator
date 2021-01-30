/*     */ package com.alodiga.hsm.data;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
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
/*     */ public class ConnectDB
/*     */ {
/*  29 */   static String strUsuario = null;
/*  30 */   static String strPassword = null;
/*  31 */   static String strBaseDatos = null;
/*  32 */   static String strHost = null;
/*  33 */   static String strPort = null;
/*  34 */   static String Driver = null;
/*     */   
/*     */   public ConnectDB(String usr, String pw, String bd)
/*     */   {
/*  38 */     strUsuario = usr;
/*  39 */     strPassword = pw;
/*  40 */     strBaseDatos = bd;
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  45 */       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/*  48 */       System.out.println("ERROR: Error al cargar la clase del Driver");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ConnectDB(String usr, String pw, String bd, String srvr, String prt)
/*     */   {
/*  59 */     strUsuario = usr;
/*  60 */     strPassword = pw;
/*  61 */     strBaseDatos = bd;
/*  62 */     strHost = srvr;
/*  63 */     strPort = prt;
/*     */     
/*     */     try
/*     */     {
/*  67 */       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/*  70 */       System.out.println("ERROR: Error al cargar la clase del Driver\n");
/*  71 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public ConnectDB()
/*     */   {
/*     */     try
/*     */     {
/*  79 */       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/*  82 */       System.out.println("ERROR: Error al cargar la clase del Driver");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Connection GetConnection()
/*     */     throws SQLException
/*     */   {
/*  91 */     String url = "jdbc:sqlserver://" + strHost + ":" + strPort + ";databaseName=" + strBaseDatos + ";instance=MSSQLServer";
/*  92 */     return DriverManager.getConnection(url, strUsuario, strPassword);
/*     */   }
/*     */   
/*     */   public static void setLoginTimeout(int seconds) throws SQLException
/*     */   {
/*  97 */     DriverManager.setLoginTimeout(seconds);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Connection GetDefaultConnection()
/*     */     throws SQLException
/*     */   {
/* 107 */     String url = "jdbc:sqlserver://" + strHost + ":" + strPort + ";databaseName=" + strBaseDatos + ";instance=MSSQLServer";
/* 108 */     return DriverManager.getConnection(url, strUsuario, strPassword);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void cerrar(ResultSet rs)
/*     */   {
/*     */     try
/*     */     {
/* 119 */       rs.close();
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void cerrar(Statement st)
/*     */   {
/*     */     try
/*     */     {
/* 130 */       st.close();
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void cerrar(Connection con)
/*     */   {
/*     */     try
/*     */     {
/* 141 */       con.close();
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Databases/ConnectDB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */