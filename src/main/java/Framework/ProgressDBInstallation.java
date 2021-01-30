/*     */ package Framework;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.border.EmptyBorder;

import com.alodiga.hsm.data.ConnectDB;
import com.alodiga.hsm.data.CreateDB;
import com.alodiga.hsm.operations.UseParameters;
/*     */ 
/*     */ public class ProgressDBInstallation
/*     */   extends JDialog
/*     */ {
/*  24 */   public static boolean closeProgress = false;
/*  25 */   private final JPanel contentPanel = new JPanel();
/*  26 */   public static String progressBar = null;
/*  27 */   public static JProgressBar progressBarInstallDB = null;
/*  28 */   int num = 0;
/*  29 */   public static Connection instaldb = null;
/*  30 */   public static String user = null; public static String pass = null; public static String ip = null; public static String port = null;
/*  31 */   private Connection installObjects = null;
/*  32 */   public static boolean failInstall = false;
/*  33 */   public static JButton btnStop = null;
/*     */   
/*     */   public ProgressDBInstallation() {
/*  36 */     setResizable(false);
/*  37 */     setBounds(100, 100, 448, 233);
/*  38 */     getContentPane().setLayout(new BorderLayout());
/*  39 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  40 */     getContentPane().add(this.contentPanel, "Center");
/*  41 */     this.contentPanel.setLayout(null);
/*     */     
/*  43 */     progressBarInstallDB = new JProgressBar();
/*  44 */     progressBarInstallDB.setBounds(24, 82, 389, 31);
/*  45 */     progressBarInstallDB.setValue(0);
/*  46 */     progressBarInstallDB.setStringPainted(true);
/*  47 */     progressBarInstallDB.setVisible(true);
/*  48 */     this.contentPanel.add(progressBarInstallDB);
/*     */     
/*     */ 
/*     */ 
/*  52 */     JButton btnStart = new JButton("Start");
/*  53 */     btnStart.setActionCommand("Start");
/*  54 */     btnStart.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/*  56 */         ProgressDBInstallation.this.callAcionPerformedStart(arg0);
/*     */       }
/*  58 */     });
/*  59 */     btnStart.setBounds(108, 138, 97, 25);
/*  60 */     this.contentPanel.add(btnStart);
/*     */     
/*     */ 
/*  63 */     btnStop = new JButton("Stop");
/*  64 */     btnStop.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  66 */         ProgressDBInstallation.this.repaint();
/*  67 */         ProgressDBInstallation.this.num = 100;
/*  68 */         ProgressDBInstallation.failInstall = true;
/*     */       }
/*  70 */     });
/*  71 */     btnStop.setVisible(false);
/*  72 */     btnStop.setBounds(239, 138, 97, 25);
/*  73 */     this.contentPanel.add(btnStop);
/*     */     
/*  75 */     JLabel LabelTitle = new JLabel("Installation of Data Bases/Tables");
/*  76 */     LabelTitle.setBounds(122, 37, 223, 16);
/*  77 */     this.contentPanel.add(LabelTitle);
/*  78 */     setLocationRelativeTo(null);
/*     */     
/*     */ 
/*  81 */     if (closeProgress) {
/*  82 */       CloseFrame();
/*     */     }
/*     */   }
/*     */   
/*     */   private void callAcionPerformedStart(ActionEvent arg0)
/*     */   {
/*  88 */     String command = arg0.getActionCommand();
/*  89 */     if ("Start".equalsIgnoreCase(command)) {
/*     */       try {
/*  91 */         int i = 0;
/*  92 */         int dialogButton = 0;
/*  93 */         int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirmation", dialogButton);
/*  94 */         if (dialogResult == 0) {
/*  95 */           System.out.println("Yes option");
/*  96 */           System.out.println("Primer Mensaje");
/*  97 */           progressBarInstallDB.setVisible(true);
/*     */           
/*     */ 
/* 100 */           while (this.num <= 100)
/*     */           {
/* 102 */             progressBarInstallDB.setValue(this.num);
/* 103 */             i++;
/*     */             try {
/* 105 */               Thread.sleep(500L);
/*     */             }
/*     */             catch (InterruptedException localInterruptedException) {}
/*     */             
/* 109 */             System.out.println("Operation 0 : " + i);
/*     */             
/* 111 */             if (i == 1) {
/* 112 */               System.out.println("Operation 1 : " + i);
/* 113 */               if (CreateDB.InstallDB(instaldb)) {
/* 114 */                 JOptionPane.showMessageDialog(null, "Data Base Created");
/* 115 */                 this.num += 10;
/*     */               } else {
/* 117 */                 CreateDB.RollBackInstallation(instaldb);
/* 118 */                 closeConnection(instaldb);
/* 119 */                 this.num = 100;
/* 120 */                 break;
/*     */               }
/*     */             }
/* 123 */             if (i == 2) {
/* 124 */               System.out.println("Operation 2 : " + i);
/* 125 */               if (CreateDB.CreateTables(instaldb)) {
/* 126 */                 JOptionPane.showMessageDialog(null, "Data Base Tables Created");
/* 127 */                 this.num += 10;
/*     */                 try {
/* 129 */                   instaldb.close();
/* 130 */                   ConnectDB con = new ConnectDB(user, pass, "OmniKey", ip, port);
/* 131 */                   this.installObjects = ConnectDB.GetConnection();
/*     */                 }
/*     */                 catch (SQLException e) {
/* 134 */                   e.printStackTrace();
/*     */                 }
/*     */               } else {
/* 137 */                 CreateDB.RollBackInstallation(instaldb);
/* 138 */                 closeConnection(instaldb);
/* 139 */                 this.num = 100;
/* 140 */                 break;
/*     */               }
/*     */             }
/* 143 */             if (i == 3) {
/* 144 */               System.out.println("Operation 3 : " + i);
/* 145 */               if (CreateDB.InstallSP(this.installObjects)) {
/* 146 */                 JOptionPane.showMessageDialog(null, "Store Procedures Created");
/* 147 */                 this.num += 10;
/*     */               } else {
/* 149 */                 CreateDB.RollBackInstallation(this.installObjects);
/* 150 */                 closeConnection(this.installObjects);
/* 151 */                 this.num = 100;
/* 152 */                 break;
/*     */               }
/*     */             }
/* 155 */             if (i == 4) {
/* 156 */               System.out.println("Operation 4 : " + i);
/* 157 */               if (CreateDB.InstallDBRoles(this.installObjects)) {
/* 158 */                 JOptionPane.showMessageDialog(null, "Data Base Roles Created");
/* 159 */                 this.num += 10;
/*     */               } else {
/* 161 */                 CreateDB.RollBackInstallation(this.installObjects);
/* 162 */                 closeConnection(this.installObjects);
/* 163 */                 this.num = 100;
/* 164 */                 break;
/*     */               }
/*     */             }
/* 167 */             if (i == 5) {
/* 168 */               System.out.println("Operation 5 : " + i);
/* 169 */               if (CreateDB.GrantPermissions(this.installObjects)) {
/* 170 */                 JOptionPane.showMessageDialog(null, "Permissions Granted");
/* 171 */                 this.num += 10;
/*     */               }
/*     */               else {
/* 174 */                 CreateDB.RollBackInstallation(this.installObjects);
/* 175 */                 closeConnection(this.installObjects);
/* 176 */                 this.num = 100;
/* 177 */                 break;
/*     */               }
/*     */             }
/* 180 */             this.num += 10;
/* 181 */             progressBarInstallDB.setValue(this.num);
/*     */             
/* 183 */             if ((this.num > 100) && (!failInstall))
/*     */             {
/* 185 */               JOptionPane.showMessageDialog(null, "Installation Complete");
/* 186 */               UseParameters.setSaveDBStatus("ON");
/* 187 */               CloseFrame();
/*     */               
/* 189 */               break;
/*     */             }
/*     */             
/* 192 */             if ((this.num > 100) && (failInstall)) {
/* 193 */               CreateDB.RollBackInstallation(this.installObjects);
/* 194 */               closeConnection(this.installObjects);
/* 195 */               JOptionPane.showMessageDialog(null, "Installation Abroted");
/* 196 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 202 */           System.out.println("No Option");
/* 203 */           JOptionPane.showMessageDialog(null, "Operation Cancelled");
/*     */         }
/*     */       }
/*     */       catch (Exception e1) {
/* 207 */         JOptionPane.showMessageDialog(this, "Installation Failed", 
/* 208 */           "", 0);
/* 209 */         e1.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void CloseFrame()
/*     */   {
/* 216 */     super.dispose();
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
/*     */   private void closeConnection(Connection closeconn)
/*     */   {
/*     */     try
/*     */     {
/* 316 */       closeconn.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 319 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/ProgressDBInstallation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */