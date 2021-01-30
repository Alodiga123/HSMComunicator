/*     */ package Framework;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.TitledBorder;

import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.UseParameters.ParametersConfig;
import com.alodiga.hsm.operations.UseParameters.TypeHSM;
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
/*     */ public class Configuration
/*     */   extends JDialog
/*     */ {
/*     */   private JPanel contentPane;
/*     */   private JTextField iphsmtextField;
/*     */   private JTextField porthsmtextField;
/*     */   private JTextField msgHeaderthales;
/*     */   private JTextField ipservertextField;
/*     */   private JTextField portservertextField;
/*     */   private JCheckBox chckbxNewCheckBox;
/*     */   private JCheckBox checkBoxEnviroment;
/*     */   private JComboBox hsmcomboBox;
/*     */   private JComboBox sqlcomboBox;
/*     */   private JComboBox comboBoxMessageheader;
/*     */   private JPanel panelTypeHsm;
/*     */   private boolean keyblock;
/*     */   private boolean testenviroment;
/*  54 */   private String ipsrv = null;
/*  55 */   private String portsrv = null;
/*  56 */   private String iphsm = null;
/*  57 */   private String porthsm = null;
/*  58 */   private String sqltype = null;
/*  59 */   private String hsmtype = null;
/*  60 */   private String atallakb = null;
/*  61 */   private String firstuse = null;
/*  62 */   private String enviroment = null;
/*  63 */   private String headertype = null;
/*  64 */   private String msgHeaderTahles = null;
/*     */   
/*     */ 
/*     */   private JLabel msgHeaderthaleslabel;
/*     */   
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  72 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*     */         try {
/*  75 */           Configuration frame = new Configuration();
/*  76 */           frame.setVisible(true);
/*     */         }
/*     */         catch (Exception e) {
/*  79 */           System.out.println("Error Message:" + e.getMessage());
/*  80 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Configuration()
/*     */   {
/*  91 */     setResizable(false);
/*  92 */     setDefaultCloseOperation(0);
/*     */     
/*  94 */     setTitle("Configuration");
/*     */     
/*  96 */     setBounds(100, 100, 605, 390);
/*  97 */     this.contentPane = new JPanel();
/*  98 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  99 */     setContentPane(this.contentPane);
/* 100 */     this.contentPane.setLayout(null);
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 105 */       UseParameters.getParametersConfig();
/* 106 */       this.ipsrv = UseParameters.ParametersConfig.ipsrv();
/* 107 */       this.portsrv = UseParameters.ParametersConfig.portsrv();
/* 108 */       this.iphsm = UseParameters.ParametersConfig.iphsm();
/* 109 */       this.porthsm = UseParameters.ParametersConfig.porthsm();
/* 110 */       this.sqltype = UseParameters.ParametersConfig.sqltype();
/* 111 */       this.hsmtype = UseParameters.ParametersConfig.hsmtype();
/* 112 */       this.headertype = UseParameters.ParametersConfig.headermessage();
/* 113 */       this.atallakb = UseParameters.ParametersConfig.atallakb();
/* 114 */       this.enviroment = UseParameters.ParametersConfig.environment();
/* 115 */       this.msgHeaderTahles = UseParameters.ParametersConfig.headerthalesmsg();
/*     */     }
/*     */     catch (Exception e1)
/*     */     {
/* 119 */       e1.printStackTrace();
/*     */     }
/*     */     
/* 122 */     String[] hsmTypes = new String[4];
/* 123 */     hsmTypes[0] = "Thales";
/* 124 */     hsmTypes[1] = "Atalla";
/* 125 */     hsmTypes[2] = "Futurex";
/* 126 */     hsmTypes[3] = "Incognito";
/*     */     
/* 128 */     for (int x = 0; x < hsmTypes.length; x++)
/*     */     {
/* 130 */       if (hsmTypes[0].equals(this.hsmtype)) {
/* 131 */         hsmTypes[0] = this.hsmtype;
/* 132 */         hsmTypes[1] = "Atalla";
/* 133 */         hsmTypes[2] = "Futurex";
/* 134 */         hsmTypes[3] = "Incognito";
/*     */       }
/* 136 */       if (hsmTypes[1].equals(this.hsmtype)) {
/* 137 */         hsmTypes[0] = this.hsmtype;
/* 138 */         hsmTypes[1] = "Thales";
/* 139 */         hsmTypes[2] = "Futurex";
/* 140 */         hsmTypes[3] = "Incognito";
/*     */       }
/* 142 */       if (hsmTypes[2].equals(this.hsmtype)) {
/* 143 */         hsmTypes[0] = this.hsmtype;
/* 144 */         hsmTypes[1] = "Atalla";
/* 145 */         hsmTypes[2] = "Thales";
/* 146 */         hsmTypes[3] = "Incognito";
/*     */       }
/* 148 */       if (hsmTypes[3].equals(this.hsmtype)) {
/* 149 */         hsmTypes[0] = this.hsmtype;
/* 150 */         hsmTypes[1] = "Atalla";
/* 151 */         hsmTypes[2] = "Futurex";
/* 152 */         hsmTypes[3] = "Thales";
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 158 */     String[] headerType = new String[3];
/* 159 */     headerType[0] = "No Header";
/* 160 */     headerType[1] = "2 byte";
/* 161 */     headerType[2] = "4 byte char";
/*     */     
/* 163 */     for (int x = 0; x < headerType.length; x++)
/*     */     {
/* 165 */       if (headerType[0].equals(this.headertype)) {
/* 166 */         headerType[0] = this.headertype;
/* 167 */         headerType[1] = "2 byte";
/* 168 */         headerType[2] = "4 byte char";
/*     */       }
/* 170 */       if (headerType[1].equals(this.headertype)) {
/* 171 */         headerType[0] = this.headertype;
/* 172 */         headerType[1] = "No Header";
/* 173 */         headerType[2] = "4 byte char";
/*     */       }
/* 175 */       if (headerType[2].equals(this.headertype)) {
/* 176 */         headerType[0] = this.headertype;
/* 177 */         headerType[1] = "No Header";
/* 178 */         headerType[2] = "2 byte";
/*     */       }
/*     */     }
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
/* 192 */     String[] dataBases = new String[4];
/* 193 */     dataBases[0] = "SQL 2000";
/* 194 */     dataBases[1] = "SQL 2005";
/* 195 */     dataBases[2] = "SQL 2008";
/* 196 */     dataBases[3] = "SQL 2012";
/*     */     
/* 198 */     for (int y = 0; y < dataBases.length; y++)
/*     */     {
/* 200 */       if (dataBases[0].equals(this.sqltype)) {
/* 201 */         dataBases[0] = this.sqltype;
/* 202 */         dataBases[1] = "SQL 2005";
/* 203 */         dataBases[2] = "SQL 2008";
/* 204 */         dataBases[3] = "SQL 2012";
/*     */       }
/* 206 */       if (dataBases[1].equals(this.sqltype)) {
/* 207 */         dataBases[0] = this.sqltype;
/* 208 */         dataBases[1] = "SQL 2000";
/* 209 */         dataBases[2] = "SQL 2008";
/* 210 */         dataBases[3] = "SQL 2012";
/*     */       }
/* 212 */       if (dataBases[2].equals(this.sqltype)) {
/* 213 */         dataBases[0] = this.sqltype;
/* 214 */         dataBases[1] = "SQL 2000";
/* 215 */         dataBases[2] = "SQL 2005";
/* 216 */         dataBases[3] = "SQL 2012";
/*     */       }
/* 218 */       if (dataBases[3].equals(this.sqltype)) {
/* 219 */         dataBases[0] = this.sqltype;
/* 220 */         dataBases[1] = "SQL 2000";
/* 221 */         dataBases[2] = "SQL 2005";
/* 222 */         dataBases[3] = "SQL 2008";
/*     */       }
/*     */     }
/*     */     
/* 226 */     JButton btnNewButton = new JButton("Save");
/* 227 */     btnNewButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent arg0)
/*     */       {
/*     */         try
/*     */         {
/* 233 */           Configuration.this.keyblock = Configuration.this.chckbxNewCheckBox.isSelected();
/* 234 */           Configuration.this.testenviroment = Configuration.this.checkBoxEnviroment.isSelected();
/* 235 */           if (Configuration.this.keyblock)
/*     */           {
/* 237 */             if (Configuration.this.testenviroment) {
/* 238 */               UseParameters.setSaveParametersConfig(Configuration.this.ipservertextField.getText(), Configuration.this.portservertextField.getText(), Configuration.this.iphsmtextField.getText(), 
/* 239 */                 Configuration.this.porthsmtextField.getText(), (String)Configuration.this.sqlcomboBox.getSelectedItem(), (String)Configuration.this.hsmcomboBox.getSelectedItem(), "1", "NO", "YES", (String)Configuration.this.comboBoxMessageheader.getSelectedItem(), 
/* 240 */                 Configuration.this.msgHeaderthales.getText());
/*     */             }
/*     */             else {
/* 243 */               UseParameters.setSaveParametersConfig(Configuration.this.ipservertextField.getText(), Configuration.this.portservertextField.getText(), Configuration.this.iphsmtextField.getText(), 
/* 244 */                 Configuration.this.porthsmtextField.getText(), (String)Configuration.this.sqlcomboBox.getSelectedItem(), (String)Configuration.this.hsmcomboBox.getSelectedItem(), "1", "NO", "NO", (String)Configuration.this.comboBoxMessageheader.getSelectedItem(), 
/* 245 */                 Configuration.this.msgHeaderthales.getText());
/*     */             }
/*     */             
/*     */           }
/* 249 */           else if (Configuration.this.testenviroment) {
/* 250 */             UseParameters.setSaveParametersConfig(Configuration.this.ipservertextField.getText(), Configuration.this.portservertextField.getText(), Configuration.this.iphsmtextField.getText(), 
/* 251 */               Configuration.this.porthsmtextField.getText(), (String)Configuration.this.sqlcomboBox.getSelectedItem(), (String)Configuration.this.hsmcomboBox.getSelectedItem(), "0", "NO", "YES", (String)Configuration.this.comboBoxMessageheader.getSelectedItem(), 
/* 252 */               Configuration.this.msgHeaderthales.getText());
/*     */           } else {
/* 254 */             UseParameters.setSaveParametersConfig(Configuration.this.ipservertextField.getText(), Configuration.this.portservertextField.getText(), Configuration.this.iphsmtextField.getText(), 
/* 255 */               Configuration.this.porthsmtextField.getText(), (String)Configuration.this.sqlcomboBox.getSelectedItem(), (String)Configuration.this.hsmcomboBox.getSelectedItem(), "0", "NO", "NO", (String)Configuration.this.comboBoxMessageheader.getSelectedItem(), 
/* 256 */               Configuration.this.msgHeaderthales.getText());
/*     */           }
/*     */           
/* 259 */           JOptionPane.showMessageDialog(null, "Configuration Saved");
/*     */         }
/*     */         catch (IOException e) {
/* 262 */           JOptionPane.showMessageDialog(null, "Properties Error : " + e.getMessage());
/* 263 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 266 */     });
/* 267 */     btnNewButton.setBounds(360, 311, 97, 25);
/* 268 */     this.contentPane.add(btnNewButton);
/*     */     
/* 270 */     JButton btnNewButton_1 = new JButton("Exit");
/* 271 */     btnNewButton_1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 273 */         Configuration.this.CloseFrame();
/*     */       }
/* 275 */     });
/* 276 */     btnNewButton_1.setBounds(490, 311, 97, 25);
/* 277 */     this.contentPane.add(btnNewButton_1);
/*     */     
/* 279 */     JPanel panelHsm = new JPanel();
/* 280 */     panelHsm.setBounds(30, 13, 251, 136);
/* 281 */     this.contentPane.add(panelHsm);
/* 282 */     panelHsm.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " HSM Connection ...", 4, 2, null, null));
/* 283 */     panelHsm.setLayout(null);
/*     */     
/* 285 */     this.porthsmtextField = new JTextField(this.porthsm);
/* 286 */     this.porthsmtextField.setBounds(14, 59, 116, 22);
/* 287 */     panelHsm.add(this.porthsmtextField);
/* 288 */     this.porthsmtextField.setColumns(10);
/*     */     
/* 290 */     this.iphsmtextField = new JTextField(this.iphsm);
/* 291 */     this.iphsmtextField.setBounds(12, 24, 116, 22);
/* 292 */     panelHsm.add(this.iphsmtextField);
/* 293 */     this.iphsmtextField.setColumns(10);
/*     */     
/*     */ 
/* 296 */     JLabel lblIpAddressHsm = new JLabel("IP Address HSM");
/* 297 */     lblIpAddressHsm.setBounds(140, 27, 114, 16);
/* 298 */     panelHsm.add(lblIpAddressHsm);
/*     */     
/* 300 */     JLabel lblPort = new JLabel("Port");
/* 301 */     lblPort.setBounds(140, 62, 71, 16);
/* 302 */     panelHsm.add(lblPort);
/*     */     
/* 304 */     this.panelTypeHsm = new JPanel();
/* 305 */     this.panelTypeHsm.setBounds(319, 13, 268, 136);
/* 306 */     this.contentPane.add(this.panelTypeHsm);
/* 307 */     this.panelTypeHsm.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Properties HSM ...", 4, 2, null, null));
/* 308 */     this.panelTypeHsm.setLayout(null);
/*     */     
/*     */ 
/*     */ 
/* 312 */     this.msgHeaderthales = new JTextField(this.msgHeaderTahles);
/* 313 */     this.msgHeaderthales.setBounds(22, 64, 116, 22);
/* 314 */     this.panelTypeHsm.add(this.msgHeaderthales);
/* 315 */     this.msgHeaderthales.setColumns(10);
/*     */     
/* 317 */     this.msgHeaderthaleslabel = new JLabel("Header Command Msg");
/* 318 */     this.msgHeaderthaleslabel.setBounds(151, 67, 71, 16);
/* 319 */     this.panelTypeHsm.add(this.msgHeaderthaleslabel);
/*     */     
/* 321 */     this.msgHeaderthales.setVisible(false);
/* 322 */     this.msgHeaderthales.setEditable(false);
/* 323 */     this.msgHeaderthales.setEnabled(false);
/*     */     
/* 325 */     this.msgHeaderthaleslabel.setVisible(false);
/*     */     
/*     */ 
/* 328 */     this.chckbxNewCheckBox = new JCheckBox("Key Block ?");
/* 329 */     this.chckbxNewCheckBox.setBounds(23, 63, 113, 25);
/* 330 */     this.panelTypeHsm.add(this.chckbxNewCheckBox);
/* 331 */     this.chckbxNewCheckBox.setVisible(false);
/*     */     
/* 333 */     this.hsmcomboBox = new JComboBox(hsmTypes);
/* 334 */     this.hsmcomboBox.setBounds(22, 29, 114, 22);
/* 335 */     this.panelTypeHsm.add(this.hsmcomboBox);
/* 336 */     this.hsmcomboBox.setActionCommand("HsmType");
/* 337 */     this.hsmcomboBox.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 339 */         Configuration.this.callActionListhsmType(arg0);
/* 340 */         Configuration.this.callActionRepaint();
/*     */       }
/*     */     });
/*     */     try
/*     */     {
/* 345 */       UseParameters.getTypeHsm();
/*     */     }
/*     */     catch (IOException e) {
/* 348 */       e.printStackTrace();
/*     */     }
/* 350 */     if (UseParameters.TypeHSM.typeHsm().equals("Thales")) {
/* 351 */       this.chckbxNewCheckBox.setVisible(false);
/* 352 */       this.msgHeaderthales.setVisible(true);
/* 353 */       this.msgHeaderthales.setEditable(true);
/* 354 */       this.msgHeaderthales.setEnabled(true);
/* 355 */       this.msgHeaderthaleslabel.setVisible(true);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 364 */       UseParameters.getTypeHsm();
/*     */     }
/*     */     catch (IOException e) {
/* 367 */       e.printStackTrace();
/*     */     }
/* 369 */     if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/* 370 */       this.chckbxNewCheckBox.setVisible(true);
/* 371 */       this.msgHeaderthales.setVisible(false);
/* 372 */       this.msgHeaderthales.setEditable(false);
/* 373 */       this.msgHeaderthales.setEnabled(false);
/* 374 */       this.msgHeaderthaleslabel.setVisible(false);
/*     */     }
/*     */     
/* 377 */     this.comboBoxMessageheader = new JComboBox(headerType);
/* 378 */     this.comboBoxMessageheader.setBounds(22, 97, 114, 22);
/* 379 */     this.panelTypeHsm.add(this.comboBoxMessageheader);
/*     */     
/* 381 */     JLabel lblTypeOfHsm = new JLabel("Type Of HSM");
/* 382 */     lblTypeOfHsm.setBounds(152, 32, 114, 16);
/* 383 */     this.panelTypeHsm.add(lblTypeOfHsm);
/*     */     
/* 385 */     JLabel lblMessageHeader = new JLabel("TCP Message Header");
/* 386 */     lblMessageHeader.setBounds(152, 100, 114, 16);
/* 387 */     this.panelTypeHsm.add(lblMessageHeader);
/*     */     
/*     */ 
/* 390 */     if (this.atallakb.equals("1"))
/*     */     {
/* 392 */       System.out.println("Atalla AKB from File: " + this.atallakb);
/* 393 */       this.chckbxNewCheckBox.setSelected(true);
/*     */     }
/*     */     
/* 396 */     this.checkBoxEnviroment = new JCheckBox("Test/Certificate");
/* 397 */     this.checkBoxEnviroment.setBounds(14, 92, 116, 25);
/* 398 */     panelHsm.add(this.checkBoxEnviroment);
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
/* 419 */     if (this.enviroment.equals("YES")) {
/* 420 */       System.out.println("Environment used: " + this.enviroment);
/* 421 */       this.checkBoxEnviroment.setSelected(true);
/*     */     }
/*     */     
/*     */ 
/* 425 */     JPanel panelSQL = new JPanel();
/* 426 */     panelSQL.setBounds(319, 174, 171, 124);
/* 427 */     this.contentPane.add(panelSQL);
/* 428 */     panelSQL.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " SQL Version ...", 4, 2, null, null));
/* 429 */     panelSQL.setLayout(null);
/* 430 */     panelSQL.setVisible(false);
/*     */     
/* 432 */     this.sqlcomboBox = new JComboBox(dataBases);
/* 433 */     this.sqlcomboBox.setBounds(12, 41, 114, 22);
/* 434 */     panelSQL.add(this.sqlcomboBox);
/*     */     
/* 436 */     JPanel panelDataBase = new JPanel();
/* 437 */     panelDataBase.setBounds(30, 174, 251, 126);
/* 438 */     this.contentPane.add(panelDataBase);
/* 439 */     panelDataBase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Data Base Connection ...", 4, 2, null, null));
/* 440 */     panelDataBase.setLayout(null);
/*     */     
/* 442 */     this.portservertextField = new JTextField(this.portsrv);
/* 443 */     this.portservertextField.setBounds(14, 75, 116, 22);
/* 444 */     panelDataBase.add(this.portservertextField);
/* 445 */     this.portservertextField.setColumns(10);
/*     */     
/* 447 */     JLabel label = new JLabel("Port");
/* 448 */     label.setBounds(140, 78, 114, 16);
/* 449 */     panelDataBase.add(label);
/*     */     
/* 451 */     JLabel lblIpServerAddress = new JLabel("IP Server Address ");
/* 452 */     lblIpServerAddress.setBounds(140, 43, 114, 16);
/* 453 */     panelDataBase.add(lblIpServerAddress);
/*     */     
/* 455 */     this.ipservertextField = new JTextField(this.ipsrv);
/* 456 */     this.ipservertextField.setBounds(12, 40, 116, 22);
/* 457 */     panelDataBase.add(this.ipservertextField);
/* 458 */     this.ipservertextField.setColumns(10);
/*     */   }
/*     */   
/*     */   private void callActionListhsmType(ActionEvent arg0)
/*     */   {
/* 463 */     String command = arg0.getActionCommand();
/* 464 */     if ("HsmType".equalsIgnoreCase(command)) {
/* 465 */       System.out.println("Entre al Metodo HSM Type");
/* 466 */       if (this.hsmcomboBox.getSelectedItem().equals("Atalla")) {
/* 467 */         System.out.println("Entre Atalla");
/* 468 */         this.msgHeaderthales.setVisible(false);
/* 469 */         this.msgHeaderthales.setEditable(false);
/* 470 */         this.msgHeaderthales.setEnabled(false);
/* 471 */         this.msgHeaderthaleslabel.setVisible(false);
/* 472 */         this.chckbxNewCheckBox.setVisible(true);
/* 473 */         this.chckbxNewCheckBox.setEnabled(true);
/*     */       }
/* 475 */       if (this.hsmcomboBox.getSelectedItem().equals("Thales")) {
/* 476 */         System.out.println("Entre Thales");
/* 477 */         this.chckbxNewCheckBox.setVisible(false);
/* 478 */         this.msgHeaderthales.setVisible(true);
/* 479 */         this.msgHeaderthales.setEditable(true);
/* 480 */         this.msgHeaderthales.setEnabled(true);
/* 481 */         this.msgHeaderthaleslabel.setVisible(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void callActionRepaint()
/*     */   {
/* 494 */     Configuration frame = null;
/*     */     
/* 496 */     repaint();
/*     */   }
/*     */   
/* 499 */   protected void CloseFrame() { super.dispose(); }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/Configuration.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */