/*     */ package com.alodiga.hsm.data;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExecuteSP
/*     */ {
/*     */   public static String execInsertSp(String name, String key, String lenght, String hsm, String date, String environment, String checkdigit, Connection sp)
/*     */     throws SQLException
/*     */   {
/*  17 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call insertNewRegistry(?,?,?,?,?,?,?,?,?) }");
/*  18 */     prcProcedimientoAlmacenado.setString(1, key);
/*  19 */     prcProcedimientoAlmacenado.setString(2, name);
/*  20 */     prcProcedimientoAlmacenado.setString(3, hsm);
/*  21 */     prcProcedimientoAlmacenado.setString(4, lenght);
/*  22 */     prcProcedimientoAlmacenado.setString(5, date);
/*  23 */     prcProcedimientoAlmacenado.setString(6, environment);
/*  24 */     prcProcedimientoAlmacenado.setString(7, checkdigit);
/*  25 */     prcProcedimientoAlmacenado.registerOutParameter(8, 12);
/*  26 */     prcProcedimientoAlmacenado.registerOutParameter(9, 4);
/*     */     
/*  28 */     prcProcedimientoAlmacenado.execute();
/*     */     
/*  30 */     sp.commit();
/*  31 */     String confirmation = prcProcedimientoAlmacenado.getString(8);
/*  32 */     String verify = prcProcedimientoAlmacenado.getString(9);
/*  33 */     prcProcedimientoAlmacenado.close();
/*  34 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execDeleteSp(String id, String name, String key, String hsm, String date, Connection sp) throws SQLException
/*     */   {
/*  39 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call deleteRegistry(?,?,?,?,?,?,?) }");
/*  40 */     prcProcedimientoAlmacenado.setString(1, id);
/*  41 */     prcProcedimientoAlmacenado.setString(2, name);
/*  42 */     prcProcedimientoAlmacenado.setString(3, key);
/*  43 */     prcProcedimientoAlmacenado.setString(4, hsm);
/*  44 */     prcProcedimientoAlmacenado.setString(5, date);
/*  45 */     prcProcedimientoAlmacenado.registerOutParameter(6, 12);
/*  46 */     prcProcedimientoAlmacenado.registerOutParameter(7, 4);
/*     */     
/*  48 */     prcProcedimientoAlmacenado.execute();
/*     */     
/*  50 */     sp.commit();
/*  51 */     String confirmation = prcProcedimientoAlmacenado.getString(6);
/*  52 */     String verify = prcProcedimientoAlmacenado.getString(7);
/*  53 */     prcProcedimientoAlmacenado.close();
/*  54 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execDuplicateKey(String idkye, String name, String date, Connection sp)
/*     */     throws SQLException
/*     */   {
/*  60 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call duplicateRegistry(?,?,?,?,?) }");
/*  61 */     prcProcedimientoAlmacenado.setString(1, idkye);
/*  62 */     prcProcedimientoAlmacenado.setString(2, name);
/*  63 */     prcProcedimientoAlmacenado.setString(3, date);
/*  64 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/*  65 */     prcProcedimientoAlmacenado.registerOutParameter(5, 4);
/*     */     
/*  67 */     prcProcedimientoAlmacenado.execute();
/*     */     
/*  69 */     sp.commit();
/*  70 */     String confirmation = prcProcedimientoAlmacenado.getString(4);
/*  71 */     String verify = prcProcedimientoAlmacenado.getString(5);
/*  72 */     prcProcedimientoAlmacenado.close();
/*  73 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execIsmember(String user, Connection sp)
/*     */     throws SQLException
/*     */   {
/*  79 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call ismember_test(?,?) }");
/*  80 */     prcProcedimientoAlmacenado.setString(1, user);
/*  81 */     prcProcedimientoAlmacenado.registerOutParameter(2, 4);
/*  82 */     prcProcedimientoAlmacenado.execute();
/*  83 */     String role = prcProcedimientoAlmacenado.getString(2);
/*  84 */     prcProcedimientoAlmacenado.close();
/*  85 */     return role;
/*     */   }
/*     */   
/*     */   public static String execLoadTransportKek(Connection sp) throws SQLException
/*     */   {
/*  90 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call load_transportKek(?,?,?,?,?,?,?,?) }");
/*  91 */     prcProcedimientoAlmacenado.registerOutParameter(1, 4);
/*  92 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/*  93 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/*  94 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/*  95 */     prcProcedimientoAlmacenado.registerOutParameter(5, 12);
/*  96 */     prcProcedimientoAlmacenado.registerOutParameter(6, 12);
/*  97 */     prcProcedimientoAlmacenado.registerOutParameter(7, 12);
/*  98 */     prcProcedimientoAlmacenado.registerOutParameter(8, 12);
/*  99 */     prcProcedimientoAlmacenado.execute();
/* 100 */     String resultset = prcProcedimientoAlmacenado.getString(8) + "," + prcProcedimientoAlmacenado.getString(2) + "," + prcProcedimientoAlmacenado.getString(3) + 
/* 101 */       "," + prcProcedimientoAlmacenado.getString(4) + "," + prcProcedimientoAlmacenado.getString(5) + "," + prcProcedimientoAlmacenado.getString(6) + 
/* 102 */       "," + prcProcedimientoAlmacenado.getString(7) + "," + prcProcedimientoAlmacenado.getString(1);
/* 103 */     prcProcedimientoAlmacenado.close();
/* 104 */     return resultset;
/*     */   }
/*     */   
/*     */   public static String execInsertTransportKek(String transportkek, String CheckDigit, String hsmused, String date, String kekLenght, String keyblock, String environment, Connection sp) throws SQLException {
/* 108 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call insertTransportKek(?,?,?,?,?,?,?,?,?) }");
/* 109 */     prcProcedimientoAlmacenado.setString(1, transportkek);
/* 110 */     prcProcedimientoAlmacenado.setString(2, CheckDigit);
/* 111 */     prcProcedimientoAlmacenado.setString(3, hsmused);
/* 112 */     prcProcedimientoAlmacenado.setString(4, date);
/* 113 */     prcProcedimientoAlmacenado.setString(5, kekLenght);
/* 114 */     prcProcedimientoAlmacenado.setString(6, keyblock);
/* 115 */     prcProcedimientoAlmacenado.setString(7, environment);
/* 116 */     prcProcedimientoAlmacenado.registerOutParameter(8, 12);
/* 117 */     prcProcedimientoAlmacenado.registerOutParameter(9, 4);
/* 118 */     prcProcedimientoAlmacenado.execute();
/* 119 */     String confirmation = prcProcedimientoAlmacenado.getString(8);
/* 120 */     String verify = prcProcedimientoAlmacenado.getString(9);
/* 121 */     prcProcedimientoAlmacenado.close();
/* 122 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execLoadCryptoKEK(Connection sp)
/*     */     throws SQLException
/*     */   {
/* 128 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call load_crypto_transportKek(?) }");
/* 129 */     prcProcedimientoAlmacenado.registerOutParameter(1, 12);
/* 130 */     prcProcedimientoAlmacenado.execute();
/* 131 */     String resultset = prcProcedimientoAlmacenado.getString(1);
/* 132 */     prcProcedimientoAlmacenado.close();
/* 133 */     return resultset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String execVerifyKekCheckDigit(String checkSum, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 142 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call verifyCheckdigitKek(?,?,?) }");
/* 143 */     prcProcedimientoAlmacenado.setString(1, checkSum);
/* 144 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 145 */     prcProcedimientoAlmacenado.registerOutParameter(3, 4);
/* 146 */     prcProcedimientoAlmacenado.execute();
/* 147 */     String confirmation = prcProcedimientoAlmacenado.getString(2);
/* 148 */     String verify = prcProcedimientoAlmacenado.getString(3);
/* 149 */     prcProcedimientoAlmacenado.close();
/* 150 */     return verify;
/*     */   }
/*     */   
/*     */   public static String execInsertCheckSumCert(String checksum, String date, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 156 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call insert_checkdigit(?,?,?,?) }");
/* 157 */     prcProcedimientoAlmacenado.setString(1, checksum);
/* 158 */     prcProcedimientoAlmacenado.setString(2, date);
/* 159 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 160 */     prcProcedimientoAlmacenado.registerOutParameter(4, 4);
/* 161 */     prcProcedimientoAlmacenado.execute();
/* 162 */     String confirmation = prcProcedimientoAlmacenado.getString(3);
/* 163 */     String verify = prcProcedimientoAlmacenado.getString(4);
/* 164 */     prcProcedimientoAlmacenado.close();
/* 165 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execVerifyCheckSumCert(Connection sp) throws SQLException {
/* 169 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call verifyCheckSumCert(?,?) }");
/* 170 */     prcProcedimientoAlmacenado.registerOutParameter(1, 12);
/* 171 */     prcProcedimientoAlmacenado.registerOutParameter(2, 4);
/* 172 */     prcProcedimientoAlmacenado.execute();
/* 173 */     String confirmation = prcProcedimientoAlmacenado.getString(1);
/* 174 */     String verify = prcProcedimientoAlmacenado.getString(2);
/* 175 */     prcProcedimientoAlmacenado.close();
/* 176 */     return verify;
/*     */   }
/*     */   
/*     */   public static String execGetCheckSumCert(Connection sp) throws SQLException
/*     */   {
/* 181 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCheckSumCert(?) }");
/* 182 */     prcProcedimientoAlmacenado.registerOutParameter(1, 12);
/* 183 */     prcProcedimientoAlmacenado.execute();
/* 184 */     String checksumBd = prcProcedimientoAlmacenado.getString(1);
/* 185 */     prcProcedimientoAlmacenado.close();
/* 186 */     return checksumBd;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String execInsertCryptogram(String name, String crypto, String lenght, String hsm, String keyblock, String environment, String keytype, String checkdigit, String date, String Parity, String accuntNmbOffset, String maxPinLenght, String minPinLenght, String typeOfCvv, String expDateFormat, String pinVerfMethod, String pinBlockFormat, Connection sp, String user)
/*     */     throws SQLException
/*     */   {
/* 196 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call insertNewCryptogram(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
/* 197 */     prcProcedimientoAlmacenado.setString(1, name);
/* 198 */     prcProcedimientoAlmacenado.setString(2, crypto);
/* 199 */     prcProcedimientoAlmacenado.setString(3, lenght);
/* 200 */     prcProcedimientoAlmacenado.setString(4, hsm);
/* 201 */     prcProcedimientoAlmacenado.setString(5, keyblock);
/* 202 */     prcProcedimientoAlmacenado.setString(6, environment);
/* 203 */     prcProcedimientoAlmacenado.setString(7, keytype);
/* 204 */     prcProcedimientoAlmacenado.setString(8, checkdigit);
/* 205 */     prcProcedimientoAlmacenado.setString(9, date);
/* 206 */     prcProcedimientoAlmacenado.setString(10, Parity);
/* 207 */     prcProcedimientoAlmacenado.setString(11, accuntNmbOffset);
/* 208 */     prcProcedimientoAlmacenado.setString(12, maxPinLenght);
/* 209 */     prcProcedimientoAlmacenado.setString(13, minPinLenght);
/* 210 */     prcProcedimientoAlmacenado.setString(14, typeOfCvv);
/* 211 */     prcProcedimientoAlmacenado.setString(15, expDateFormat);
/* 212 */     prcProcedimientoAlmacenado.setString(16, pinVerfMethod);
/* 213 */     prcProcedimientoAlmacenado.setString(17, pinBlockFormat);
/* 214 */     prcProcedimientoAlmacenado.setString(18, user);
/* 215 */     prcProcedimientoAlmacenado.registerOutParameter(19, 12);
/* 216 */     prcProcedimientoAlmacenado.registerOutParameter(20, 4);
/*     */     
/* 218 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 220 */     sp.commit();
/* 221 */     String confirmation = prcProcedimientoAlmacenado.getString(19);
/* 222 */     String verify = prcProcedimientoAlmacenado.getString(20);
/* 223 */     prcProcedimientoAlmacenado.close();
/* 224 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execDeleteCryptogram(String id, String name, String crypto, String checkdigit, String leghtkey, Connection sp) throws SQLException
/*     */   {
/* 229 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call deleteCryptogram(?,?,?,?,?,?,?) }");
/* 230 */     prcProcedimientoAlmacenado.setString(1, id);
/* 231 */     prcProcedimientoAlmacenado.setString(2, name);
/* 232 */     prcProcedimientoAlmacenado.setString(3, crypto);
/* 233 */     prcProcedimientoAlmacenado.setString(4, checkdigit);
/* 234 */     prcProcedimientoAlmacenado.setString(5, leghtkey);
/* 235 */     prcProcedimientoAlmacenado.registerOutParameter(6, 12);
/* 236 */     prcProcedimientoAlmacenado.registerOutParameter(7, 4);
/*     */     
/* 238 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 240 */     sp.commit();
/* 241 */     String confirmation = prcProcedimientoAlmacenado.getString(6);
/* 242 */     String verify = prcProcedimientoAlmacenado.getString(7);
/* 243 */     prcProcedimientoAlmacenado.close();
/* 244 */     return confirmation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String execIsmemberofSecurity(Connection sp)
/*     */     throws SQLException
/*     */   {
/* 252 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call ismemberSecurity(?) }");
/* 253 */     prcProcedimientoAlmacenado.registerOutParameter("intanswer", 4);
/* 254 */     prcProcedimientoAlmacenado.execute();
/* 255 */     String role = prcProcedimientoAlmacenado.getString("intanswer");
/* 256 */     prcProcedimientoAlmacenado.close();
/* 257 */     return role;
/*     */   }
/*     */   
/*     */   public static String execInsertCryptogramsCounter(int clearKeyid, Connection sp) throws SQLException
/*     */   {
/* 262 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call insertCryptogramsCounter(?,?,?) }");
/* 263 */     prcProcedimientoAlmacenado.setInt(1, clearKeyid);
/* 264 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 265 */     prcProcedimientoAlmacenado.registerOutParameter(3, 4);
/* 266 */     prcProcedimientoAlmacenado.execute();
/* 267 */     String confirmation = prcProcedimientoAlmacenado.getString(2);
/* 268 */     String verify = prcProcedimientoAlmacenado.getString(3);
/* 269 */     prcProcedimientoAlmacenado.close();
/* 270 */     return confirmation;
/*     */   }
/*     */   
/* 273 */   public static String execDeleteCryptogramsCounter(int clearKeyid, Connection sp) throws SQLException { CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call deleteCryptogramsCounter(?,?,?) }");
/* 274 */     prcProcedimientoAlmacenado.setInt(1, clearKeyid);
/* 275 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 276 */     prcProcedimientoAlmacenado.registerOutParameter(3, 4);
/* 277 */     prcProcedimientoAlmacenado.execute();
/* 278 */     String confirmation = prcProcedimientoAlmacenado.getString(2);
/* 279 */     String verify = prcProcedimientoAlmacenado.getString(3);
/* 280 */     prcProcedimientoAlmacenado.close();
/* 281 */     return confirmation;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String execInsertCuatodian(String name, String lastname, String age, String address, String active, String email, String id, String Phone, String LinkUser, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 288 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call insertNewCustodian(?,?,?,?,?,?,?,?,?,?,?) }");
/* 289 */     prcProcedimientoAlmacenado.setString(1, name);
/* 290 */     prcProcedimientoAlmacenado.setString(2, lastname);
/* 291 */     prcProcedimientoAlmacenado.setString(3, age);
/* 292 */     prcProcedimientoAlmacenado.setString(4, id);
/* 293 */     prcProcedimientoAlmacenado.setString(5, address);
/* 294 */     prcProcedimientoAlmacenado.setString(6, Phone);
/* 295 */     prcProcedimientoAlmacenado.setString(7, email);
/* 296 */     prcProcedimientoAlmacenado.setString(8, active);
/* 297 */     prcProcedimientoAlmacenado.setString(9, LinkUser);
/* 298 */     prcProcedimientoAlmacenado.registerOutParameter(10, 12);
/* 299 */     prcProcedimientoAlmacenado.registerOutParameter(11, 4);
/*     */     
/* 301 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 303 */     sp.commit();
/* 304 */     String confirmation = prcProcedimientoAlmacenado.getString(10);
/* 305 */     String verify = prcProcedimientoAlmacenado.getString(11);
/* 306 */     prcProcedimientoAlmacenado.close();
/* 307 */     return confirmation;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String execDeleteCuatodian(String id, String identificationNumber, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 314 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call deleteCustodian(?,?,?,?) }");
/* 315 */     prcProcedimientoAlmacenado.setString(1, id);
/* 316 */     prcProcedimientoAlmacenado.setString(2, identificationNumber);
/* 317 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 318 */     prcProcedimientoAlmacenado.registerOutParameter(4, 4);
/*     */     
/* 320 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 322 */     sp.commit();
/* 323 */     String confirmation = prcProcedimientoAlmacenado.getString(3);
/* 324 */     String verify = prcProcedimientoAlmacenado.getString(4);
/* 325 */     prcProcedimientoAlmacenado.close();
/* 326 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execLinkUserCustodian(String updateCustodian, String inserUser, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 332 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call linkUserCustodian(?,?,?,?) }");
/* 333 */     prcProcedimientoAlmacenado.setString(1, updateCustodian);
/* 334 */     prcProcedimientoAlmacenado.setString(2, inserUser);
/* 335 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 336 */     prcProcedimientoAlmacenado.registerOutParameter(4, 4);
/*     */     
/* 338 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 340 */     sp.commit();
/* 341 */     String confirmation = prcProcedimientoAlmacenado.getString(3);
/* 342 */     String verify = prcProcedimientoAlmacenado.getString(4);
/* 343 */     prcProcedimientoAlmacenado.close();
/* 344 */     return confirmation;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String execModifyCustodian(String id, String name, String lastname, String age, String address, String active, String email, String idNumber, String Phone, String LinkUser, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 351 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call ModifyCustodian(?,?,?,?,?,?,?,?,?,?,?,?) }");
/* 352 */     prcProcedimientoAlmacenado.setString(1, name);
/* 353 */     prcProcedimientoAlmacenado.setString(2, lastname);
/* 354 */     prcProcedimientoAlmacenado.setString(3, age);
/* 355 */     prcProcedimientoAlmacenado.setString(4, idNumber);
/* 356 */     prcProcedimientoAlmacenado.setString(5, address);
/* 357 */     prcProcedimientoAlmacenado.setString(6, Phone);
/* 358 */     prcProcedimientoAlmacenado.setString(7, email);
/* 359 */     prcProcedimientoAlmacenado.setString(8, active);
/* 360 */     prcProcedimientoAlmacenado.setString(9, LinkUser);
/* 361 */     prcProcedimientoAlmacenado.setString(10, id);
/* 362 */     prcProcedimientoAlmacenado.registerOutParameter(11, 12);
/* 363 */     prcProcedimientoAlmacenado.registerOutParameter(12, 4);
/*     */     
/* 365 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 367 */     sp.commit();
/* 368 */     String confirmation = prcProcedimientoAlmacenado.getString(11);
/* 369 */     String verify = prcProcedimientoAlmacenado.getString(12);
/* 370 */     prcProcedimientoAlmacenado.close();
/* 371 */     return confirmation;
/*     */   }
/*     */   
/*     */   public static String execUnLinkUserCustodian(String updateCustodian, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 377 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call UnlinkUserCustodian(?,?,?) }");
/* 378 */     prcProcedimientoAlmacenado.setString(1, updateCustodian);
/* 379 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 380 */     prcProcedimientoAlmacenado.registerOutParameter(3, 4);
/*     */     
/* 382 */     prcProcedimientoAlmacenado.execute();
/*     */     
/* 384 */     sp.commit();
/* 385 */     String confirmation = prcProcedimientoAlmacenado.getString(2);
/* 386 */     String verify = prcProcedimientoAlmacenado.getString(3);
/* 387 */     prcProcedimientoAlmacenado.close();
/* 388 */     return confirmation;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String execIsServicemember(String user, Connection sp)
/*     */     throws SQLException
/*     */   {
/* 395 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call ismemberservice(?,?) }");
/* 396 */     prcProcedimientoAlmacenado.setString(1, user);
/* 397 */     prcProcedimientoAlmacenado.registerOutParameter(2, 4);
/* 398 */     prcProcedimientoAlmacenado.execute();
/* 399 */     String role = prcProcedimientoAlmacenado.getString(2);
/* 400 */     prcProcedimientoAlmacenado.close();
/* 401 */     return role;
/*     */   }
/*     */   
/*     */   public static String execGetCryptoFromTerminalID(String terminalID, Connection sp) throws SQLException {
/* 405 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCryptogramFromTerminalID(?,?,?,?) }");
/* 406 */     prcProcedimientoAlmacenado.setString(1, terminalID);
/* 407 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 408 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 409 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/* 410 */     prcProcedimientoAlmacenado.execute();
/* 411 */     String resultset = prcProcedimientoAlmacenado.getString(2) + "," + prcProcedimientoAlmacenado.getString(3) + "," + prcProcedimientoAlmacenado.getString(4);
/* 412 */     prcProcedimientoAlmacenado.close();
/* 413 */     return resultset;
/*     */   }
/*     */   
/*     */   public static String execGetCryptoPvkVisa(Connection sp) throws SQLException {
/* 417 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCryptoPVKVisa(?,?,?,?) }");
/* 418 */     prcProcedimientoAlmacenado.registerOutParameter(1, 12);
/* 419 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 420 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 421 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/* 422 */     prcProcedimientoAlmacenado.execute();
/* 423 */     String resultset = prcProcedimientoAlmacenado.getString(1) + "," + prcProcedimientoAlmacenado.getString(2) + "," + prcProcedimientoAlmacenado.getString(3) + "," + prcProcedimientoAlmacenado.getString(4);
/* 424 */     prcProcedimientoAlmacenado.close();
/* 425 */     return resultset;
/*     */   }
/*     */   
/*     */   public static String execGetCryptoPvkIBM(Connection sp) throws SQLException
/*     */   {
/* 430 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCryptoPVKIBM(?,?,?,?) }");
/* 431 */     prcProcedimientoAlmacenado.registerOutParameter(1, 12);
/* 432 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 433 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 434 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/* 435 */     prcProcedimientoAlmacenado.execute();
/* 436 */     String resultset = prcProcedimientoAlmacenado.getString(1) + "," + prcProcedimientoAlmacenado.getString(2) + "," + prcProcedimientoAlmacenado.getString(3) + "," + prcProcedimientoAlmacenado.getString(4);
/* 437 */     prcProcedimientoAlmacenado.close();
/* 438 */     return resultset;
/*     */   }
/*     */   
/*     */   public static String execGetCryptoVisaCVV(String typeOfCVV, Connection sp) throws SQLException {
/* 442 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCryptoVisaCVV(?,?,?,?) }");
/* 443 */     System.out.println("Valor del CVV Recivido despues de invocar ExecuteSP" + typeOfCVV);
/* 444 */     String typeOfCvvfinal = null;
/*     */     
/* 446 */     if (typeOfCVV.equals("CVV")) {
/* 447 */       typeOfCvvfinal = "1";
/*     */     }
/* 449 */     if (typeOfCVV.equals("CVV2")) {
/* 450 */       typeOfCvvfinal = "2";
/*     */     }
/* 452 */     if (typeOfCVV.equals("iCVV")) {
/* 453 */       typeOfCvvfinal = "3";
/*     */     }
/*     */     else {
/* 456 */       typeOfCvvfinal = "0";
/*     */     }
/*     */     
/* 459 */     System.out.println("Valor del CVV Recivido antes de invocar BD" + typeOfCvvfinal);
/* 460 */     prcProcedimientoAlmacenado.setString(1, typeOfCvvfinal);
/* 461 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 462 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 463 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/* 464 */     prcProcedimientoAlmacenado.execute();
/* 465 */     String resultset = prcProcedimientoAlmacenado.getString(2) + "," + prcProcedimientoAlmacenado.getString(3) + "," + prcProcedimientoAlmacenado.getString(4);
/* 466 */     prcProcedimientoAlmacenado.close();
/* 467 */     System.out.println("Valor del Resultset" + resultset);
/* 468 */     return resultset;
/*     */   }
/*     */   
/*     */   public static String execGetCryptoForTranslatetoKWP(String terminalIdorigen, String terminalIdDestino, Connection sp) throws SQLException {
/* 472 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCryptosForTranslatetoKWP(?,?,?,?,?,?,?,?,?) }");
/* 473 */     prcProcedimientoAlmacenado.setString(1, terminalIdorigen);
/* 474 */     prcProcedimientoAlmacenado.setString(2, terminalIdorigen);
/* 475 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 476 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/* 477 */     prcProcedimientoAlmacenado.registerOutParameter(5, 12);
/* 478 */     prcProcedimientoAlmacenado.registerOutParameter(6, 12);
/* 479 */     prcProcedimientoAlmacenado.registerOutParameter(7, 12);
/* 480 */     prcProcedimientoAlmacenado.registerOutParameter(8, 12);
/* 481 */     prcProcedimientoAlmacenado.registerOutParameter(9, 12);
/* 482 */     prcProcedimientoAlmacenado.execute();
/* 483 */     String resultset = prcProcedimientoAlmacenado.getString(3) + "," + prcProcedimientoAlmacenado.getString(4) + "," + prcProcedimientoAlmacenado.getString(5) + 
/* 484 */       "," + prcProcedimientoAlmacenado.getString(6) + "," + prcProcedimientoAlmacenado.getString(7) + "," + prcProcedimientoAlmacenado.getString(8) + "," + prcProcedimientoAlmacenado.getString(9);
/* 485 */     prcProcedimientoAlmacenado.close();
/* 486 */     return resultset;
/*     */   }
/*     */   
/*     */   public static String execGetCryptoForTranslatetoMFK(String terminalId, Connection sp) throws SQLException {
/* 490 */     CallableStatement prcProcedimientoAlmacenado = sp.prepareCall("{ call getCryptosForTranslatetoMFK(?,?,?,?,?) }");
/* 491 */     prcProcedimientoAlmacenado.setString(1, terminalId);
/* 492 */     prcProcedimientoAlmacenado.registerOutParameter(2, 12);
/* 493 */     prcProcedimientoAlmacenado.registerOutParameter(3, 12);
/* 494 */     prcProcedimientoAlmacenado.registerOutParameter(4, 12);
/* 495 */     prcProcedimientoAlmacenado.registerOutParameter(5, 12);
/* 496 */     prcProcedimientoAlmacenado.execute();
/* 497 */     String resultset = prcProcedimientoAlmacenado.getString(2) + "," + prcProcedimientoAlmacenado.getString(3) + "," + prcProcedimientoAlmacenado.getString(4) + 
/* 498 */       "," + prcProcedimientoAlmacenado.getString(5);
/* 499 */     prcProcedimientoAlmacenado.close();
/*     */     
/* 501 */     return resultset;
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Databases/ExecuteSP.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */