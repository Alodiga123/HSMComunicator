/*     */ package Framework;
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
/*     */ import javax.swing.border.TitledBorder;

import com.alodiga.hsm.data.ConnectDB;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CredentialsInstallDB
/*     */   extends JDialog
/*     */ {
/*     */   private JPanel contentPane;
/*     */   private JPasswordField passwordField;
/*     */   private JTextField textField;
/*     */   String strConsultaSQL;
/*     */   Statement estSQL1;
/*     */   ResultSet rs;
/*     */   ResultSet rs2;
/*  54 */   public Connection conn = null;
/*     */   
/*     */   public CredentialsInstallDB()
/*     */   {
/*  58 */     setResizable(false);
/*  59 */     setTitle("Data Base Installation");
/*  60 */     setDefaultCloseOperation(0);
/*  61 */     setBounds(100, 100, 360, 262);
/*  62 */     this.contentPane = new JPanel();
/*  63 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  64 */     setContentPane(this.contentPane);
/*  65 */     this.contentPane.setLayout(null);
/*     */     
/*  67 */     JPanel panellogin = new JPanel();
/*  68 */     panellogin.setBounds(12, 23, 335, 198);
/*  69 */     panellogin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Credentials ...", 4, 2, null, null));
/*  70 */     this.contentPane.add(panellogin);
/*  71 */     panellogin.setLayout(null);
/*     */     
/*  73 */     JLabel lblPassword = new JLabel("Password");
/*  74 */     lblPassword.setBounds(245, 86, 108, 16);
/*  75 */     panellogin.add(lblPassword);
/*     */     
/*  77 */     JLabel lblUsuario = new JLabel("User");
/*  78 */     lblUsuario.setBounds(245, 43, 108, 16);
/*  79 */     panellogin.add(lblUsuario);
/*     */     
/*  81 */     this.textField = new JTextField();
/*  82 */     this.textField.setBounds(31, 36, 202, 30);
/*  83 */     panellogin.add(this.textField);
/*  84 */     this.textField.setColumns(10);
/*     */     
/*  86 */     this.passwordField = new JPasswordField();
/*  87 */     this.passwordField.setBounds(31, 79, 202, 30);
/*  88 */     panellogin.add(this.passwordField);
/*     */     
/*  90 */     JButton btnNewButton_2 = new JButton("Continue");
/*  91 */     btnNewButton_2.setBounds(31, 139, 97, 25);
/*  92 */     panellogin.add(btnNewButton_2);
/*     */     
/*  94 */     JButton btnNewButton_3 = new JButton("Exit");
/*  95 */     btnNewButton_3.setBounds(140, 139, 97, 25);
/*  96 */     panellogin.add(btnNewButton_3);
/*     */     
/*     */ 
/*  99 */     btnNewButton_3.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 101 */         CredentialsInstallDB.this.CloseFrame();
/*     */       }
/* 103 */     });
/* 104 */     btnNewButton_2.addActionListener(new ActionListener()
/*     */     {
/*     */ 
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 109 */         String user = CredentialsInstallDB.this.textField.getText().trim();
/* 110 */         String password = new String(CredentialsInstallDB.this.passwordField.getPassword()).trim();
/*     */         
/* 112 */         if ((user.isEmpty()) || (password.isEmpty()))
/*     */         {
/* 114 */           JOptionPane.showMessageDialog(null, "Please Insert a User with Data Base Privileges");
/*     */         }
/*     */         else
/*     */         {
/*     */           try
/*     */           {
/* 120 */             if (ConnectServer())
/*     */             {
/* 122 */               Statement estSQL2 = CredentialsInstallDB.this.conn.createStatement();
/*     */               
/* 124 */               String sqlCount2 = "SELECT name FROM master.sys.databases WHERE name = N'OmniKey'";
/* 125 */               ResultSet rs2 = estSQL2.executeQuery(sqlCount2);
/* 126 */               rs2.next();
/*     */               
/* 128 */               if (rs2.equals("OmniKey")) {
/* 129 */                 JOptionPane.showMessageDialog(null, "Message : Data Base Already Exists", "Warning Message", 2);
/*     */               }
/*     */               else {
/* 132 */                 ProgressDBInstallation.instaldb = CredentialsInstallDB.this.conn;
/* 133 */                 ProgressDBInstallation installation = new ProgressDBInstallation();
/* 134 */                 installation.setVisible(true);
/* 135 */                 installation.setLocationRelativeTo(null);
/* 136 */                 CredentialsInstallDB.this.CloseFrame();
/*     */               }
/*     */             }
/*     */           }
/*     */           catch (Exception e1)
/*     */           {
/* 142 */             JOptionPane.showMessageDialog(null, "Error al ejecutar CONSULTA!");
/* 143 */             CredentialsInstallDB.this.CloseFrame();
/*     */             try {
/* 145 */               CredentialsInstallDB.this.conn.close();
/*     */             }
/*     */             catch (SQLException e2) {
/* 148 */               e2.printStackTrace();
/*     */             }
/*     */             
/* 151 */             e1.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */       public boolean ConnectServer()
/*     */         throws Exception
/*     */       {
/* 159 */         UseParameters.getParametersConfig();
/* 160 */         String ipsrv = UseParameters.ParametersConfig.ipsrv();
/* 161 */         String portsrv = UseParameters.ParametersConfig.portsrv();
/*     */         
/* 163 */         String user = CredentialsInstallDB.this.textField.getText().trim();
/* 164 */         String password = new String(CredentialsInstallDB.this.passwordField.getPassword()).trim();
/*     */         
/*     */ 
/* 167 */         ProgressDBInstallation.user = user;
/* 168 */         ProgressDBInstallation.pass = password;
/* 169 */         ProgressDBInstallation.ip = ipsrv;
/* 170 */         ProgressDBInstallation.port = portsrv;
/* 171 */         ConnectDB con = new ConnectDB(user, password, "master", ipsrv, portsrv);
/*     */         
/*     */ 
/*     */         try
/*     */         {
/* 176 */           ConnectDB.setLoginTimeout(15);
/* 177 */           CredentialsInstallDB.this.conn = ConnectDB.GetConnection();
/*     */           
/* 179 */           if (CredentialsInstallDB.this.conn != null)
/*     */           {
/*     */ 
/* 182 */             JOptionPane.showMessageDialog(null, "Conexion establecida");
/* 183 */             return true;
/*     */           }
/*     */         }
/*     */         catch (SQLException e1) {
/* 187 */           JOptionPane.showMessageDialog(null, "Message : " + e1.getMessage());
/* 188 */           e1.printStackTrace();
/*     */           
/*     */ 
/* 191 */           System.out.println(user + password + ipsrv + portsrv);
/*     */         }
/* 193 */         return false;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void CloseFrame()
/*     */   {
/* 203 */     super.dispose();
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/CredentialsInstallDB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */