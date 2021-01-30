/*     */ package com.alodiga.hsm.operations;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class UseParameters
/*     */ {
/*     */   public static void setSaveParametersConfig(String ipsrv, String portsrv, String iphsm, String porthsm, String sqltype, String hsmtype, String akb, String use, String enviroment, String headermsg, String thalesmsgheader)
/*     */     throws IOException
/*     */   {
/*  14 */     Properties properties = new Properties();
/*  15 */     FileInputStream data_input = new FileInputStream("parameters.properties");
/*  16 */     properties.load(data_input);
/*  17 */     data_input.close();
/*     */     
/*  19 */     FileOutputStream data_output = new FileOutputStream("parameters.properties");
/*  20 */     properties.setProperty("IP_SERVER", ipsrv);
/*  21 */     properties.setProperty("PORT_SERVER", portsrv);
/*  22 */     properties.setProperty("IP_HSM", iphsm);
/*  23 */     properties.setProperty("PORT_HSM", porthsm);
/*  24 */     properties.setProperty("SQL_VERSION", sqltype);
/*  25 */     properties.setProperty("TYPE_HSM", hsmtype);
/*  26 */     properties.setProperty("KEY_BLOCK", akb);
/*  27 */     properties.setProperty("FIRST_USE", use);
/*  28 */     properties.setProperty("TEST_USE", enviroment);
/*  29 */     properties.setProperty("HEADER_MESSAGE", headermsg);
/*  30 */     properties.setProperty("THALES_MSG_HEADER", thalesmsgheader);
/*     */     
/*  32 */     properties.store(data_output, "/*-----------------Parameters File-------------------*/");
/*  33 */     data_output.close();
/*     */   }
/*     */   
/*     */   public static void setSaveCheckDigitKek(String Kek_Key) throws IOException
/*     */   {
/*  38 */     Properties properties = new Properties();
/*  39 */     FileInputStream data_input = new FileInputStream("parameters.properties");
/*  40 */     properties.load(data_input);
/*  41 */     data_input.close();
/*     */     
/*  43 */     FileOutputStream data_output = new FileOutputStream("parameters.properties");
/*  44 */     properties.setProperty("TRANSPORT_KEK_CHECK_VALUE", Kek_Key);
/*  45 */     properties.store(data_output, "/*-----------------Parameters File-------------------*/");
/*  46 */     data_output.close();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setSaveDBStatus(String isInstalled)
/*     */     throws IOException
/*     */   {
/*  53 */     Properties properties = new Properties();
/*  54 */     FileInputStream data_input = new FileInputStream("parameters.properties");
/*  55 */     properties.load(data_input);
/*  56 */     data_input.close();
/*     */     
/*  58 */     FileOutputStream data_output = new FileOutputStream("parameters.properties");
/*  59 */     properties.setProperty("IS_DB", isInstalled);
/*  60 */     properties.store(data_output, "/*-----------------Parameters File-------------------*/");
/*  61 */     data_output.close();
/*     */   }
/*     */   
/*     */ 
/*     */   public static ParametersConfig getParametersConfig()
/*     */     throws IOException
/*     */   {
/*  68 */     Properties properties = new Properties();
/*  69 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/*  70 */     properties.load(data_entry);
/*  71 */     data_entry.close();
/*     */     
/*  73 */     if (properties.getProperty("FIRST_USE").equals("YES")) {
/*  74 */       return new ParametersConfig(
/*  75 */         properties.getProperty("IP_SERVER"), 
/*  76 */         properties.getProperty("PORT_SERVER"), 
/*  77 */         properties.getProperty("IP_HSM"), 
/*  78 */         properties.getProperty("PORT_HSM"), 
/*  79 */         properties.getProperty("SQL_VERSION"), 
/*  80 */         properties.getProperty("TYPE_HSM"), 
/*  81 */         properties.getProperty("KEY_BLOCK"), 
/*  82 */         properties.getProperty("TEST_USE"), 
/*  83 */         properties.getProperty("HEADER_MESSAGE"), 
/*  84 */         properties.getProperty("THALES_MSG_HEADER"));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  89 */     System.out.println(properties.getProperty("IP_SERVER"));
/*  90 */     return new ParametersConfig(
/*  91 */       properties.getProperty("IP_SERVER"), 
/*  92 */       properties.getProperty("PORT_SERVER"), 
/*  93 */       properties.getProperty("IP_HSM"), 
/*  94 */       properties.getProperty("PORT_HSM"), 
/*  95 */       properties.getProperty("SQL_VERSION"), 
/*  96 */       properties.getProperty("TYPE_HSM"), 
/*  97 */       properties.getProperty("KEY_BLOCK"), 
/*  98 */       properties.getProperty("TEST_USE"), 
/*  99 */       properties.getProperty("HEADER_MESSAGE"), 
/* 100 */       properties.getProperty("THALES_MSG_HEADER"));
/*     */   }
/*     */   
/*     */ 
/*     */   public static HsmConfig getHSMConfig()
/*     */     throws IOException
/*     */   {
/* 107 */     Properties propertiescon = new Properties();
/* 108 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 109 */     propertiescon.load(data_entry);
/* 110 */     data_entry.close();
/*     */     
     return new HsmConfig(
/* 113 */       propertiescon.getProperty("IP_HSM"), 
/* 114 */       Integer.parseInt(propertiescon.getProperty("PORT_HSM")), 
/* 115 */       propertiescon.getProperty("HEADER_MESSAGE"));
/*     */   }
/*     */   
/*     */   public static CheckDigitKek getCheckDigitKek() throws IOException {
/* 119 */     Properties propertiescon = new Properties();
/* 120 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 121 */     propertiescon.load(data_entry);
/* 122 */     data_entry.close();
/*     */     
/* 124 */     return new CheckDigitKek(
/* 125 */       propertiescon.getProperty("TRANSPORT_KEK_CHECK_VALUE"));
/*     */   }
/*     */   
/*     */   public static TypeHSM getTypeHsm() throws IOException {
/* 129 */     Properties propertiescon = new Properties();
/* 130 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 131 */     propertiescon.load(data_entry);
/* 132 */     data_entry.close();
/*     */     
/* 134 */     return new TypeHSM(
/* 135 */       propertiescon.getProperty("TYPE_HSM"));
/*     */   }
/*     */   
/*     */   public static EnvironmentUsed getEnvriomentUsed() throws IOException {
/* 139 */     Properties propertiescon = new Properties();
/* 140 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 141 */     propertiescon.load(data_entry);
/* 142 */     data_entry.close();
/*     */     
/* 144 */     return new EnvironmentUsed(
/* 145 */       propertiescon.getProperty("TEST_USE"));
/*     */   }
/*     */   
/*     */   public static DBInstalled getDBInstalled() throws IOException
/*     */   {
/* 150 */     Properties propertiescon = new Properties();
/* 151 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 152 */     propertiescon.load(data_entry);
/* 153 */     data_entry.close();
/*     */     
/* 155 */     if (propertiescon.getProperty("FIRST_USE").equals("YES")) {
/* 156 */       return new DBInstalled(propertiescon.getProperty("IS_DB"));
/*     */     }
/* 158 */     return new DBInstalled(
/* 159 */       propertiescon.getProperty("IS_DB"));
/*     */   }
/*     */   
/*     */   public static HeaderThalesMsg getHeaderThalesMsg() throws IOException
/*     */   {
/* 164 */     Properties propertiescon = new Properties();
/* 165 */     FileInputStream data_entry = new FileInputStream("parameters.properties");
/* 166 */     propertiescon.load(data_entry);
/* 167 */     data_entry.close();
/*     */     
/* 169 */     return new HeaderThalesMsg(
/* 170 */       propertiescon.getProperty("THALES_MSG_HEADER"));
/*     */   }
/*     */   
/*     */ 
/*     */   public static class ParametersConfig
/*     */   {
/* 176 */     static String ipsrv = null;
/* 177 */     static String portsrv = null;
/* 178 */     static String iphsm = null;
/* 179 */     static String porthsm = null;
/* 180 */     static String sqltype = null;
/* 181 */     static String hsmtype = null;
/* 182 */     static String atallakb = null;
/* 183 */     static String environment = null;
/* 184 */     static String headermessage = null;
/* 185 */     static String headerthalesmsg = null;
/*     */     
/*     */     public ParametersConfig(String ipsrv, String portsrv, String iphsm, String porthsm, String sqltype, String hsmtype, String akb, String env, String headermsg, String headerthalesmsg)
/*     */     {
/* 189 */       ipsrv = ipsrv;
/* 190 */       portsrv = portsrv;
/* 191 */       iphsm = iphsm;
/* 192 */       porthsm = porthsm;
/* 193 */       sqltype = sqltype;
/* 194 */       hsmtype = hsmtype;
/* 195 */       atallakb = akb;
/* 196 */       environment = env;
/* 197 */       headermessage = headermsg;
/* 198 */       headerthalesmsg = headerthalesmsg;
/*     */     }
/*     */     
/*     */     public static String ipsrv() {
/* 202 */       return ipsrv;
/*     */     }
/*     */     
/*     */     public static String portsrv() {
/* 206 */       return portsrv;
/*     */     }
/*     */     
/* 209 */     public static String iphsm() { return iphsm; }
/*     */     
/*     */     public static String porthsm()
/*     */     {
/* 213 */       return porthsm;
/*     */     }
/*     */     
/* 216 */     public static String sqltype() { return sqltype; }
/*     */     
/*     */     public static String hsmtype()
/*     */     {
/* 220 */       return hsmtype;
/*     */     }
/*     */     
/* 223 */     public static String atallakb() { return atallakb; }
/*     */     
/*     */     public static String environment() {
/* 226 */       return environment;
/*     */     }
/*     */     
/* 229 */     public static String headermessage() { return headermessage; }
/*     */     
/*     */     public static String headerthalesmsg() {
/* 232 */       return headerthalesmsg;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HsmConfig
/*     */   {
/* 238 */     static String iphsmCon = null;
/* 239 */     static int porthsmCon = 0;
/* 240 */     static String headermessage = null;
/*     */     
/* 242 */     public HsmConfig(String iphsm, int porthsm, String headermsg) { iphsmCon = iphsm;
/* 243 */       porthsmCon = porthsm;
/* 244 */       headermessage = headermsg;
/*     */     }
/*     */     
/* 247 */     public static String iphsmCon() { return iphsmCon; }
/*     */     
/*     */     public static int porthsmCon()
/*     */     {
/* 251 */       return porthsmCon;
/*     */     }
/*     */     
/* 254 */     public static String headermessage() { return headermessage; }
/*     */   }
/*     */   
/*     */   public static class TypeHSM
/*     */   {
/* 259 */     static String typeHsm = null;
/*     */     
/* 261 */     public TypeHSM(String typehsm) { typeHsm = typehsm; }
/*     */     
/*     */     public static String typeHsm() {
/* 264 */       return typeHsm;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HeaderThalesMsg
/*     */   {
/* 270 */     static String headerthalesMsg = null;
/*     */     
/* 272 */     public HeaderThalesMsg(String headerthalesmsg) { headerthalesMsg = headerthalesmsg; }
/*     */     
/*     */     public static String headerthalesmsg() {
/* 275 */       return headerthalesMsg;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class CheckDigitKek
/*     */   {
/* 283 */     static String CheckDigit = null;
/*     */     
/* 285 */     public CheckDigitKek(String checkdigit) { CheckDigit = checkdigit; }
/*     */     
/*     */     public static String CheckDigit() {
/* 288 */       return CheckDigit;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class EnvironmentUsed
/*     */   {
/* 296 */     static String EnvironmentUsed = null;
/*     */     
/* 298 */     public EnvironmentUsed(String environmentuse) { EnvironmentUsed = environmentuse; }
/*     */     
/*     */     public static String EnvironmentUsed() {
/* 301 */       return EnvironmentUsed;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DBInstalled
/*     */   {
/* 307 */     static String dbInstalled = null;
/*     */     
/* 309 */     public DBInstalled(String FirstUse) { dbInstalled = FirstUse; }
/*     */     
/*     */     public static String dbInstalled() {
/* 312 */       return dbInstalled;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Operations/UseParameters.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */