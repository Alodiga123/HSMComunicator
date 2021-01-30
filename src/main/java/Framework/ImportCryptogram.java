/*     */ package Framework;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.text.ParseException;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import javax.swing.text.MaskFormatter;

import com.alodiga.hsm.AtallaCryptoCommand;
import com.alodiga.hsm.CryptoConnection;
import com.alodiga.hsm.UnpackAtallaCryptoCommand;
import com.alodiga.hsm.data.ExecuteSP;
import com.alodiga.hsm.operations.HexFormat;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.HexFormat.TextCheckDigit;
import com.alodiga.hsm.operations.HexFormat.TextKeyBlockFormat;
import com.alodiga.hsm.operations.HexFormat.TextKeyFormat;
import com.alodiga.hsm.operations.UseParameters.CheckDigitKek;
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
/*     */ public class ImportCryptogram
/*     */   extends JFrame
/*     */ {
/*     */   private JPanel contentPane;
/*     */   String strConsultaSQL;
/*     */   Statement estSQL1;
/*     */   ResultSet rs;
/*  60 */   Connection cryptoconn = null;
/*  61 */   private JTextField textFieldHeader = null;
/*  62 */   private JTextField textFieldcrypto_kek; private static JTextField textFieldHeader_kek = null;
/*     */   private JTextField textField_Checkdigit;
/*  64 */   JFormattedTextField formattedTextFieldcryptogram = null;
/*  65 */   JFormattedTextField formattedTextField_crypto2; JFormattedTextField formattedTextField_CryptoKeyBlock = null;
/*  66 */   JFormattedTextField formattedTextField_crypto3 = null;
/*  67 */   JFormattedTextField formattedTextFieldcryptogram_kek = null;
/*     */   JFormattedTextField formattedTextField_crypto2_kek;
/*  69 */   static JFormattedTextField formattedTextField_CryptoKeyBlock_kek = null;
/*  70 */   static JFormattedTextField formattedTextField_crypto3_kek = null;
/*  71 */   JFormattedTextField formattedTextField_Checkdigit = null;
/*  72 */   JComboBox comboBox_keytype = null;
/*  73 */   JComboBox comboBoxkeylenght = null;
/*  74 */   JComboBox comboBoxkeylenght_kb = null;
/*  75 */   private String[] keyLenght = new String[3];
/*  76 */   private String[] keyType = new String[8];
/*  77 */   private MaskFormatter Hexformat = null;
/*  78 */   private JCheckBox chckbxNewCheckBox = null; private JCheckBox checkBox = null;
/*     */   private boolean keyblock;
/*  80 */   private JLabel lblCryptogram = null;
/*  81 */   private JLabel lblPart_1 = null;
/*  82 */   private JLabel lblPart = null;
/*  83 */   private JLabel lblCryptogram_kek = null;
/*  84 */   private JLabel lblPart_1_kek = null;
/*  85 */   private JLabel lblPart_kek = null;
/*  86 */   private JLabel lblPart_keybloc = null;
/*  87 */   private JLabel lblPart_MAC = null;
/*  88 */   private JLabel lblHeader = null;
/*  89 */   private JLabel lblPart_keybloc_kek = null;
/*  90 */   private JLabel lblPart_MAC_kek = null;
/*  91 */   private JLabel lblHeader_kek = null;
/*  92 */   private JButton findbutton = null;
/*     */   private JButton btnImport;
/*  94 */   private JButton btnNewButton_1 = null; private JButton btnNewButton = null;
/*  95 */   public static Connection transportKEKConn = null;
/*  96 */   public static boolean failedResponseHsm = false; public static boolean failedImportHsm = false;
/*     */   
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 101 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*     */         try {
/* 104 */           ImportCryptogram frame = new ImportCryptogram();
/* 105 */           frame.setVisible(true);
/*     */         } catch (Exception e) {
/* 107 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */   public ImportCryptogram()
/*     */     throws IOException
/*     */   {
/* 117 */     setTitle("Import Cryptograms");
/* 118 */     setLocationRelativeTo(null);
/* 119 */     setDefaultCloseOperation(0);
/* 120 */     setBounds(100, 100, 728, 519);
/* 121 */     this.contentPane = new JPanel();
/* 122 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 123 */     setContentPane(this.contentPane);
/* 124 */     this.contentPane.setLayout(null);
/*     */     
/*     */ 
/* 127 */     HexFormat.getHexSingleFormat();
/* 128 */     HexFormat.getHexSingleFormatCheckDigit();
/* 129 */     HexFormat.getHexKeyBlockFormat();
/*     */     
/*     */     try
/*     */     {
/* 133 */       this.Hexformat = new MaskFormatter("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
/*     */     }
/*     */     catch (ParseException e1) {
/* 136 */       e1.printStackTrace();
/*     */     }
/* 138 */     this.Hexformat.setValidCharacters("0123456789abcdefABCDEF");
/*     */     
/*     */ 
/*     */ 
/* 142 */     JPanel panel_crypto = new JPanel();
/* 143 */     panel_crypto.setBounds(27, 34, 302, 203);
/* 144 */     panel_crypto.setLayout(null);
/* 145 */     panel_crypto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cryptogram ...", 4, 2, null, null));
/* 146 */     this.contentPane.add(panel_crypto);
/*     */     
/* 148 */     this.formattedTextFieldcryptogram = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 149 */     this.formattedTextFieldcryptogram.setBounds(29, 37, 136, 29);
/* 150 */     panel_crypto.add(this.formattedTextFieldcryptogram);
/* 151 */     this.formattedTextFieldcryptogram.setVisible(true);
/*     */     
/* 153 */     this.textFieldHeader = new JTextField();
/* 154 */     this.textFieldHeader.setBounds(29, 37, 136, 29);
/* 155 */     panel_crypto.add(this.textFieldHeader);
/* 156 */     this.textFieldHeader.setVisible(false);
/* 157 */     this.textFieldHeader.setEditable(false);
/*     */     
/* 159 */     this.formattedTextField_crypto2 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 160 */     this.formattedTextField_crypto2.setEditable(false);
/* 161 */     this.formattedTextField_crypto2.setBounds(29, 90, 136, 29);
/* 162 */     panel_crypto.add(this.formattedTextField_crypto2);
/*     */     
/*     */ 
/* 165 */     this.formattedTextField_CryptoKeyBlock = new JFormattedTextField(HexFormat.TextKeyBlockFormat.formatKeyBlock());
/* 166 */     this.formattedTextField_CryptoKeyBlock.setEditable(false);
/* 167 */     this.formattedTextField_CryptoKeyBlock.setBounds(29, 90, 136, 29);
/* 168 */     panel_crypto.add(this.formattedTextField_CryptoKeyBlock);
/*     */     
/*     */ 
/* 171 */     this.formattedTextField_crypto3 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 172 */     this.formattedTextField_crypto3.setEditable(false);
/* 173 */     this.formattedTextField_crypto3.setBounds(29, 146, 136, 29);
/* 174 */     panel_crypto.add(this.formattedTextField_crypto3);
/*     */     
/* 176 */     this.lblPart = new JLabel("Part 2");
/* 177 */     this.lblPart.setBounds(192, 96, 97, 16);
/* 178 */     panel_crypto.add(this.lblPart);
/*     */     
/* 180 */     this.lblPart_1 = new JLabel("Part 3");
/* 181 */     this.lblPart_1.setBounds(192, 152, 97, 16);
/* 182 */     panel_crypto.add(this.lblPart_1);
/*     */     
/* 184 */     this.lblCryptogram = new JLabel("Part 1");
/* 185 */     this.lblCryptogram.setBounds(192, 43, 97, 16);
/* 186 */     panel_crypto.add(this.lblCryptogram);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 191 */     JPanel panel_kek = new JPanel();
/* 192 */     panel_kek.setBounds(27, 250, 302, 213);
/* 193 */     panel_kek.setLayout(null);
/* 194 */     panel_kek.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kek to use ...", 4, 2, null, null));
/* 195 */     this.contentPane.add(panel_kek);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 202 */     textFieldHeader_kek = new JTextField();
/* 203 */     textFieldHeader_kek.setBounds(29, 37, 136, 29);
/* 204 */     panel_kek.add(textFieldHeader_kek);
/* 205 */     textFieldHeader_kek.setVisible(true);
/* 206 */     textFieldHeader_kek.setEditable(false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 215 */     formattedTextField_CryptoKeyBlock_kek = new JFormattedTextField(HexFormat.TextKeyBlockFormat.formatKeyBlock());
/* 216 */     formattedTextField_CryptoKeyBlock_kek.setEditable(true);
/* 217 */     formattedTextField_CryptoKeyBlock_kek.setBounds(29, 90, 136, 29);
/* 218 */     panel_kek.add(formattedTextField_CryptoKeyBlock_kek);
/*     */     
/*     */ 
/* 221 */     formattedTextField_crypto3_kek = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/* 222 */     formattedTextField_crypto3_kek.setEditable(true);
/* 223 */     formattedTextField_crypto3_kek.setBounds(29, 146, 136, 29);
/* 224 */     panel_kek.add(formattedTextField_crypto3_kek);
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
/* 240 */     this.keyLenght[0] = "Single";
/* 241 */     this.keyLenght[1] = "Double";
/* 242 */     this.keyLenght[2] = "Triple";
/*     */     
/* 244 */     this.keyType[0] = "KEK";
/* 245 */     this.keyType[1] = "KWP";
/* 246 */     this.keyType[2] = "PVK";
/* 247 */     this.keyType[3] = "CVK";
/*     */     
/* 249 */     this.keyType[4] = "CAK";
/* 250 */     this.keyType[5] = "MAK";
/*     */     
/* 252 */     JPanel panel_detail = new JPanel();
/* 253 */     panel_detail.setBounds(341, 34, 354, 203);
/* 254 */     this.contentPane.add(panel_detail);
/* 255 */     panel_detail.setLayout(null);
/*     */     
/* 257 */     JLabel lblKeyType = new JLabel("Key Type");
/* 258 */     lblKeyType.setBounds(143, 98, 97, 16);
/* 259 */     panel_detail.add(lblKeyType);
/* 260 */     this.comboBox_keytype = new JComboBox(this.keyType);
/* 261 */     this.comboBox_keytype.setActionCommand("KeyType");
/* 262 */     this.comboBox_keytype.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 264 */         ImportCryptogram.this.callActionListKeyblock(arg0);
/*     */       }
/* 266 */     });
/* 267 */     this.comboBox_keytype.setBounds(12, 95, 119, 22);
/* 268 */     panel_detail.add(this.comboBox_keytype);
/*     */     
/* 270 */     this.comboBoxkeylenght = new JComboBox(this.keyLenght);
/* 271 */     this.comboBoxkeylenght.setBounds(12, 33, 119, 22);
/* 272 */     this.comboBoxkeylenght.setActionCommand("KeyLenght");
/* 273 */     this.comboBoxkeylenght.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 275 */         ImportCryptogram.this.callActionPerformedCheck(e);
/*     */       }
/* 277 */     });
/* 278 */     panel_detail.add(this.comboBoxkeylenght);
/*     */     
/* 280 */     this.comboBoxkeylenght_kb = new JComboBox(this.keyLenght);
/* 281 */     this.comboBoxkeylenght_kb.setBounds(12, 33, 119, 22);
/* 282 */     panel_detail.add(this.comboBoxkeylenght_kb);
/* 283 */     this.comboBoxkeylenght_kb.setEditable(false);
/* 284 */     this.comboBoxkeylenght_kb.setVisible(false);
/*     */     
/* 286 */     JLabel label_3 = new JLabel("Length");
/* 287 */     label_3.setBounds(143, 36, 97, 16);
/* 288 */     panel_detail.add(label_3);
/*     */     
/* 290 */     this.formattedTextField_Checkdigit = new JFormattedTextField(HexFormat.TextCheckDigit.formattrypeCd());
/* 291 */     this.formattedTextField_Checkdigit.setBounds(12, 146, 119, 29);
/* 292 */     panel_detail.add(this.formattedTextField_Checkdigit);
/* 293 */     this.formattedTextField_Checkdigit.setColumns(10);
/*     */     
/* 295 */     JLabel label_2 = new JLabel("Check Digit");
/* 296 */     label_2.setBounds(143, 138, 97, 16);
/* 297 */     panel_detail.add(label_2);
/* 298 */     panel_detail.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Crypto Properties ...", 4, 2, null, null));
/*     */     
/* 300 */     this.keyblock = false;
/* 301 */     this.chckbxNewCheckBox = new JCheckBox("Key Block");
/* 302 */     this.chckbxNewCheckBox.setActionCommand("KeyBlockFlag");
/* 303 */     this.chckbxNewCheckBox.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 305 */         ImportCryptogram.this.keyblock = ImportCryptogram.this.chckbxNewCheckBox.isSelected();
/* 306 */         ImportCryptogram.this.callActionPerformedCheck(e);
/* 307 */         System.out.println("Despues del Metodo callActionPerformedCheck :" + ImportCryptogram.this.chckbxNewCheckBox.isSelected());
/* 308 */         System.out.println("Despues del Metodo callActionPerformedCheck :" + ImportCryptogram.this.comboBoxkeylenght_kb.getSelectedItem());
/* 309 */         System.out.println("Despues del Metodo callActionPerformedCheck 2 :" + ImportCryptogram.this.comboBoxkeylenght.getSelectedItem());
/*     */       }
/* 311 */     });
/* 312 */     this.chckbxNewCheckBox.setBounds(248, 94, 113, 25);
/* 313 */     panel_detail.add(this.chckbxNewCheckBox);
/*     */     
/*     */ 
/* 316 */     JButton btnGenerate = new JButton("Verify");
/* 317 */     btnGenerate.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 319 */         String insert_Lenght = null;String insert_keypart1 = null;String insert_keypart2 = null;String insert_keyblock = null;
/* 320 */         String insert_keypart3 = null;String insert_finalkey = null;
/*     */         
/*     */ 
/*     */         try
/*     */         {
/* 325 */           if (ImportCryptogram.this.keyblock) {
/* 326 */             insert_keyblock = "YES";
/* 327 */             insert_Lenght = (String)ImportCryptogram.this.comboBoxkeylenght_kb.getSelectedItem();
/* 328 */             insert_keypart1 = ImportCryptogram.this.textFieldHeader.getText();
/* 329 */             insert_keypart2 = ImportCryptogram.this.formattedTextField_CryptoKeyBlock.getText();
/* 330 */             insert_keypart3 = ImportCryptogram.this.formattedTextField_crypto3.getText();
/* 331 */             insert_finalkey = insert_keypart1 + "," + insert_keypart2 + "," + insert_keypart3;
/*     */             
/* 333 */             if (insert_keypart2.isEmpty()) {
/* 334 */               JOptionPane.showMessageDialog(null, "Please Insert a Valid Value for Key Block");
/*     */             }
/* 336 */             else if (insert_keypart3.isEmpty()) {
/* 337 */               JOptionPane.showMessageDialog(null, "Please Insert a Valid Value for MAC");
/*     */             } else {
/* 339 */               String CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 340 */               String CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/* 341 */               System.out.println("Valor de la Bandera de Error de HSM CheckDigit: " + ImportCryptogram.failedResponseHsm);
/*     */               
/* 343 */               if (ImportCryptogram.failedResponseHsm) {
/* 344 */                 System.out.println("Valor de la Bandera de Error de HSM CheckDigit: " + ImportCryptogram.failedResponseHsm);
/* 345 */                 JOptionPane.showMessageDialog(null, "Message Error : " + CheckDigitGenerated, "Error Message", 0);
/* 346 */                 ImportCryptogram.failedResponseHsm = false;
/*     */               } else {
/* 348 */                 ImportCryptogram.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/* 349 */                 ImportCryptogram.failedResponseHsm = false;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 355 */           if (!ImportCryptogram.this.keyblock) {
/* 356 */             insert_keyblock = "NO";
/* 357 */             insert_Lenght = (String)ImportCryptogram.this.comboBoxkeylenght.getSelectedItem();
/* 358 */             if (insert_Lenght.equals("Single")) {
/* 359 */               insert_keypart1 = ImportCryptogram.this.formattedTextFieldcryptogram.getText();
/* 360 */               insert_finalkey = insert_keypart1;
/*     */               
/* 362 */               if (insert_finalkey.isEmpty()) {
/* 363 */                 JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*     */               } else {
/* 365 */                 String CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 366 */                 String CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/* 367 */                 ImportCryptogram.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*     */               }
/*     */             }
/* 370 */             if (insert_Lenght.equals("Double")) {
/* 371 */               insert_keypart1 = ImportCryptogram.this.formattedTextFieldcryptogram.getText();
/* 372 */               insert_keypart2 = ImportCryptogram.this.formattedTextField_crypto2.getText();
/* 373 */               insert_finalkey = insert_keypart1 + insert_keypart2;
/*     */               
/* 375 */               if (insert_finalkey.isEmpty()) {
/* 376 */                 JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*     */               } else {
/* 378 */                 String CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 379 */                 String CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/* 380 */                 ImportCryptogram.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*     */               }
/*     */             }
/* 383 */             if (insert_Lenght.equals("Triple")) {
/* 384 */               insert_keypart1 = ImportCryptogram.this.formattedTextFieldcryptogram.getText();
/* 385 */               insert_keypart2 = ImportCryptogram.this.formattedTextField_crypto2.getText();
/* 386 */               insert_keypart3 = ImportCryptogram.this.formattedTextField_crypto3.getText();
/* 387 */               insert_finalkey = insert_keypart1 + insert_keypart2 + insert_keypart3;
/*     */               
/* 389 */               if (insert_finalkey.isEmpty()) {
/* 390 */                 JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*     */               } else {
/* 392 */                 String CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 393 */                 String CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/* 394 */                 ImportCryptogram.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 400 */           e.printStackTrace();
/*     */         }
/*     */         
/*     */       }
/* 404 */     });
/* 405 */     btnGenerate.setEnabled(true);
/* 406 */     btnGenerate.setActionCommand("Save");
/* 407 */     btnGenerate.setBounds(143, 161, 71, 16);
/* 408 */     panel_detail.add(btnGenerate);
/*     */     
/* 410 */     this.lblPart_keybloc = new JLabel("Key Block");
/* 411 */     this.lblPart_keybloc.setBounds(192, 96, 97, 16);
/* 412 */     panel_crypto.add(this.lblPart_keybloc);
/* 413 */     this.lblPart_keybloc.setVisible(false);
/*     */     
/* 415 */     this.lblPart_MAC = new JLabel("MAC");
/* 416 */     this.lblPart_MAC.setBounds(192, 152, 97, 16);
/* 417 */     panel_crypto.add(this.lblPart_MAC);
/* 418 */     this.lblPart_MAC.setVisible(false);
/*     */     
/* 420 */     this.lblHeader = new JLabel("Header");
/* 421 */     this.lblHeader.setBounds(192, 43, 97, 16);
/* 422 */     panel_crypto.add(this.lblHeader);
/* 423 */     this.lblHeader.setVisible(false);
/*     */     
/*     */ 
/* 426 */     textFieldHeader_kek.setText("1KDNE000");
/* 427 */     this.lblPart_keybloc_kek = new JLabel("Key Block");
/* 428 */     this.lblPart_keybloc_kek.setBounds(192, 96, 97, 16);
/* 429 */     panel_kek.add(this.lblPart_keybloc_kek);
/* 430 */     this.lblPart_keybloc_kek.setVisible(false);
/*     */     
/* 432 */     this.lblPart_MAC_kek = new JLabel("MAC");
/* 433 */     this.lblPart_MAC_kek.setBounds(192, 152, 97, 16);
/* 434 */     panel_kek.add(this.lblPart_MAC_kek);
/* 435 */     this.lblPart_MAC_kek.setVisible(false);
/*     */     
/* 437 */     this.lblHeader_kek = new JLabel("Header");
/* 438 */     this.lblHeader_kek.setBounds(192, 43, 97, 16);
/* 439 */     panel_kek.add(this.lblHeader_kek);
/* 440 */     this.lblHeader_kek.setVisible(false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 446 */     JPanel panelOperations = new JPanel();
/* 447 */     panelOperations.setBounds(341, 389, 228, 74);
/* 448 */     this.contentPane.add(panelOperations);
/* 449 */     panelOperations.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operations ...", 4, 2, null, null));
/* 450 */     panelOperations.setLayout(null);
/*     */     
/* 452 */     this.btnImport = new JButton("Import");
/* 453 */     this.btnImport.setActionCommand("Import");
/* 454 */     this.btnImport.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 456 */         ImportCryptogram.this.actionImportCryptogram(arg0);
/*     */       }
/*     */       
/* 459 */     });
/* 460 */     this.btnImport.setBounds(12, 28, 97, 25);
/* 461 */     panelOperations.add(this.btnImport);
/* 462 */     this.btnImport.setEnabled(true);
/*     */     
/* 464 */     JButton btnExit = new JButton("Exit");
/* 465 */     btnExit.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 467 */         ImportCryptogram.this.CloseFrame();
/*     */ 
/*     */       }
/*     */       
/*     */ 
/* 472 */     });
/* 473 */     btnExit.setBounds(121, 28, 97, 25);
/* 474 */     panelOperations.add(btnExit);
/*     */     
/* 476 */     JPanel panelExport = new JPanel();
/* 477 */     panelExport.setBounds(341, 260, 228, 74);
/* 478 */     this.contentPane.add(panelExport);
/* 479 */     panelExport.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Import KEK ...", 4, 2, null, null));
/* 480 */     panelExport.setLayout(null);
/*     */     
/*     */ 
/* 483 */     this.btnNewButton = new JButton("Load Transport Key");
/* 484 */     this.btnNewButton.setActionCommand("SetKEK");
/* 485 */     this.btnNewButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 487 */         ImportCryptogram.this.callActionSetKEK(e);
/*     */       }
/* 489 */     });
/* 490 */     this.btnNewButton.setBounds(12, 25, 204, 25);
/* 491 */     panelExport.add(this.btnNewButton);
/* 492 */     this.btnNewButton.setText("Load Transport Key");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void callActionPerformedCheck(ActionEvent e)
/*     */   {
/* 499 */     String command = e.getActionCommand();
/* 500 */     System.out.println("Comando al Entrar: " + command);
/*     */     
/* 502 */     if ("KeyLenght".equalsIgnoreCase(command)) {
/* 503 */       System.out.println("Entre Key Block");
/* 504 */       if (this.comboBoxkeylenght.getSelectedItem().equals("Single")) {
/* 505 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 506 */         this.formattedTextField_crypto2.setEditable(false);
/* 507 */         this.formattedTextField_crypto3.setEditable(false);
/*     */       }
/* 509 */       if (this.comboBoxkeylenght.getSelectedItem().equals("Double")) {
/* 510 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 511 */         this.formattedTextField_crypto2.setEditable(true);
/* 512 */         this.formattedTextField_crypto3.setEditable(false);
/*     */       }
/* 514 */       if (this.comboBoxkeylenght.getSelectedItem().equals("Triple")) {
/* 515 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 516 */         this.formattedTextField_crypto2.setEditable(true);
/* 517 */         this.formattedTextField_crypto3.setEditable(true);
/*     */       }
/*     */     }
/* 520 */     if ("KeyBlockFlag".equalsIgnoreCase(command)) {
/* 521 */       System.out.println("Entre Lenght Key");
/* 522 */       System.out.println(this.keyblock);
/* 523 */       if (this.keyblock)
/*     */       {
/* 525 */         this.comboBoxkeylenght.setEnabled(false);
/* 526 */         this.comboBoxkeylenght.setVisible(false);
/*     */         
/* 528 */         this.comboBoxkeylenght_kb.setEnabled(true);
/* 529 */         this.comboBoxkeylenght_kb.setVisible(true);
/*     */         
/* 531 */         this.formattedTextFieldcryptogram.setEditable(false);
/* 532 */         this.formattedTextFieldcryptogram.setEnabled(false);
/* 533 */         this.formattedTextFieldcryptogram.setVisible(false);
/*     */         
/* 535 */         this.textFieldHeader.setVisible(true);
/* 536 */         this.textFieldHeader.setEditable(false);
/*     */         
/* 538 */         this.formattedTextField_crypto3.setEditable(true);
/*     */         
/* 540 */         this.formattedTextField_crypto2.setEditable(false);
/* 541 */         this.formattedTextField_crypto2.setVisible(false);
/*     */         
/* 543 */         this.formattedTextField_CryptoKeyBlock.setVisible(true);
/* 544 */         this.formattedTextField_CryptoKeyBlock.setEditable(true);
/*     */         
/* 546 */         this.lblCryptogram.setVisible(false);
/* 547 */         this.lblPart.setVisible(false);
/* 548 */         this.lblPart_1.setVisible(false);
/*     */         
/* 550 */         this.lblPart_keybloc.setVisible(true);
/* 551 */         this.lblPart_MAC.setVisible(true);
/* 552 */         this.lblHeader.setVisible(true);
/*     */       }
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
/* 580 */       if (!this.keyblock) {
/* 581 */         this.comboBoxkeylenght_kb.setEnabled(false);
/* 582 */         this.comboBoxkeylenght_kb.setVisible(false);
/*     */         
/* 584 */         this.comboBoxkeylenght.setEnabled(true);
/* 585 */         this.comboBoxkeylenght.setVisible(true);
/*     */         
/*     */ 
/* 588 */         this.textFieldHeader.setVisible(false);
/* 589 */         this.textFieldHeader.setEditable(false);
/*     */         
/* 591 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 592 */         this.formattedTextFieldcryptogram.setEnabled(true);
/* 593 */         this.formattedTextFieldcryptogram.setVisible(true);
/*     */         
/* 595 */         this.formattedTextField_crypto3.setEditable(false);
/* 596 */         this.formattedTextField_crypto2.setEditable(false);
/* 597 */         this.formattedTextField_crypto2.setVisible(true);
/*     */         
/* 599 */         this.formattedTextField_CryptoKeyBlock.setVisible(false);
/* 600 */         this.formattedTextField_CryptoKeyBlock.setEditable(false);
/*     */         
/* 602 */         this.formattedTextFieldcryptogram.setText("");
/* 603 */         this.formattedTextField_crypto2.setText("");
/* 604 */         this.formattedTextField_crypto3.setText("");
/*     */         
/* 606 */         this.lblPart_keybloc.setVisible(false);
/* 607 */         this.lblPart_MAC.setVisible(false);
/* 608 */         this.lblHeader.setVisible(false);
/*     */         
/* 610 */         this.lblCryptogram.setVisible(true);
/* 611 */         this.lblPart.setVisible(true);
/* 612 */         this.lblPart_1.setVisible(true);
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
/*     */   private void callActionListKeyblock(ActionEvent arg0)
/*     */   {
/* 648 */     String command = arg0.getActionCommand();
/* 649 */     System.out.println(this.keyblock);
/* 650 */     if (("KeyType".equalsIgnoreCase(command)) && 
/* 651 */       (this.keyblock)) {
/* 652 */       System.out.println("Entre al Metodo Header KeyBloc");
/* 653 */       if (this.comboBox_keytype.getSelectedItem().equals("KEK")) {
/* 654 */         this.textFieldHeader.setText("1KDNE000");
/* 655 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 657 */       if (this.comboBox_keytype.getSelectedItem().equals("KWP")) {
/* 658 */         this.textFieldHeader.setText("1PUNE000");
/* 659 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 661 */       if (this.comboBox_keytype.getSelectedItem().equals("PVK")) {
/* 662 */         this.textFieldHeader.setText("1VUNE000");
/* 663 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 665 */       if (this.comboBox_keytype.getSelectedItem().equals("CVK")) {
/* 666 */         this.textFieldHeader.setText("1CDNE000");
/* 667 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 669 */       if (this.comboBox_keytype.getSelectedItem().equals("CSCK")) {
/* 670 */         this.textFieldHeader.setText("BUSCAR");
/* 671 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 673 */       if (this.comboBox_keytype.getSelectedItem().equals("CAK")) {
/* 674 */         this.textFieldHeader.setText("1dDNE000");
/* 675 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 677 */       if (this.comboBox_keytype.getSelectedItem().equals("MAK")) {
/* 678 */         this.textFieldHeader.setText("1MDNE000");
/* 679 */         this.textFieldHeader.setEditable(false);
/*     */       }
/* 681 */       if (this.comboBox_keytype.getSelectedItem().equals("EMK")) {
/* 682 */         this.textFieldHeader.setText("BUSCAR");
/* 683 */         this.textFieldHeader.setEditable(false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void callActionSetKEK(ActionEvent e)
/*     */   {
/* 693 */     String commandKEK = e.getActionCommand();
/* 694 */     if ("SetKEK".equalsIgnoreCase(commandKEK)) {
/* 695 */       int dialogButton = 0;
/* 696 */       int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want use the Transport KEK in the System?", "Confirmation", dialogButton);
/* 697 */       if (dialogResult == 0) {
/* 698 */         Login openConn = new Login();
/* 699 */         Login.ImportConsole = true;
/* 700 */         openConn.setLocationRelativeTo(null);
/* 701 */         openConn.setVisible(true);
/*     */       }
/*     */       else {
/* 704 */         System.out.println("No Option");
/* 705 */         JOptionPane.showMessageDialog(null, "Operation Cancelled");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void loadKEKfromBD()
/*     */   {
/* 712 */     if (transportKEKConn != null) {
/* 713 */       System.out.println("EnTRE CONSOLA IMPORT KEK");
/* 714 */       LoadTransportKEK transportkey = null;
/* 715 */       String resultsetkek = null;
/*     */       try {
/* 717 */         resultsetkek = ExecuteSP.execLoadTransportKek(transportKEKConn);
/*     */       }
/*     */       catch (SQLException e1) {
/* 720 */         e1.printStackTrace();
/*     */       }
/*     */       
/* 723 */       String[] Split_resultsetkek = resultsetkek.split(",");
/* 724 */       String db_Kek = null;String db_CheckDigit = null;String db_Environment = null;String db_HSM = null;String db_keyblock = null;String db_lenght = null;String FormatterKeyPart1 = null;String FormatterKeyPart2 = null;
/* 725 */       String FormatterKeyPart3 = null;
/* 726 */       db_keyblock = Split_resultsetkek[0];
/*     */       
/* 728 */       FormatterKeyPart1 = Split_resultsetkek[1];
/* 729 */       FormatterKeyPart2 = Split_resultsetkek[2];
/* 730 */       FormatterKeyPart3 = Split_resultsetkek[3];
/* 731 */       db_CheckDigit = Split_resultsetkek[2];
/* 732 */       db_lenght = Split_resultsetkek[5];
/* 733 */       db_Environment = Split_resultsetkek[8];
/*     */       
/* 735 */       textFieldHeader_kek.setText("1KDNE000");
/* 736 */       formattedTextField_CryptoKeyBlock_kek.setText(FormatterKeyPart2);
/* 737 */       formattedTextField_crypto3_kek.setText(FormatterKeyPart3);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 744 */         UseParameters.getCheckDigitKek();
/* 745 */         String checkdigitkek = UseParameters.CheckDigitKek.CheckDigit();
/* 746 */         if (checkdigitkek.equals("")) {
/* 747 */           JOptionPane.showMessageDialog(null, "You need to configure a Transport KEK for Export/Import Functions");
/*     */         }
/*     */         else {
/* 750 */           System.out.println("CheckDigi from File : " + checkdigitkek);
/* 751 */           String str1 = ExecuteSP.execVerifyKekCheckDigit(checkdigitkek, transportKEKConn);
/*     */         }
/*     */       }
/*     */       catch (IOException e2) {
/* 755 */         e2.printStackTrace();
/*     */       }
/*     */       catch (SQLException e1) {
/* 758 */         e1.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void actionImportCryptogram(ActionEvent arg0)
/*     */   {
/* 766 */     String commandImport = arg0.getActionCommand();
/*     */     
/* 768 */     String CryptoImportRespondUnpack = null;
/*     */     try
/*     */     {
/* 771 */       if ("Import".equalsIgnoreCase(commandImport)) {
/* 772 */         String finalCryptotoImport = null;
/* 773 */         String KEKToUse = null;
/* 774 */         String lenghtofCryptoImport = null;
/*     */         
/* 776 */         UseParameters.getTypeHsm();
/* 777 */         if (UseParameters.TypeHSM.typeHsm().equals("Atalla"))
/*     */         {
/* 779 */           String nl = System.getProperty("line.separator");
/* 780 */           JTextArea CopyImportrtKey = new JTextArea(5, 30);
/* 781 */           int dialogButton = 0;
/* 782 */           int dialogResult = JOptionPane.showConfirmDialog(this, "The Cryptogram a Import is in AKB Format?", "Confirmation", dialogButton);
/* 783 */           if (dialogResult == 0) {
/* 784 */             System.out.println("IMPORT FORMAT AKB");
/* 785 */             finalCryptotoImport = this.textFieldHeader.getText() + "," + this.formattedTextField_CryptoKeyBlock.getText() + "," + this.formattedTextField_crypto3.getText();
/* 786 */             KEKToUse = textFieldHeader_kek.getText() + "," + formattedTextField_CryptoKeyBlock_kek.getText() + "," + formattedTextField_crypto3_kek.getText();
/*     */             
/* 788 */             String CryptoImportRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.importKey(KEKToUse, finalCryptotoImport, "AKB"));
/* 789 */             CryptoImportRespondUnpack = UnpackAtallaCryptoCommand.unpackImportKey(CryptoImportRespond);
/*     */             
/* 791 */             System.out.println("Valor de la Bandera de Error de HSM : " + failedImportHsm);
/*     */             
/* 793 */             if (failedImportHsm) {
/* 794 */               System.out.println("Valor de la Bandera de Error de HSM : " + failedImportHsm);
/* 795 */               JOptionPane.showMessageDialog(null, "Message Error : " + CryptoImportRespondUnpack, "Error Message", 0);
/* 796 */               failedImportHsm = false;
/*     */             } else {
/* 798 */               CopyImportrtKey.setText("Export Cryptogram : " + CryptoImportRespondUnpack.substring(0, 74) + 
/* 799 */                 nl + "Check Digit : " + CryptoImportRespondUnpack.substring(75, 79));
/*     */             }
/*     */           } else {
/* 802 */             System.out.println("IMPORT FORMAT NON-AKB");
/*     */             
/* 804 */             KEKToUse = textFieldHeader_kek.getText() + "," + formattedTextField_CryptoKeyBlock_kek.getText() + "," + formattedTextField_crypto3_kek.getText();
/*     */             
/* 806 */             if (this.comboBoxkeylenght.getSelectedItem().equals("Single")) {
/* 807 */               finalCryptotoImport = this.formattedTextFieldcryptogram.getText();
/* 808 */               lenghtofCryptoImport = "Single";
/*     */             }
/* 810 */             if (this.comboBoxkeylenght.getSelectedItem().equals("Double")) {
/* 811 */               finalCryptotoImport = this.formattedTextFieldcryptogram.getText() + "," + this.formattedTextField_crypto2.getText();
/* 812 */               lenghtofCryptoImport = "Double";
/*     */             }
/* 814 */             if (this.comboBoxkeylenght.getSelectedItem().equals("Triple")) {
/* 815 */               finalCryptotoImport = this.formattedTextFieldcryptogram.getText() + "," + this.formattedTextField_crypto2.getText() + "," + this.formattedTextField_crypto3.getText();
/* 816 */               lenghtofCryptoImport = "Triple";
/*     */             }
/* 818 */             String CryptoImportRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.importKey(KEKToUse, finalCryptotoImport, "Non-AKB"));
/* 819 */             CryptoImportRespondUnpack = UnpackAtallaCryptoCommand.unpackImportKey(CryptoImportRespond);
/*     */             
/* 821 */             System.out.println("Valor de la Bandera de Error de HSM : " + failedImportHsm);
/*     */             
/* 823 */             if (failedImportHsm) {
/* 824 */               System.out.println("Valor de la Bandera de Error de HSM : " + failedImportHsm);
/* 825 */               JOptionPane.showMessageDialog(null, "Message Error : " + CryptoImportRespondUnpack, "Error Message", 0);
/* 826 */               failedImportHsm = false;
/*     */             }
/*     */             else {
/* 829 */               if (lenghtofCryptoImport.equals("Single")) {
/* 830 */                 CopyImportrtKey.setText("Export Cryptogram : " + CryptoImportRespondUnpack.substring(0, 16) + 
/* 831 */                   nl + "Check Digit : " + CryptoImportRespondUnpack.substring(17, 21));
/*     */               }
/* 833 */               if (lenghtofCryptoImport.equals("Double")) {
/* 834 */                 CopyImportrtKey.setText("Export Cryptogram : " + CryptoImportRespondUnpack.substring(0, 32) + 
/* 835 */                   nl + "Check Digit : " + CryptoImportRespondUnpack.substring(33, 37));
/*     */               }
/* 837 */               if (lenghtofCryptoImport.equals("Triple")) {
/* 838 */                 CopyImportrtKey.setText("Export Cryptogram : " + CryptoImportRespondUnpack.substring(0, 48) + 
/* 839 */                   nl + "Check Digit : " + CryptoImportRespondUnpack.substring(49, 53));
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 846 */           CopyImportrtKey.setEditable(false);
/* 847 */           JOptionPane.showMessageDialog(null, new JScrollPane(CopyImportrtKey), "Export Result", 1);
/*     */           
/*     */ 
/*     */ 
/* 851 */           failedImportHsm = false;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e1)
/*     */     {
/* 857 */       e1.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void CloseFrame()
/*     */   {
/* 865 */     super.dispose();
/*     */   }
/*     */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/ImportCryptogram.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */