/*    */ package com.alodiga.hsm.data;
/*    */ 
/*    */ import java.awt.EventQueue;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.WindowEvent;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.Timer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CountDown
/*    */   implements ActionListener, PropertyChangeListener
/*    */ {
/*    */   private static final int TIME_OUT = 30;
/* 22 */   private int count = 30;
/* 23 */   private final Timer timer = new Timer(1000, this);
/* 24 */   private JDialog dialog = new JDialog();
/* 25 */   private final JOptionPane optPane = new JOptionPane();
/*    */   
/*    */   public static void main(String[] args) {
/* 28 */     EventQueue.invokeLater(new Runnable()
/*    */     {
/*    */       public void run() {
/* 31 */         new CountDown().StartTimer();
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   private void StartTimer() {
/* 37 */     JFrame frame = new JFrame("Title");
/* 38 */     frame.setDefaultCloseOperation(3);
/* 39 */     frame.setLocationByPlatform(true);
/* 40 */     this.timer.setCoalesce(false);
/* 41 */     this.optPane.setMessage(message());
/* 42 */     this.optPane.setMessageType(1);
/* 43 */     this.optPane.setOptionType(-1);
/* 44 */     this.optPane.addPropertyChangeListener(this);
/* 45 */     this.dialog.add(this.optPane);
/* 46 */     this.dialog.pack();
/* 47 */     frame.add(new JLabel(frame.getTitle(), 0));
/* 48 */     frame.setVisible(true);
/* 49 */     this.dialog.setLocationRelativeTo(frame);
/* 50 */     this.dialog.setVisible(true);
/* 51 */     this.timer.start();
/*    */   }
/*    */   
/*    */   public void propertyChange(PropertyChangeEvent e) {
/* 55 */     String prop = e.getPropertyName();
/* 56 */     if ("value".equals(prop)) {
/* 57 */       thatsAllFolks();
/*    */     }
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 62 */     this.count -= 1;
/* 63 */     this.optPane.setMessage(message());
/* 64 */     if (this.count == 0) {
/* 65 */       thatsAllFolks();
/*    */     }
/* 67 */     this.timer.restart();
/*    */   }
/*    */   
/*    */   private String message() {
/* 71 */     return "Closing in " + this.count + " seconds.";
/*    */   }
/*    */   
/*    */   private void thatsAllFolks() {
/* 75 */     this.dialog.setVisible(false);
/* 76 */     this.dialog.dispatchEvent(new WindowEvent(
/* 77 */       this.dialog, 201));
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Databases/CountDown.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */