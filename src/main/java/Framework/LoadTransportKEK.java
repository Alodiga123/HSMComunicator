/*     */ package Framework;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.TitledBorder;

import com.alodiga.hsm.AtallaCryptoCommand;
import com.alodiga.hsm.CryptoConnection;
import com.alodiga.hsm.UnpackAtallaCryptoCommand;
import com.alodiga.hsm.data.ExecuteSP;
import com.alodiga.hsm.operations.HexFormat;
import com.alodiga.hsm.operations.SystemDate;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.HexFormat.TextCheckDigit;
import com.alodiga.hsm.operations.HexFormat.TextKeyBlockFormat;
import com.alodiga.hsm.operations.HexFormat.TextKeyFormat;
import com.alodiga.hsm.operations.UseParameters.EnvironmentUsed;
import com.alodiga.hsm.operations.UseParameters.TypeHSM;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoadTransportKEK
/*     */   extends JDialog
/*     */ {
/*  43 */   private final JPanel contentPanel = new JPanel();
/*  44 */   private JTextField CheckDigit_textField; private JTextField textFieldHeader = null;
/*  45 */   private static JComboBox comboBoxlengthkey = null;
/*  46 */   private static JComboBox comboBoxlengthkey_kb = null;
/*  47 */   private static JFormattedTextField formattedTextFieldKey1 = null;
/*  48 */   private static JFormattedTextField formattedTextFieldKey2 = null; private static JFormattedTextField formattedTextFieldKeyBlock = null;
/*  49 */   private static JFormattedTextField formattedTextFieldKey3 = null;
/*  50 */   private static JFormattedTextField formattedTextFieldCheckDigit = null;
/*  51 */   private static String[] keyLenght = new String[3];
/*  52 */   private static JLabel labellenght = null;
/*  53 */   private static JLabel labelCheckDigit = null;
/*  54 */   private static JLabel labelPart1 = null;
/*  55 */   private static JLabel labelPart2 = null;
/*  56 */   private static JLabel labelPart3 = null;
/*  57 */   private static JLabel lblPart_keybloc = null;
/*  58 */   private static JLabel lblPart_MAC = null;
/*  59 */   private static JLabel lblHeader = null;
/*  60 */   private static JButton button = null;
/*     */   private static JButton btnVerify;
/*     */   private boolean keyblock;
/*  63 */   private static JCheckBox checkBox = null; private static JCheckBox checkBox_Environment = null;
/*  64 */   private static JPanel cryptoPanel = null;
/*  65 */   private static Connection kwp_Conn = null;
/*  66 */   public static String actionCommandCryptoAssociated = null;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/*  72 */       LoadTransportKEK dialog = new LoadTransportKEK(null);
/*  73 */       dialog.setDefaultCloseOperation(2);
/*  74 */       dialog.setVisible(true);
/*     */     } catch (Exception e) {
/*  76 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LoadTransportKEK(Connection sql)
/*     */     throws IOException
/*     */   {
/*  85 */     setTitle("KEK for Export/Import Operations");
/*  86 */     setDefaultCloseOperation(0);
/*  87 */     kwp_Conn = sql;
/*  88 */     setResizable(false);
/*  89 */     setBounds(100, 100, 591, 367);
/*  90 */     getContentPane().setLayout(new BorderLayout());
/*  91 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  92 */     getContentPane().add(this.contentPanel, "Center");
/*  93 */     this.contentPanel.setLayout(null);
/*     */     
/*  95 */     HexFormat.getHexSingleFormat();
/*  96 */     HexFormat.getHexSingleFormatCheckDigit();
/*  97 */     HexFormat.getHexKeyBlockFormat();
/*     */     
/*  99 */     JPanel propertiesPanel = new JPanel();
/* 100 */     propertiesPanel.setLayout(null);
/* 101 */     propertiesPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Crypto Properties ...", 4, 2, null, null));
/* 102 */     propertiesPanel.setBounds(335, 13, 238, 250);
/* 103 */     this.contentPanel.add(propertiesPanel);
/*     */     
/* 105 */     keyLenght[0] = "Single";
/* 106 */     keyLenght[1] = "Double";
/* 107 */     keyLenght[2] = "Triple";
/* 108 */     comboBoxlengthkey = new JComboBox(keyLenght);
/* 109 */     comboBoxlengthkey.setActionCommand("LenghtKey");
/* 110 */     comboBoxlengthkey.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 113 */         LoadTransportKEK.this.callActionPerformedCheck(e);
/*     */       }
/* 115 */     });
/* 116 */     comboBoxlengthkey.setBounds(12, 36, 119, 22);
/* 117 */     propertiesPanel.add(comboBoxlengthkey);
/*     */     
/* 119 */     comboBoxlengthkey_kb = new JComboBox(keyLenght);
/* 120 */     comboBoxlengthkey_kb.setActionCommand("LenghtKey");
/* 121 */     comboBoxlengthkey_kb.setBounds(12, 36, 119, 22);
/* 122 */     propertiesPanel.add(comboBoxlengthkey_kb);
/* 123 */     comboBoxlengthkey_kb.setVisible(false);
/* 124 */     comboBoxlengthkey_kb.setEditable(false);
/*     */     
/* 126 */     labellenght = new JLabel("Length");
/* 127 */     labellenght.setBounds(155, 39, 97, 16);
/* 128 */     propertiesPanel.add(labellenght);
/*     */     
/* 130 */     formattedTextFieldCheckDigit = new JFormattedTextField(HexFormat.TextCheckDigit.formattrypeCd());
/* 131 */     formattedTextFieldCheckDigit.setColumns(10);
/* 132 */     formattedTextFieldCheckDigit.setBounds(12, 146, 119, 29);
/* 133 */     propertiesPanel.add(formattedTextFieldCheckDigit);
/*     */     
/* 135 */     labelCheckDigit = new JLabel("Check Digit");
/* 136 */     labelCheckDigit.setBounds(143, 140, 97, 16);
/* 137 */     propertiesPanel.add(labelCheckDigit);
/*     */     
/* 139 */     this.keyblock = false;
/* 140 */     checkBox = new JCheckBox("Key Block");
/* 141 */     checkBox.setActionCommand("KeyBlockFlag");
/* 142 */     checkBox.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 144 */         LoadTransportKEK.this.keyblock = LoadTransportKEK.checkBox.isSelected();
/* 145 */         LoadTransportKEK.this.callActionPerformedCheck(e);
/*     */       }
/* 147 */     });
/* 148 */     checkBox.setBounds(12, 91, 113, 25);
/* 149 */     propertiesPanel.add(checkBox);
/*     */     
/* 151 */     checkBox_Environment = new JCheckBox("Test/Certificate");
/* 152 */     checkBox_Environment.setBounds(12, 204, 129, 25);
/* 153 */     propertiesPanel.add(checkBox_Environment);
/*     */     
/* 155 */     btnVerify = new JButton("Verify");
/* 156 */     btnVerify.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 159 */         String insert_Lenght = null;String insert_keypart1 = null;String insert_keypart2 = null;
/* 160 */         String insert_keypart3 = null;String insert_finalkey = null;String insert_checkdigit = null;String insert_typehsm = null;
/* 161 */         String date = null;String insert_keyblock = null;String insert_keytype = null;String insert_environment = null;String insert_keyname = null;
/*     */         
/*     */ 
/*     */         try
/*     */         {
/* 166 */           if (LoadTransportKEK.this.keyblock) {
/* 167 */             insert_Lenght = (String)LoadTransportKEK.comboBoxlengthkey_kb.getSelectedItem();
/* 168 */             insert_keypart1 = LoadTransportKEK.this.textFieldHeader.getText();
/* 169 */             insert_keypart2 = LoadTransportKEK.formattedTextFieldKeyBlock.getText();
/* 170 */             insert_keypart3 = LoadTransportKEK.formattedTextFieldKey3.getText();
/* 171 */             insert_finalkey = insert_keypart1 + "," + insert_keypart2 + "," + insert_keypart3;
/*     */             
/* 173 */             if (insert_keypart2.isEmpty()) {
/* 174 */               JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*     */             }
/* 176 */             else if (insert_keypart3.isEmpty()) {
/* 177 */               JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for MAC");
/*     */             } else {
/* 179 */               System.out.println("Header LOAD:" + insert_keypart1);
/* 180 */               System.out.println("LENGHT LOAD:" + insert_Lenght);
/*     */               
/* 182 */               String CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 183 */               String CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/* 184 */               System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedResponseHsm);
/*     */               
/* 186 */               if (CryptoAssociated.failedResponseHsm) {
/* 187 */                 System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedResponseHsm);
/* 188 */                 JOptionPane.showMessageDialog(null, "Message Error : " + CheckDigitGenerated, "Error Message", 0);
/* 189 */                 CryptoAssociated.failedResponseHsm = false;
/*     */               } else {
/* 191 */                 LoadTransportKEK.formattedTextFieldCheckDigit.setText(CheckDigitGenerated);
/* 192 */                 CryptoAssociated.failedResponseHsm = false;
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */           }
/*     */           else
/*     */           {
/* 200 */             insert_Lenght = (String)LoadTransportKEK.comboBoxlengthkey.getSelectedItem();
/* 201 */             if (insert_Lenght.equals("Single")) {
/* 202 */               insert_keypart1 = LoadTransportKEK.formattedTextFieldKey1.getText();
/* 203 */               insert_finalkey = insert_keypart1;
/*     */             }
/* 205 */             if (insert_Lenght.equals("Double")) {
/* 206 */               insert_keypart1 = LoadTransportKEK.formattedTextFieldKey1.getText();
/* 207 */               insert_keypart2 = LoadTransportKEK.formattedTextFieldKey2.getText();
/* 208 */               insert_finalkey = insert_keypart1 + insert_keypart2;
/*     */             }
/* 210 */             if (insert_Lenght.equals("Triple")) {
/* 211 */               insert_keypart1 = LoadTransportKEK.formattedTextFieldKey1.getText();
/* 212 */               insert_keypart2 = LoadTransportKEK.formattedTextFieldKey2.getText();
/* 213 */               insert_keypart3 = LoadTransportKEK.formattedTextFieldKey3.getText();
/* 214 */               insert_finalkey = insert_keypart1 + insert_keypart2 + insert_keypart3;
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/* 220 */           e.printStackTrace();
/*     */         }
/*     */         
/*     */       }
/* 224 */     });
/* 225 */     btnVerify.setEnabled(true);
/* 226 */     btnVerify.setActionCommand("Save");
/* 227 */     btnVerify.setBounds(143, 161, 71, 16);
/* 228 */     propertiesPanel.add(btnVerify);
/* 229 */     UseParameters.getEnvriomentUsed();
/* 230 */     if (UseParameters.EnvironmentUsed.EnvironmentUsed().equals("YES")) {
/* 231 */       checkBox_Environment.setSelected(true);
/* 232 */       checkBox_Environment.setEnabled(false);
/*     */     }
/*     */     
/* 235 */     cryptoPanel = new JPanel();
/* 236 */     cryptoPanel.setLayout(null);
/* 237 */     cryptoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cryptogram ...", 4, 2, null, null));
/* 238 */     cryptoPanel.setBounds(12, 13, 300, 250);
/* 239 */     this.contentPanel.add(cryptoPanel);
/*     */     
/* 241 */     formattedTextFieldKey1 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 242 */     formattedTextFieldKey1.setBounds(29, 37, 136, 29);
/* 243 */     cryptoPanel.add(formattedTextFieldKey1);
/*     */     
/*     */ 
/*     */ 
/* 247 */     this.textFieldHeader = new JTextField();
/* 248 */     this.textFieldHeader.setBounds(29, 37, 136, 29);
/* 249 */     cryptoPanel.add(this.textFieldHeader);
/* 250 */     this.textFieldHeader.setVisible(false);
/*     */     
/*     */ 
/*     */ 
/* 254 */     formattedTextFieldKey2 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 255 */     formattedTextFieldKey2.setEditable(false);
/* 256 */     formattedTextFieldKey2.setBounds(29, 90, 136, 29);
/* 257 */     cryptoPanel.add(formattedTextFieldKey2);
/*     */     
/* 259 */     formattedTextFieldKeyBlock = new JFormattedTextField(HexFormat.TextKeyBlockFormat.formatKeyBlock());
/* 260 */     formattedTextFieldKeyBlock.setEditable(false);
/* 261 */     formattedTextFieldKeyBlock.setBounds(29, 90, 136, 29);
/* 262 */     formattedTextFieldKeyBlock.setVisible(false);
/* 263 */     cryptoPanel.add(formattedTextFieldKeyBlock);
/*     */     
/* 265 */     formattedTextFieldKey3 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 266 */     formattedTextFieldKey3.setEditable(false);
/* 267 */     formattedTextFieldKey3.setBounds(29, 146, 136, 29);
/* 268 */     cryptoPanel.add(formattedTextFieldKey3);
/*     */     
/* 270 */     labelPart2 = new JLabel("Part 2");
/* 271 */     labelPart2.setBounds(192, 96, 97, 16);
/* 272 */     cryptoPanel.add(labelPart2);
/*     */     
/* 274 */     labelPart3 = new JLabel("Part 3");
/* 275 */     labelPart3.setBounds(192, 152, 97, 16);
/* 276 */     cryptoPanel.add(labelPart3);
/*     */     
/* 278 */     labelPart1 = new JLabel("Part 1");
/* 279 */     labelPart1.setBounds(192, 43, 97, 16);
/* 280 */     cryptoPanel.add(labelPart1);
/*     */     
/*     */ 
/* 283 */     lblPart_keybloc = new JLabel("Key Block");
/* 284 */     lblPart_keybloc.setBounds(192, 96, 97, 16);
/* 285 */     cryptoPanel.add(lblPart_keybloc);
/*     */     
/* 287 */     lblPart_MAC = new JLabel("MAC");
/* 288 */     lblPart_MAC.setBounds(192, 152, 97, 16);
/* 289 */     cryptoPanel.add(lblPart_MAC);
/*     */     
/* 291 */     lblHeader = new JLabel("Header");
/* 292 */     lblHeader.setBounds(192, 43, 97, 16);
/* 293 */     cryptoPanel.add(lblHeader);
/*     */     
/* 295 */     lblPart_keybloc.setVisible(false);
/* 296 */     lblPart_MAC.setVisible(false);
/* 297 */     lblHeader.setVisible(false);
/*     */     
/* 299 */     JButton btnNewButton = new JButton("Save");
/* 300 */     btnNewButton.setActionCommand("Save");
/* 301 */     btnNewButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent f) {
/*     */         try {
/* 305 */           LoadTransportKEK.this.callActionPerformedSave(f);
/*     */         }
/*     */         catch (IOException e) {
/* 308 */           e.printStackTrace();
/*     */         }
/*     */         
/*     */       }
/* 312 */     });
/* 313 */     btnNewButton.setBounds(345, 285, 97, 25);
/* 314 */     this.contentPanel.add(btnNewButton);
/*     */     
/* 316 */     JButton btnExit = new JButton("Exit");
/* 317 */     btnExit.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 319 */         LoadTransportKEK.this.CloseFrame();
/*     */       }
/* 321 */     });
/* 322 */     btnExit.setBounds(466, 285, 97, 25);
/* 323 */     this.contentPanel.add(btnExit);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 328 */     System.out.println("ActionCommandSendFormaCryptoAssociated: " + actionCommandCryptoAssociated);
/* 329 */     if (actionCommandCryptoAssociated.equals("Show Transport Key")) {
/* 330 */       String resultsetkek = null;
/*     */       try {
/* 332 */         resultsetkek = ExecuteSP.execLoadTransportKek(kwp_Conn);
/*     */       }
/*     */       catch (SQLException e1) {
/* 335 */         e1.printStackTrace();
/*     */       }
/* 337 */       System.out.println("RESULTSETLOADKEK :" + resultsetkek);
/*     */       
/* 339 */       String[] Split_resultsetkek = resultsetkek.split(",");
/* 340 */       String db_Kek = null;String db_CheckDigit = null;String db_Environment = null;String db_HSM = null;String db_keyblock = null;String db_lenght = null;String FormatterKeyPart1 = null;String FormatterKeyPart2 = null;
/* 341 */       String FormatterKeyPart3 = null;
/* 342 */       db_keyblock = Split_resultsetkek[0];
/*     */       
/* 344 */       if (db_keyblock.equals("YES"))
/*     */       {
/* 346 */         FormatterKeyPart1 = Split_resultsetkek[1];
/* 347 */         FormatterKeyPart2 = Split_resultsetkek[2];
/* 348 */         FormatterKeyPart3 = Split_resultsetkek[3];
/* 349 */         db_CheckDigit = Split_resultsetkek[2];
/* 350 */         db_lenght = Split_resultsetkek[5];
/* 351 */         db_Environment = Split_resultsetkek[8];
/*     */         
/* 353 */         labelPart1.setVisible(false);
/* 354 */         labelPart2.setVisible(false);
/* 355 */         labelPart3.setVisible(false);
/*     */         
/* 357 */         lblPart_keybloc.setVisible(true);
/* 358 */         lblPart_MAC.setVisible(true);
/* 359 */         lblHeader.setVisible(true);
/*     */         
/* 361 */         this.textFieldHeader.setVisible(true);
/* 362 */         this.textFieldHeader.setEditable(false);
/*     */         
/* 364 */         formattedTextFieldKey1.setEditable(false);
/* 365 */         formattedTextFieldKey1.setVisible(false);
/* 366 */         formattedTextFieldKeyBlock.setEditable(false);
/* 367 */         formattedTextFieldKeyBlock.setVisible(true);
/* 368 */         formattedTextFieldKey3.setEditable(false);
/*     */         
/* 370 */         formattedTextFieldKey2.setVisible(false);
/*     */         
/* 372 */         this.textFieldHeader.setText(FormatterKeyPart1);
/* 373 */         formattedTextFieldKeyBlock.setText(FormatterKeyPart2);
/* 374 */         formattedTextFieldKey3.setText(FormatterKeyPart3);
/*     */         
/* 376 */         formattedTextFieldCheckDigit.setEditable(false);
/* 377 */         formattedTextFieldCheckDigit.setText(db_CheckDigit.toString());
/*     */         
/* 379 */         comboBoxlengthkey.setVisible(false);
/* 380 */         comboBoxlengthkey_kb.setSelectedItem(db_lenght.toString());
/* 381 */         comboBoxlengthkey_kb.setEnabled(false);
/* 382 */         comboBoxlengthkey_kb.setVisible(true);
/*     */         
/* 384 */         checkBox.setSelected(true);
/* 385 */         checkBox.setEnabled(false);
/*     */         
/* 387 */         btnVerify.setEnabled(false);
/*     */         
/* 389 */         checkBox_Environment.setEnabled(false);
/* 390 */         if (db_Environment.equals("YES")) {
/* 391 */           checkBox_Environment.setSelected(true);
/*     */         } else {
/* 393 */           checkBox_Environment.setSelected(false);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 398 */       if (db_keyblock.equals("NO")) {
/* 399 */         db_lenght = Split_resultsetkek[3];
/* 400 */         db_Environment = Split_resultsetkek[8];
/* 401 */         db_CheckDigit = Split_resultsetkek[2];
/*     */         
/* 403 */         if (db_lenght.equals("Single")) {
/* 404 */           FormatterKeyPart1 = Split_resultsetkek[1];
/*     */         }
/* 406 */         if (db_lenght.equals("Double")) {
/* 407 */           FormatterKeyPart1 = Split_resultsetkek[1].substring(0, 16);
/* 408 */           FormatterKeyPart2 = Split_resultsetkek[2].substring(16, 32);
/*     */         }
/* 410 */         if (db_lenght.equals("Triple")) {
/* 411 */           FormatterKeyPart1 = Split_resultsetkek[1].substring(0, 16);
/* 412 */           FormatterKeyPart2 = Split_resultsetkek[2].substring(16, 32);
/* 413 */           FormatterKeyPart3 = Split_resultsetkek[3].substring(32, 48);
/*     */         }
/*     */         
/* 416 */         labelPart1.setVisible(true);
/* 417 */         labelPart2.setVisible(true);
/* 418 */         labelPart3.setVisible(true);
/*     */         
/* 420 */         lblPart_keybloc.setVisible(false);
/* 421 */         lblPart_MAC.setVisible(false);
/* 422 */         lblHeader.setVisible(false);
/*     */         
/* 424 */         this.textFieldHeader.setVisible(false);
/* 425 */         this.textFieldHeader.setEditable(false);
/* 426 */         formattedTextFieldKey1.setVisible(true);
/*     */         
/* 428 */         formattedTextFieldKeyBlock.setEditable(false);
/* 429 */         formattedTextFieldKeyBlock.setVisible(false);
/*     */         
/* 431 */         formattedTextFieldKey1.setEditable(false);
/* 432 */         formattedTextFieldKey2.setEditable(false);
/* 433 */         formattedTextFieldKey3.setEditable(false);
/* 434 */         comboBoxlengthkey_kb.setVisible(false);
/* 435 */         comboBoxlengthkey.setSelectedItem(db_lenght);
/*     */         
/* 437 */         checkBox.setSelected(false);
/* 438 */         checkBox.setEnabled(false);
/*     */         
/* 440 */         formattedTextFieldCheckDigit.setEditable(false);
/* 441 */         formattedTextFieldCheckDigit.setText(db_CheckDigit);
/*     */         
/* 443 */         checkBox_Environment.setEnabled(false);
/* 444 */         if (db_Environment.equals("YES")) {
/* 445 */           checkBox_Environment.setSelected(true);
/*     */         } else {
/* 447 */           checkBox_Environment.setSelected(false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void callActionPerformedCheck(ActionEvent e)
/*     */   {
/* 456 */     String command = e.getActionCommand();
/* 457 */     System.out.println("Comando al Entrar: " + command);
/*     */     
/* 459 */     if ("LenghtKey".equalsIgnoreCase(command)) {
/* 460 */       System.out.println("Entre Key Block");
/* 461 */       if (comboBoxlengthkey.getSelectedItem().equals("Single")) {
/* 462 */         System.out.println(comboBoxlengthkey.getSelectedItem());
/* 463 */         formattedTextFieldKey1.setEditable(true);
/* 464 */         formattedTextFieldKey2.setEditable(false);
/* 465 */         formattedTextFieldKey3.setEditable(false);
/*     */       }
/* 467 */       if (comboBoxlengthkey.getSelectedItem().equals("Double")) {
/* 468 */         System.out.println(comboBoxlengthkey.getSelectedItem());
/* 469 */         formattedTextFieldKey1.setEditable(true);
/* 470 */         formattedTextFieldKey2.setEditable(true);
/* 471 */         formattedTextFieldKey3.setEditable(false);
/*     */       }
/* 473 */       if (comboBoxlengthkey.getSelectedItem().equals("Triple")) {
/* 474 */         System.out.println(comboBoxlengthkey.getSelectedItem());
/* 475 */         formattedTextFieldKey1.setEditable(true);
/* 476 */         formattedTextFieldKey2.setEditable(true);
/* 477 */         formattedTextFieldKey3.setEditable(true);
/*     */       }
/*     */     }
/* 480 */     if ("KeyBlockFlag".equalsIgnoreCase(command)) {
/* 481 */       System.out.println("Entre Lenght Key");
/* 482 */       System.out.println(this.keyblock);
/* 483 */       if (this.keyblock)
/*     */       {
/* 485 */         comboBoxlengthkey.setVisible(false);
/*     */         
/* 487 */         comboBoxlengthkey_kb.setVisible(true);
/* 488 */         comboBoxlengthkey_kb.setEditable(false);
/*     */         
/* 490 */         labelPart1.setVisible(false);
/* 491 */         labelPart2.setVisible(false);
/* 492 */         labelPart3.setVisible(false);
/*     */         
/* 494 */         formattedTextFieldKey1.setEditable(false);
/* 495 */         formattedTextFieldKey1.setVisible(false);
/*     */         
/* 497 */         this.textFieldHeader.setVisible(true);
/* 498 */         this.textFieldHeader.setEditable(false);
/* 499 */         this.textFieldHeader.setText("1KDNE000");
/*     */         
/* 501 */         formattedTextFieldKeyBlock.setVisible(true);
/* 502 */         formattedTextFieldKeyBlock.setEditable(true);
/* 503 */         formattedTextFieldKey2.setVisible(false);
/* 504 */         formattedTextFieldKey2.setEditable(false);
/* 505 */         formattedTextFieldKey3.setEditable(true);
/*     */         
/* 507 */         lblPart_keybloc.setVisible(true);
/* 508 */         lblPart_MAC.setVisible(true);
/* 509 */         lblHeader.setVisible(true);
/*     */       }
/*     */       else {
/* 512 */         comboBoxlengthkey.setVisible(true);
/*     */         
/* 514 */         comboBoxlengthkey_kb.setVisible(false);
/* 515 */         comboBoxlengthkey_kb.setEditable(false);
/*     */         
/* 517 */         lblPart_keybloc.setVisible(false);
/* 518 */         lblPart_MAC.setVisible(false);
/* 519 */         lblHeader.setVisible(false);
/*     */         
/* 521 */         this.textFieldHeader.setVisible(false);
/* 522 */         this.textFieldHeader.setEditable(false);
/*     */         
/* 524 */         formattedTextFieldKey1.setVisible(true);
/* 525 */         formattedTextFieldKey1.setEditable(true);
/* 526 */         formattedTextFieldKeyBlock.setVisible(false);
/* 527 */         formattedTextFieldKeyBlock.setEditable(false);
/* 528 */         formattedTextFieldKey2.setEditable(false);
/* 529 */         formattedTextFieldKey2.setVisible(true);
/* 530 */         formattedTextFieldKey3.setEditable(false);
/*     */         
/* 532 */         labelPart1.setVisible(true);
/* 533 */         labelPart2.setVisible(true);
/* 534 */         labelPart3.setVisible(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void callActionPerformedSave(ActionEvent f) throws IOException
/*     */   {
/* 541 */     String insert_Lenght = null;String insert_keypart1 = null;String insert_keypart2 = null;
/* 542 */     String insert_keypart3 = null;String insert_finalkey = null;String insert_checkdigit = null;String insert_typehsm = null;
/* 543 */     String date = null;String insert_keyBlock = null;String insert_environment = null;
/* 544 */     String verify = null;
/* 545 */     String confirmation = null;
/* 546 */     String command = f.getActionCommand();
/*     */     
/* 548 */     if ("Save".equalsIgnoreCase(command)) {
/* 549 */       if (this.keyblock) {
/* 550 */         insert_keyBlock = "YES";
/* 551 */         insert_Lenght = (String)comboBoxlengthkey_kb.getSelectedItem();
/* 552 */         insert_keypart1 = this.textFieldHeader.getText();
/* 553 */         insert_keypart2 = formattedTextFieldKeyBlock.getText();
/* 554 */         insert_keypart3 = formattedTextFieldKey3.getText();
/* 555 */         insert_finalkey = insert_keypart1 + "," + insert_keypart2 + "," + insert_keypart3;
/*     */       }
/*     */       else {
/* 558 */         insert_keyBlock = "NO";
/* 559 */         insert_Lenght = (String)comboBoxlengthkey.getSelectedItem();
/* 560 */         if (insert_Lenght.equals("Single")) {
/* 561 */           insert_keypart1 = formattedTextFieldKey1.getText();
/* 562 */           insert_finalkey = insert_keypart1;
/*     */         }
/* 564 */         if (insert_Lenght.equals("Double")) {
/* 565 */           insert_keypart1 = formattedTextFieldKey1.getText();
/* 566 */           insert_keypart2 = formattedTextFieldKey2.getText();
/* 567 */           insert_finalkey = insert_keypart1 + insert_keypart2;
/*     */         }
/* 569 */         if (insert_Lenght.equals("Triple")) {
/* 570 */           insert_keypart1 = formattedTextFieldKey1.getText();
/* 571 */           insert_keypart2 = formattedTextFieldKey2.getText();
/* 572 */           insert_keypart3 = formattedTextFieldKey3.getText();
/* 573 */           insert_finalkey = insert_keypart1 + insert_keypart2 + insert_keypart3;
/*     */         }
/*     */       }
/* 576 */       insert_checkdigit = formattedTextFieldCheckDigit.getText();
/* 577 */       UseParameters.getTypeHsm();
/* 578 */       insert_typehsm = UseParameters.TypeHSM.typeHsm();
/* 579 */       UseParameters.getEnvriomentUsed();
/* 580 */       insert_environment = UseParameters.EnvironmentUsed.EnvironmentUsed();
/* 581 */       date = SystemDate.GenerateDate().toString();
/*     */       
/* 583 */       Connection insertkwp = kwp_Conn;
/* 584 */       System.out.println("Primer Mensaje");
/* 585 */       System.out.println(insert_finalkey);
/*     */       
/* 587 */       int dialogButton = 0;
/* 588 */       int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirmation", dialogButton);
/* 589 */       if (dialogResult == 0) {
/* 590 */         System.out.println("Yes option");
/*     */         
/* 592 */         String CheckDigitGenerated = null;
/*     */         try {
/* 594 */           String CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 595 */           CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*     */           
/* 597 */           System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedResponseHsm);
/*     */           
/* 599 */           if (CryptoAssociated.failedResponseHsm) {
/* 600 */             System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedResponseHsm);
/* 601 */             JOptionPane.showMessageDialog(null, "Message Error : " + CheckDigitGenerated, "Error Message", 0);
/* 602 */             CryptoAssociated.failedResponseHsm = false;
/*     */           }
/*     */           else {
/* 605 */             System.out.println("Check Digit From HSM: " + CheckDigitGenerated);
/* 606 */             if (insert_checkdigit.equals(CheckDigitGenerated))
/*     */             {
/* 608 */               confirmation = ExecuteSP.execInsertTransportKek(insert_finalkey, insert_checkdigit, insert_typehsm, date, insert_Lenght, 
/* 609 */                 insert_keyBlock, insert_environment, kwp_Conn);
/* 610 */               JOptionPane.showMessageDialog(null, "Message : " + confirmation);
/* 611 */               UseParameters.setSaveCheckDigitKek(insert_checkdigit);
/*     */             }
/*     */             else
/*     */             {
/* 615 */               JOptionPane.showMessageDialog(null, "An Error Ocurred, Verify teh Input Data", "Error Message", 0); }
/* 616 */             CryptoAssociated.failedResponseHsm = false;
/*     */           }
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/* 621 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 626 */         System.out.println("No Option");
/* 627 */         JOptionPane.showMessageDialog(null, "Operation Cancelled");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void CloseFrame()
/*     */   {
/* 634 */     JDialog repaintCryptoAssociated = new JDialog();
/* 635 */     repaintCryptoAssociated.repaint();
/* 636 */     super.dispose();
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/LoadTransportKEK.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */