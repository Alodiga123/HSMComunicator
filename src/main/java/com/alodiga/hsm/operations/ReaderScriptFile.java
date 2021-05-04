/*     */ package com.alodiga.hsm.operations;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ 
/*     */ 
/*     */ public class ReaderScriptFile
/*     */ {
/*     */   public String result;
/*     */   public String errorMessage;
/*     */   public boolean error;
/*     */   
/*     */   public ReaderScriptFile()
/*     */   {
/*  17 */     this.error = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean leerTexto(InputStream str)
/*     */   {
/*  28 */     this.error = false;
/*  29 */     this.errorMessage = null;
/*     */     
/*     */ 
/*     */ 
/*  33 */     InputStreamReader reader = new InputStreamReader(str);
/*     */     
/*  35 */     char[] buffer = new char['Ɛ'];
/*     */     
/*  37 */     int chars_leidos = 0;
/*     */     
/*     */ 
/*  40 */     StringBuffer cadenacreciente = new StringBuffer(400);
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/*  46 */       while (reader.ready())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  51 */         chars_leidos = reader.read(buffer);
/*  52 */         if (chars_leidos > 0)
/*     */         {
/*     */ 
/*  55 */           cadenacreciente.append(buffer, 0, chars_leidos);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/*  63 */       this.errorMessage = exception.getMessage();
/*  64 */       this.error = true;
/*     */     }
/*     */     
/*  67 */     this.result = cadenacreciente.toString();
/*     */     
/*  69 */     return !this.error;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean leerTextoDeArchivo(String archivonombre)
/*     */   {
/*  80 */     this.result = "";
/*  81 */     this.error = false;
/*     */     
/*  83 */     File fil = new File(archivonombre);
/*  84 */     if (!fil.exists())
/*     */     {
/*  86 */       this.errorMessage = "El archivo no existe";
/*  87 */       this.error = true;
/*  88 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */   FileInputStream stream;
try {
	stream = new FileInputStream(fil);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
/*     */     try
/*     */     {
/*  95 */        stream = new FileInputStream(fil);
/*     */     }
/*     */     catch (Exception exception) {

/*  99 */       this.errorMessage = ("El archivo no existe : " + exception.getMessage());
/* 100 */       this.error = true;
/* 101 */       return false;
/*     */     }
/*     */     

/* 105 */     leerTexto(stream);
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 110 */       stream.close();
/*     */ 
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/* 115 */       this.errorMessage = ("Una excepcion tonta : " + exception.getMessage());
/*     */     }
/*     */     
/* 118 */     return !this.error;
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Operations/ReaderScriptFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */