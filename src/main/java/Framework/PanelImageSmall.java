/*    */ package Framework;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ 
/*    */ public class PanelImageSmall extends javax.swing.JPanel
/*    */ {
/*    */   public PanelImageSmall() {
/*  8 */     setSize(200, 140);
/*    */   }
/*    */   
/*    */   public void paintComponent(java.awt.Graphics g) {
/* 12 */     Dimension tamanio = getSize();
/* 13 */     javax.swing.ImageIcon imagenFondo = new javax.swing.ImageIcon(getClass().getResource("/images/Certificate-digital.jpg"));
/* 14 */     g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
/* 15 */     setOpaque(false);
/* 16 */     super.paintComponent(g);
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/PanelImageSmall.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */