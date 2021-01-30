/*    */ package com.alodiga.hsm.operations;
/*    */ 
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepaintParentComponent
/*    */ {
/*    */   public static void repaintParent(JComponent component)
/*    */   {
/* 32 */     JComponent parentComponent = (JComponent)SwingUtilities.getAncestorOfClass(JComponent.class, component);
/*    */     
/*    */ 
/* 35 */     if (parentComponent != null)
/*    */     {
/*    */ 
/* 38 */       parentComponent.revalidate();
/* 39 */       parentComponent.repaint();
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 44 */       component.revalidate();
/* 45 */       component.repaint();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Operations/RepaintParentComponent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */