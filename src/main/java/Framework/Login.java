/*     */ package Framework;
/*     */ import java.awt.Color;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.LineBorder;
/*     */ import javax.swing.border.TitledBorder;

import com.alodiga.hsm.data.ConnectDB;
import com.alodiga.hsm.data.ExecuteSP;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.UseParameters.ParametersConfig;
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
/*     */ public class Login
/*     */   extends JDialog
/*     */ {
/*     */   private JPanel contentPane;
/*     */   private JPasswordField passwordField;
/*     */   private JTextField textField;
/*     */   String strConsultaSQL;
/*  49 */   public static boolean ImportConsole = false;
/*     */   
/*     */   Statement estSQL1;
/*     */   ResultSet rs;
/*     */   ResultSet rs2;
/*  54 */   public Connection conn = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  61 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*     */         try {
/*  64 */           Login frame = new Login();
/*  65 */           frame.setVisible(true);
/*     */         } catch (Exception e) {
/*  67 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Login()
/*     */   {
/*  77 */     setResizable(false);
/*  78 */     setTitle("Login");
/*  79 */     setDefaultCloseOperation(0);
/*  80 */     setBounds(100, 100, 639, 302);
/*  81 */     this.contentPane = new JPanel();
/*  82 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  83 */     setContentPane(this.contentPane);
/*  84 */     this.contentPane.setLayout(null);
/*     */     
/*  86 */     JPanel panellogin = new JPanel();
/*  87 */     panellogin.setBounds(272, 23, 335, 198);
/*  88 */     panellogin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Credentials ...", 4, 2, null, null));
/*  89 */     this.contentPane.add(panellogin);
/*  90 */     panellogin.setLayout(null);
/*     */     
/*  92 */     JLabel lblPassword = new JLabel("Password");
/*  93 */     lblPassword.setBounds(245, 86, 108, 16);
/*  94 */     panellogin.add(lblPassword);
/*     */     
/*  96 */     JLabel lblUsuario = new JLabel("User");
/*  97 */     lblUsuario.setBounds(245, 43, 108, 16);
/*  98 */     panellogin.add(lblUsuario);
/*     */     
/* 100 */     this.textField = new JTextField();
/* 101 */     this.textField.setBounds(31, 36, 202, 30);
/* 102 */     panellogin.add(this.textField);
/* 103 */     this.textField.setColumns(10);
/*     */     
/* 105 */     this.passwordField = new JPasswordField();
/* 106 */     this.passwordField.setBounds(31, 79, 202, 30);
/* 107 */     panellogin.add(this.passwordField);
/*     */     
/* 109 */     JButton btnNewButton_2 = new JButton("Login");
/* 110 */     btnNewButton_2.setBounds(31, 139, 97, 25);
/* 111 */     panellogin.add(btnNewButton_2);
/*     */     
/* 113 */     JButton btnNewButton_3 = new JButton("Exit");
/* 114 */     btnNewButton_3.setBounds(140, 139, 97, 25);
/* 115 */     panellogin.add(btnNewButton_3);
/*     */     
/*     */ 
/* 118 */     LoginPanelImage panelLoginimage = new LoginPanelImage();
/* 119 */     panelLoginimage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
/* 120 */     panelLoginimage.setBounds(12, 23, 248, 198);
/* 121 */     this.contentPane.add(panelLoginimage);
/*     */     
/*     */ 
/* 124 */     btnNewButton_3.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 126 */         Login.this.CloseFrame();
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 133 */     });
/* 134 */     btnNewButton_2.addActionListener(new ActionListener()
/*     */     {
/*     */ 
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 139 */         String user = Login.this.textField.getText().trim();
/* 140 */         String password = new String(Login.this.passwordField.getPassword()).trim();
/*     */         
/* 142 */         if ((user.isEmpty()) || (password.isEmpty()))
/*     */         {
/* 144 */           JOptionPane.showMessageDialog(null, "Please Insert a UserName and Password");
/*     */         }
/*     */         else
/*     */         {
/*     */           try
/*     */           {
/* 150 */             if (ConnectServer())
/*     */             {
/*     */ 
/* 153 */               if (!Login.ImportConsole) {
/* 154 */                 CryptoAssociated.cryptoconn = Login.this.conn;
/*     */               }
/*     */               
/*     */ 
/* 158 */               if (Login.ImportConsole) {
/* 159 */                 Login.this.CloseFrame();
/* 160 */                 ImportCryptogram.transportKEKConn = Login.this.conn;
/* 161 */                 ImportCryptogram.loadKEKfromBD();
/* 162 */                 Login.ImportConsole = false;
/*     */               }
/*     */               
/* 165 */               Login.this.CloseFrame();
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
/* 177 */               ConnectDB.cerrar(Login.this.rs);
/* 178 */               ConnectDB.cerrar(Login.this.estSQL1);
/*     */             }
/*     */             
/*     */           }
/*     */           catch (Exception e1)
/*     */           {
/* 184 */             JOptionPane.showMessageDialog(null, "Error al ejecutar CONSULTA!");
/*     */             
/* 186 */             e1.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */       public boolean ConnectServer()
/*     */         throws Exception
/*     */       {
/*     */         try
/*     */         {
/* 196 */           UseParameters.getParametersConfig();
/* 197 */           String ipsrv = UseParameters.ParametersConfig.ipsrv();
/* 198 */           String portsrv = UseParameters.ParametersConfig.portsrv();
/*     */           
/* 200 */           String user = Login.this.textField.getText().trim();
/* 201 */           String password = new String(Login.this.passwordField.getPassword()).trim();
/*     */           
/*     */ 
/* 204 */           ConnectDB con = new ConnectDB(user, password, "OmniKey", ipsrv, portsrv);
/*     */           
/*     */ 
/*     */           try
/*     */           {
/* 209 */             ConnectDB.setLoginTimeout(15);
/* 210 */             Login.this.conn = ConnectDB.GetConnection();
/*     */           }
/*     */           catch (SQLException e1)
/*     */           {
/* 214 */             JOptionPane.showMessageDialog(null, "Message : " + e1.getMessage());
/* 215 */             e1.printStackTrace();
/*     */           }
/*     */           
/* 218 */           System.out.println(user + password + ipsrv + portsrv);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 223 */           if (Login.this.conn != null)
/*     */           {
/*     */ 
/* 226 */             JOptionPane.showMessageDialog(null, "Conexion establecida");
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
/* 239 */             String role = ExecuteSP.execIsmember(user, Login.this.conn);
/* 240 */             System.out.println(role);
/*     */             
/*     */ 
/* 243 */             if (role.equals("0"))
/*     */             {
/* 245 */               System.out.println("Entre");
/* 246 */               JOptionPane.showMessageDialog(null, "User don't have Permission");
/*     */               
/* 248 */               ConnectDB.cerrar(Login.this.rs2);
/* 249 */               ConnectDB.cerrar(Login.this.conn);
/* 250 */               return false;
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 255 */             Login.this.estSQL1 = Login.this.conn.createStatement();
/* 256 */             return true;
/*     */           }
/*     */           
/* 259 */           return false;
/*     */ 
/*     */ 
/*     */         }
/*     */         catch (SQLException e)
/*     */         {
/*     */ 
/*     */ 
/* 267 */           JOptionPane.showMessageDialog(null, "Conection Error : " + e.getMessage());
/* 268 */           e.printStackTrace(); }
/* 269 */         return false;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void CloseFrame()
/*     */   {
/* 283 */     super.dispose();
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/Login.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */