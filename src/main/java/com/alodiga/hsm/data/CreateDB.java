/*     */ package com.alodiga.hsm.data;
/*     */ import java.io.File;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import javax.swing.JOptionPane;

import com.alodiga.hsm.operations.ReaderScriptFile;
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
/*     */ public class CreateDB
/*     */ {
/*  22 */   public static Connection install_conn = null;
/*  23 */   private static File miDir = new File(".");
/*  24 */   public static String ActualDirectory = miDir.getAbsolutePath();
/*     */   static String strConsultaSQL;
/*     */   static Statement estSQL1;
/*     */   
/*     */   public static boolean InstallDB(Connection conn)
/*     */   {
/*  30 */     ReaderScriptFile sqlreader = new ReaderScriptFile();
/*  31 */     sqlreader.leerTextoDeArchivo(miDir + "/Scripts/newCreateDb.sql");
/*     */     
/*  33 */     if (sqlreader.error)
/*     */     {
/*     */ 
/*  36 */       JOptionPane.showMessageDialog(null, sqlreader.errorMessage);
/*  37 */       return false;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*  42 */       strConsultaSQL = sqlreader.result;
/*  43 */       estSQL1 = conn.createStatement();
/*  44 */       estSQL1.execute(strConsultaSQL);
/*     */       
/*  46 */       estSQL1.close();
/*  47 */       return true;
/*     */     }
/*     */     catch (SQLException e) {
/*  50 */       JOptionPane.showMessageDialog(null, "Error Message :" + e.getMessage());
/*  51 */       e.printStackTrace();
/*     */     }
/*     */     
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean InstallDBRoles(Connection conn)
/*     */   {
/*  59 */     ReaderScriptFile sqlreader = new ReaderScriptFile();
/*  60 */     ReaderScriptFile sqlreader2 = new ReaderScriptFile();
/*  61 */     ReaderScriptFile sqlreader3 = new ReaderScriptFile();
/*  62 */     ReaderScriptFile sqlreader4 = new ReaderScriptFile();
/*  63 */     ReaderScriptFile sqlreader5 = new ReaderScriptFile();
/*  64 */     ReaderScriptFile sqlreader6 = new ReaderScriptFile();
/*     */     
/*  66 */     sqlreader.leerTextoDeArchivo(miDir + "/Scripts/CreateUsersAndRoles.sql");
/*  67 */     sqlreader2.leerTextoDeArchivo(miDir + "/Scripts/CreateSchemaAcces.sql");
/*  68 */     sqlreader3.leerTextoDeArchivo(miDir + "/Scripts/CreateSchemaConfig.sql");
/*  69 */     sqlreader4.leerTextoDeArchivo(miDir + "/Scripts/CreateSchemaMon.sql");
/*  70 */     sqlreader5.leerTextoDeArchivo(miDir + "/Scripts/CreateSchemaSecure.sql");
/*  71 */     sqlreader6.leerTextoDeArchivo(miDir + "/Scripts/CreateSchemaService.sql");
/*     */     
/*  73 */     if (sqlreader.error)
/*     */     {
/*     */ 
/*  76 */       JOptionPane.showMessageDialog(null, sqlreader.errorMessage);
/*  77 */       return false;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*  82 */       String strConsultaSQL2 = sqlreader2.result;
/*  83 */       String strConsultaSQL3 = sqlreader3.result;
/*  84 */       String strConsultaSQL4 = sqlreader4.result;
/*  85 */       String strConsultaSQL5 = sqlreader5.result;
/*  86 */       String strConsultaSQL6 = sqlreader6.result;
/*     */       
/*  88 */       strConsultaSQL = sqlreader.result;
/*  89 */       estSQL1 = conn.createStatement();
/*  90 */       estSQL1.execute(strConsultaSQL);
/*  91 */       estSQL1.execute(strConsultaSQL2);
/*  92 */       estSQL1.execute(strConsultaSQL3);
/*  93 */       estSQL1.execute(strConsultaSQL4);
/*  94 */       estSQL1.execute(strConsultaSQL5);
/*  95 */       estSQL1.execute(strConsultaSQL6);
/*  96 */       estSQL1.close();
/*     */       
/*  98 */       return true;
/*     */     }
/*     */     catch (SQLException e) {
/* 101 */       JOptionPane.showMessageDialog(null, "Error Message :" + e.getMessage());
/* 102 */       e.printStackTrace();
/*     */     }
/*     */     
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean InstallSP(Connection conn) {
/* 109 */     ReaderScriptFile sqlreader = new ReaderScriptFile();
/* 110 */     ReaderScriptFile sqlreader2 = new ReaderScriptFile();
/* 111 */     ReaderScriptFile sqlreader3 = new ReaderScriptFile();
/* 112 */     ReaderScriptFile sqlreader4 = new ReaderScriptFile();
/* 113 */     ReaderScriptFile sqlreader5 = new ReaderScriptFile();
/* 114 */     ReaderScriptFile sqlreader6 = new ReaderScriptFile();
/* 115 */     ReaderScriptFile sqlreader7 = new ReaderScriptFile();
/* 116 */     ReaderScriptFile sqlreader8 = new ReaderScriptFile();
/* 117 */     ReaderScriptFile sqlreader9 = new ReaderScriptFile();
/* 118 */     ReaderScriptFile sqlreader10 = new ReaderScriptFile();
/* 119 */     ReaderScriptFile sqlreader11 = new ReaderScriptFile();
/* 120 */     ReaderScriptFile sqlreader12 = new ReaderScriptFile();
/* 121 */     ReaderScriptFile sqlreader13 = new ReaderScriptFile();
/* 122 */     ReaderScriptFile sqlreader14 = new ReaderScriptFile();
/* 123 */     ReaderScriptFile sqlreader15 = new ReaderScriptFile();
/* 124 */     ReaderScriptFile sqlreader16 = new ReaderScriptFile();
/* 125 */     ReaderScriptFile sqlreader17 = new ReaderScriptFile();
/* 126 */     ReaderScriptFile sqlreader18 = new ReaderScriptFile();
/*     */     
/* 128 */     sqlreader.leerTextoDeArchivo(miDir + "/Scripts/SPDeleteCrypto.sql");
/* 129 */     sqlreader2.leerTextoDeArchivo(miDir + "/Scripts/SPGetCryptoCVKVIsa.sql");
/* 130 */     sqlreader3.leerTextoDeArchivo(miDir + "/Scripts/SPGetCryptogramFromTerminalID.sql");
/* 131 */     sqlreader4.leerTextoDeArchivo(miDir + "/Scripts/SPGetCryptoPVKIBM.sql");
/* 132 */     sqlreader5.leerTextoDeArchivo(miDir + "/Scripts/SPGetCryptoPVKVisa.sql");
/* 133 */     sqlreader6.leerTextoDeArchivo(miDir + "/Scripts/SPGetCryptosForTranslatetoKWP.sql");
/* 134 */     sqlreader7.leerTextoDeArchivo(miDir + "/Scripts/SPGetCryptosForTranslatetoMFK.sql");
/* 135 */     sqlreader8.leerTextoDeArchivo(miDir + "/Scripts/SPInsertCryptoCounter.sql");
/* 136 */     sqlreader9.leerTextoDeArchivo(miDir + "/Scripts/SPInsertCrypto.sql");
/* 137 */     sqlreader10.leerTextoDeArchivo(miDir + "/Scripts/SPIsServiceMemeber.sql");
/* 138 */     sqlreader11.leerTextoDeArchivo(miDir + "/Scripts/SPIsmemeber.sql");
/* 139 */     sqlreader12.leerTextoDeArchivo(miDir + "/Scripts/SPLoadKEK.sql");
/* 140 */     sqlreader13.leerTextoDeArchivo(miDir + "/Scripts/SPSecurity.sql");
/* 141 */     sqlreader14.leerTextoDeArchivo(miDir + "/Scripts/SPVerifyCSCert.sql");
/* 142 */     sqlreader15.leerTextoDeArchivo(miDir + "/Scripts/SPVerifyKEK.sql");
/* 143 */     sqlreader16.leerTextoDeArchivo(miDir + "/Scripts/SPLoadCryptoKEK.sql");
/*     */     
/*     */ 
/* 146 */     if (sqlreader.error)
/*     */     {
/*     */ 
/* 149 */       JOptionPane.showMessageDialog(null, sqlreader.errorMessage);
/* 150 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 157 */       strConsultaSQL = sqlreader.result;
/* 158 */       String strConsultaSQL2 = sqlreader2.result;
/* 159 */       String strConsultaSQL3 = sqlreader3.result;
/* 160 */       String strConsultaSQL4 = sqlreader4.result;
/* 161 */       String strConsultaSQL5 = sqlreader5.result;
/* 162 */       String strConsultaSQL6 = sqlreader6.result;
/* 163 */       String strConsultaSQL7 = sqlreader7.result;
/* 164 */       String strConsultaSQL8 = sqlreader8.result;
/* 165 */       String strConsultaSQL9 = sqlreader9.result;
/* 166 */       String strConsultaSQL10 = sqlreader10.result;
/* 167 */       String strConsultaSQL11 = sqlreader11.result;
/* 168 */       String strConsultaSQL12 = sqlreader12.result;
/* 169 */       String strConsultaSQL13 = sqlreader13.result;
/* 170 */       String strConsultaSQL14 = sqlreader14.result;
/* 171 */       String strConsultaSQL15 = sqlreader15.result;
/* 172 */       String strConsultaSQL16 = sqlreader16.result;
/*     */       
/* 174 */       estSQL1 = conn.createStatement();
/* 175 */       estSQL1.execute(strConsultaSQL);
/* 176 */       estSQL1.execute(strConsultaSQL2);
/* 177 */       estSQL1.execute(strConsultaSQL3);
/* 178 */       estSQL1.execute(strConsultaSQL4);
/* 179 */       estSQL1.execute(strConsultaSQL5);
/* 180 */       estSQL1.execute(strConsultaSQL6);
/* 181 */       estSQL1.execute(strConsultaSQL7);
/* 182 */       estSQL1.execute(strConsultaSQL8);
/* 183 */       estSQL1.execute(strConsultaSQL9);
/* 184 */       estSQL1.execute(strConsultaSQL10);
/* 185 */       estSQL1.execute(strConsultaSQL11);
/* 186 */       estSQL1.execute(strConsultaSQL12);
/* 187 */       estSQL1.execute(strConsultaSQL13);
/* 188 */       estSQL1.execute(strConsultaSQL14);
/* 189 */       estSQL1.execute(strConsultaSQL15);
/* 190 */       estSQL1.execute(strConsultaSQL16);
/* 191 */       estSQL1.close();
/*     */       
/* 193 */       return true;
/*     */     } catch (SQLException e) {
/* 195 */       JOptionPane.showMessageDialog(null, "Error Message :" + e.getMessage());
/*     */       
/* 197 */       e.printStackTrace();
/*     */     }
/*     */     
/* 200 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean CreateTables(Connection conn) {
/* 204 */     ReaderScriptFile sqlreader = new ReaderScriptFile();
/* 205 */     sqlreader.leerTextoDeArchivo(miDir + "/Scripts/newCreateTables.sql");
/*     */     
/* 207 */     if (sqlreader.error)
/*     */     {
/*     */ 
/* 210 */       JOptionPane.showMessageDialog(null, sqlreader.errorMessage);
/* 211 */       return false;
/*     */     }
/*     */     try
/*     */     {
/* 215 */       strConsultaSQL = sqlreader.result;
/* 216 */       estSQL1 = conn.createStatement();
/* 217 */       estSQL1.execute(strConsultaSQL);
/* 218 */       estSQL1.close();
/*     */       
/* 220 */       return true;
/*     */     } catch (SQLException e) {
/* 222 */       JOptionPane.showMessageDialog(null, "Error Message :" + e.getMessage());
/*     */       
/* 224 */       e.printStackTrace();
/*     */     }
/*     */     
/* 227 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean GrantPermissions(Connection conn) {
/* 231 */     ReaderScriptFile sqlreader = new ReaderScriptFile();
/* 232 */     sqlreader.leerTextoDeArchivo(miDir + "/Scripts/newGrantPermissions.sql");
/*     */     
/* 234 */     if (sqlreader.error)
/*     */     {
/*     */ 
/* 237 */       JOptionPane.showMessageDialog(null, sqlreader.errorMessage);
/* 238 */       return false;
/*     */     }
/*     */     try
/*     */     {
/* 242 */       strConsultaSQL = sqlreader.result;
/* 243 */       estSQL1 = conn.createStatement();
/* 244 */       estSQL1.execute(strConsultaSQL);
/* 245 */       estSQL1.close();
/*     */       
/* 247 */       return true;
/*     */     } catch (SQLException e) {
/* 249 */       JOptionPane.showMessageDialog(null, "Error Message :" + e.getMessage());
/*     */       
/* 251 */       e.printStackTrace();
/*     */     }
/*     */     
/* 254 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean RollBackInstallation(Connection conn) {
/* 258 */     ReaderScriptFile sqlreader = new ReaderScriptFile();
/* 259 */     sqlreader.leerTextoDeArchivo(miDir + "/Scripts/RollBackInstallation.sql");
/*     */     
/* 261 */     if (sqlreader.error)
/*     */     {
/*     */ 
/* 264 */       JOptionPane.showMessageDialog(null, sqlreader.errorMessage);
/* 265 */       return false;
/*     */     }
/*     */     try
/*     */     {
/* 269 */       strConsultaSQL = sqlreader.result;
/* 270 */       estSQL1 = conn.createStatement();
/* 271 */       estSQL1.execute(strConsultaSQL);
/* 272 */       estSQL1.close();
/*     */       
/* 274 */       return true;
/*     */     } catch (SQLException e) {
/* 276 */       JOptionPane.showMessageDialog(null, "Error Message :" + e.getMessage());
/*     */       
/* 278 */       e.printStackTrace();
/*     */     }
/*     */     
/* 281 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Databases/CreateDB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */