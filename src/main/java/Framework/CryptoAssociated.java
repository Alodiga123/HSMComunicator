/*      */ package Framework;
/*      */ import java.awt.Container;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.text.ParseException;
/*      */ import java.util.Properties;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFormattedTextField;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.border.TitledBorder;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.swing.text.MaskFormatter;

import com.alodiga.hsm.AtallaCryptoCommand;
import com.alodiga.hsm.CryptoConnection;
import com.alodiga.hsm.ThalesCryptoCommand;
import com.alodiga.hsm.UnpackAtallaCryptoCommand;
import com.alodiga.hsm.UnpackThalesCryptoCommand;
import com.alodiga.hsm.data.ExecuteSP;
import com.alodiga.hsm.operations.HexFormat;
import com.alodiga.hsm.operations.SystemDate;
import com.alodiga.hsm.operations.UseParameters;
import com.alodiga.hsm.operations.HexFormat.TextCheckDigit;
import com.alodiga.hsm.operations.HexFormat.TextKeyBlockFormat;
import com.alodiga.hsm.operations.HexFormat.TextKeyFormat;
import com.alodiga.hsm.operations.UseParameters.EnvironmentUsed;
import com.alodiga.hsm.operations.UseParameters.TypeHSM;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CryptoAssociated
/*      */   extends JFrame
/*      */ {
/*      */   String strConsultaSQL;
/*      */   Statement estSQL1;
/*      */   ResultSet rs;
/*  103 */   public static Connection cryptoconn = null;
/*      */   private JPanel contentPane;
/*  105 */   private JTextField textFieldcrypto; private JTextField textFieldHeader = null;
/*      */   private JTextField textField_Checkdigit;
/*  107 */   Object[][] cryptodata = new Object[1][9];
/*  108 */   String[] colunmNames = { "ID", "Name", "Cryptograms", "Check Digit", "Key Type", "Length", "Date Created", "Environment" };
/*      */   private JTable table;
/*  110 */   JFormattedTextField formattedTextFieldcryptogram = null;
/*  111 */   JFormattedTextField formattedTextField_crypto2; JFormattedTextField formattedTextField_CryptoKeyBlock = null;
/*  112 */   JFormattedTextField formattedTextField_crypto3 = null;
/*  113 */   JFormattedTextField formattedTextField_Checkdigit = null;
/*  114 */   private JComboBox PinBlockFormatBox = null;
/*  115 */   private JTextField TextFieldAcctNumbOffset = null;
/*  116 */   private JTextField TextFieldMinpinLenght = null;
/*  117 */   private JTextField TextFieldMaxpinLenght = null;
/*  118 */   private JComboBox ExpDateFormatBox = null;
/*  119 */   private JComboBox pinSchemeBox = null;
/*  120 */   JComboBox comboBox_keytype = null;
/*  121 */   JComboBox comboBoxkeylenght = null;
/*  122 */   JComboBox comboBoxkeylenght_kb = null;
/*  123 */   JComboBox comboBoxTypeOfCvv = null;
/*  124 */   private String[] keyLenght = new String[3];
/*  125 */   private String[] keyType = new String[8];
/*  126 */   private String[] pinScheme = new String[3];
/*  127 */   private String[] expDateFormat = new String[4];
/*  128 */   private String[] pinBlockFormat = new String[9];
/*  129 */   private String[] typeOfCvv = new String[4];
/*  130 */   private MaskFormatter Hexformat = null;
/*  131 */   private JCheckBox chckbxNewCheckBox = null; private JCheckBox checkBox = null;
/*      */   private boolean keyblock;
/*  133 */   private JLabel lblCryptogram = null;
/*  134 */   private JLabel lblPart_1 = null;
/*  135 */   private JLabel lblPart = null;
/*  136 */   private JLabel lblPart_keybloc = null;
/*  137 */   private JLabel lblPart_MAC = null;
/*  138 */   private JLabel lblHeader = null;
/*  139 */   private JLabel pinBlockFormatlabel = null;
/*  140 */   private JLabel labelOffsetAcctNumb = null;
/*  141 */   private JLabel lblExpDateFormat = null;
/*  142 */   private JLabel lblMinPin = null;
/*  143 */   private JLabel lblMaxPin = null;
/*  144 */   private JLabel lblPinLenght = null;
/*  145 */   private JLabel lblPinScheme = null;
/*  146 */   private JScrollPane scrollPanelcrypto = null;
/*  147 */   private JButton findbutton = null;
/*      */   private static int standardrow;
/*  149 */   private static int clearKeyId = 0;
/*  150 */   private static String clearKeyValue = null;
/*      */   private JButton button_save;
/*  152 */   private JButton btnNewButton_1 = null; private JButton btnNewButton = null;
/*  153 */   private String confirmation = "2";
/*  154 */   public static boolean failedResponseHsm = false; public static boolean failedExportHsm = false;
/*      */   private JMenuItem mi1;
/*      */   private JMenuItem mi2;
/*      */   private JMenuItem mi3;
/*      */   private JMenuItem mi4;
/*      */   private JMenuItem mi5;
/*  160 */   private JMenuItem mi6; private static final String[] PinBlockFormat = new String[50];
/*      */   
/*  162 */   static { PinBlockFormat[0] = "01";
/*  163 */     PinBlockFormat[1] = "02";
/*  164 */     PinBlockFormat[2] = "05";
/*  165 */     PinBlockFormat[3] = "08";
/*  166 */     PinBlockFormat[4] = "11";
/*  167 */     PinBlockFormat[4] = "13";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/*  177 */     EventQueue.invokeLater(new Runnable()
/*      */     {
/*      */       public void run() {
/*      */         try {
/*  181 */           String parameters = "parameters.properties";
/*  182 */           File parametersconfig = new File("parameters.properties");
/*      */           
/*  184 */           if (parametersconfig.exists()) {
/*  185 */             JOptionPane.showMessageDialog(null, "Parameters Correctly");
/*      */ 
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*      */ 
/*  193 */             FileWriter archiveproperties = null;
/*  194 */             archiveproperties = new FileWriter("parameters.properties");
/*      */             
/*  196 */             Properties properties = new Properties();
/*  197 */             FileOutputStream data_output = new FileOutputStream("parameters.properties");
/*      */             
/*  199 */             properties.setProperty("IP_SERVER", "NULL");
/*  200 */             properties.setProperty("PORT_SERVER", "NULL");
/*  201 */             properties.setProperty("IP_HSM", "NULL");
/*  202 */             properties.setProperty("PORT_HSM", "NULL");
/*  203 */             properties.setProperty("SQL_VERSION", "NULL");
/*  204 */             properties.setProperty("TYPE_HSM", "NULL");
/*  205 */             properties.setProperty("KEY_BLOCK", "NULL");
/*  206 */             properties.setProperty("FIRST_USE", "YES");
/*  207 */             properties.setProperty("TRANSPORT_KEK_CHECK_VALUE", "0");
/*  208 */             properties.setProperty("TEST_USE", "NO");
/*  209 */             properties.setProperty("IS_DB", "OFF");
/*  210 */             properties.setProperty("HEADER_MESSAGE", "NULL");
/*  211 */             properties.setProperty("THALES_MSG_HEADER", "NULL");
/*  212 */             properties.store(data_output, "/*-----------------Parameters File-------------------*/");
/*  213 */             data_output.close();
/*      */           }
/*      */           
/*  216 */           CryptoAssociated frame = new CryptoAssociated();
/*  217 */           frame.setVisible(true);
/*      */         } catch (Exception e) {
/*  219 */           e.printStackTrace();
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CryptoAssociated()
/*      */     throws IOException
/*      */   {
/*  230 */     setResizable(false);
/*      */     
/*  232 */     setTitle("Cryptograms Associated");
/*  233 */     setLocationRelativeTo(null);
/*  234 */     setDefaultCloseOperation(0);
/*  235 */     setBounds(100, 100, 1126, 781);
/*      */     
/*      */ 
/*      */ 
/*  239 */     JMenuBar menuBar = new JMenuBar();
/*  240 */     setJMenuBar(menuBar);
/*      */     
/*  242 */     JMenu fileMenu = new JMenu("File");
/*  243 */     menuBar.add(fileMenu);
/*  244 */     this.mi1 = new JMenuItem("Data Base Login");
/*  245 */     this.mi1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  247 */         Login userlogin = new Login();
/*  248 */         userlogin.setLocationRelativeTo(null);
/*  249 */         userlogin.setModal(true);
/*  250 */         userlogin.setVisible(true);
/*      */       }
/*      */       
/*  253 */     });
/*  254 */     fileMenu.add(this.mi1);
/*      */     
/*      */ 
/*  257 */     JMenu firmMenu = new JMenu("Firmware");
/*  258 */     menuBar.add(firmMenu);
/*  259 */     this.mi2 = new JMenuItem("Get Firmware..");
/*  260 */     this.mi2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  262 */         CryptoAssociated.this.callactionPerformedFirmware(e);
/*      */       }
/*  264 */     });
/*  265 */     firmMenu.add(this.mi2);
/*      */     
/*  267 */     this.mi5 = new JMenuItem("Echo Message");
/*  268 */     this.mi5.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  270 */         CryptoAssociated.this.callactionPerformedEcho(e);
/*      */       }
/*  272 */     });
/*  273 */     firmMenu.add(this.mi5);
/*      */     
/*      */ 
/*      */ 
/*  277 */     JMenu optionMenu = new JMenu("Options");
/*  278 */     menuBar.add(optionMenu);
/*  279 */     this.mi3 = new JMenuItem("Preferences");
/*  280 */     this.mi3.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/*  283 */         Configuration open = new Configuration();
/*  284 */         open.setLocationRelativeTo(null);
/*  285 */         open.setModal(true);
/*  286 */         open.setVisible(true);
/*      */       }
/*      */       
/*      */ 
/*  290 */     });
/*  291 */     optionMenu.add(this.mi3);
/*      */     
/*  293 */     this.mi4 = new JMenuItem("DB Scripts Instllation");
/*  294 */     this.mi4.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/*  297 */         CredentialsInstallDB install = new CredentialsInstallDB();
/*  298 */         install.setLocationRelativeTo(null);
/*  299 */         install.setVisible(true);
/*      */       }
/*      */       
/*  302 */     });
/*  303 */     optionMenu.add(this.mi4);
/*      */     
/*      */ 
/*  306 */     this.contentPane = new JPanel();
/*  307 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  308 */     setContentPane(this.contentPane);
/*  309 */     this.contentPane.setLayout(null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  317 */     HexFormat.getHexSingleFormat();
/*  318 */     HexFormat.getHexSingleFormatCheckDigit();
/*  319 */     HexFormat.getHexKeyBlockFormat();
/*      */     
/*      */     try
/*      */     {
/*  323 */       this.Hexformat = new MaskFormatter("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
/*      */     }
/*      */     catch (ParseException e1) {
/*  326 */       e1.printStackTrace();
/*      */     }
/*  328 */     this.Hexformat.setValidCharacters("0123456789abcdefABCDEF");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  352 */     JPanel panel_crypto = new JPanel();
/*  353 */     panel_crypto.setBounds(27, 34, 302, 367);
/*  354 */     panel_crypto.setLayout(null);
/*  355 */     panel_crypto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cryptogram ...", 4, 2, null, null));
/*  356 */     this.contentPane.add(panel_crypto);
/*      */     
/*  358 */     this.formattedTextFieldcryptogram = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/*  359 */     this.formattedTextFieldcryptogram.setBounds(29, 37, 136, 29);
/*  360 */     panel_crypto.add(this.formattedTextFieldcryptogram);
/*  361 */     this.formattedTextFieldcryptogram.setVisible(true);
/*      */     
/*  363 */     this.textFieldHeader = new JTextField();
/*  364 */     this.textFieldHeader.setBounds(29, 37, 136, 29);
/*  365 */     panel_crypto.add(this.textFieldHeader);
/*  366 */     this.textFieldHeader.setVisible(false);
/*  367 */     this.textFieldHeader.setEditable(false);
/*      */     
/*  369 */     this.formattedTextField_crypto2 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/*  370 */     this.formattedTextField_crypto2.setEditable(false);
/*  371 */     this.formattedTextField_crypto2.setBounds(29, 90, 136, 29);
/*  372 */     panel_crypto.add(this.formattedTextField_crypto2);
/*      */     
/*      */ 
/*  375 */     this.formattedTextField_CryptoKeyBlock = new JFormattedTextField(HexFormat.TextKeyBlockFormat.formatKeyBlock());
/*  376 */     this.formattedTextField_CryptoKeyBlock.setEditable(false);
/*  377 */     this.formattedTextField_CryptoKeyBlock.setBounds(29, 90, 136, 29);
/*  378 */     panel_crypto.add(this.formattedTextField_CryptoKeyBlock);
/*      */     
/*      */ 
/*  381 */     this.formattedTextField_crypto3 = new JFormattedTextField(HexFormat.TextKeyFormat.formattrype());
/*  382 */     this.formattedTextField_crypto3.setEditable(false);
/*  383 */     this.formattedTextField_crypto3.setBounds(29, 146, 136, 29);
/*  384 */     panel_crypto.add(this.formattedTextField_crypto3);
/*      */     
/*  386 */     this.lblPart = new JLabel("Part 2");
/*  387 */     this.lblPart.setBounds(192, 96, 97, 16);
/*  388 */     panel_crypto.add(this.lblPart);
/*      */     
/*  390 */     this.lblPart_1 = new JLabel("Part 3");
/*  391 */     this.lblPart_1.setBounds(192, 152, 97, 16);
/*  392 */     panel_crypto.add(this.lblPart_1);
/*      */     
/*  394 */     this.lblCryptogram = new JLabel("Part 1");
/*  395 */     this.lblCryptogram.setBounds(192, 43, 97, 16);
/*  396 */     panel_crypto.add(this.lblCryptogram);
/*      */     
/*      */ 
/*  399 */     this.keyLenght[0] = "Single";
/*  400 */     this.keyLenght[1] = "Double";
/*  401 */     this.keyLenght[2] = "Triple";
/*      */     
/*  403 */     this.keyType[0] = "KEK";
/*  404 */     this.keyType[1] = "KWP";
/*  405 */     this.keyType[2] = "PVK";
/*  406 */     this.keyType[3] = "CVK";
/*      */     
/*  408 */     this.keyType[4] = "CAK";
/*  409 */     this.keyType[5] = "MAK";
/*      */     
/*      */ 
/*  412 */     this.pinScheme[0] = "";
/*  413 */     this.pinScheme[1] = "IBM";
/*  414 */     this.pinScheme[2] = "VISA";
/*      */     
/*  416 */     this.expDateFormat[0] = "";
/*  417 */     this.expDateFormat[1] = "YYMM";
/*  418 */     this.expDateFormat[2] = "MMYY";
/*      */     
/*  420 */     this.pinBlockFormat[0] = "";
/*  421 */     this.pinBlockFormat[1] = "ISO-0";
/*  422 */     this.pinBlockFormat[2] = "ISO-1";
/*  423 */     this.pinBlockFormat[3] = "VISA-4";
/*  424 */     this.pinBlockFormat[4] = "IBM3624";
/*  425 */     this.pinBlockFormat[5] = "DOCUTEL-1";
/*  426 */     this.pinBlockFormat[6] = "AS2805.3 Format8";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  431 */     this.typeOfCvv[0] = "";
/*  432 */     this.typeOfCvv[1] = "CVV";
/*  433 */     this.typeOfCvv[2] = "CVV2";
/*  434 */     this.typeOfCvv[3] = "iCVV";
/*      */     
/*      */ 
/*      */ 
/*  438 */     this.scrollPanelcrypto = new JScrollPane();
/*  439 */     this.scrollPanelcrypto.setBounds(27, 426, 1071, 290);
/*  440 */     this.scrollPanelcrypto.setOpaque(false);
/*  441 */     this.contentPane.add(this.scrollPanelcrypto);
/*      */     
/*      */ 
/*  444 */     this.table = new JTable();
/*  445 */     this.table.setColumnSelectionAllowed(true);
/*  446 */     this.table.setEnabled(false);
/*  447 */     this.table.setOpaque(false);
/*  448 */     this.scrollPanelcrypto.setViewportView(this.table);
/*      */     
/*  450 */     this.table.setModel(new DefaultTableModel(
/*  451 */       this.cryptodata, 
/*  452 */       this.colunmNames));
/*      */     
/*  454 */     this.table.setSelectionMode(0);
/*  455 */     this.table.setAutoscrolls(true);
/*  456 */     getContentPane().add(this.scrollPanelcrypto);
/*      */     
/*  458 */     JPanel panel_detail = new JPanel();
/*  459 */     panel_detail.setBounds(353, 34, 493, 367);
/*  460 */     this.contentPane.add(panel_detail);
/*  461 */     panel_detail.setLayout(null);
/*      */     
/*  463 */     JLabel lblKeyType = new JLabel("Key Type");
/*  464 */     lblKeyType.setBounds(143, 98, 97, 16);
/*  465 */     panel_detail.add(lblKeyType);
/*  466 */     this.comboBox_keytype = new JComboBox(this.keyType);
/*  467 */     this.comboBox_keytype.setActionCommand("KeyType");
/*  468 *
/*  473 */     this.comboBox_keytype.setBounds(12, 95, 119, 22);
/*  474 */     panel_detail.add(this.comboBox_keytype);
/*      */     
/*  476 */     this.comboBoxkeylenght = new JComboBox(this.keyLenght);
/*  477 */     this.comboBoxkeylenght.setBounds(272, 33, 119, 22);
/*  478 */     this.comboBoxkeylenght.setActionCommand("KeyLenght");
/*  479 */     this.comboBoxkeylenght.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  481 */         CryptoAssociated.this.callActionPerformedCheck(e);
/*      */       }
/*  483 */     });
/*  484 */     panel_detail.add(this.comboBoxkeylenght);
/*      */     
/*  486 */     this.comboBoxkeylenght_kb = new JComboBox(this.keyLenght);
/*  487 */     this.comboBoxkeylenght_kb.setBounds(272, 33, 119, 22);
/*  488 */     panel_detail.add(this.comboBoxkeylenght_kb);
/*  489 */     this.comboBoxkeylenght_kb.setEditable(false);
/*  490 */     this.comboBoxkeylenght_kb.setVisible(false);
/*      */     
/*  492 */     JLabel label_3 = new JLabel("Length");
/*  493 */     label_3.setBounds(403, 36, 97, 16);
/*  494 */     panel_detail.add(label_3);
/*      */     
/*  496 */     this.formattedTextField_Checkdigit = new JFormattedTextField(HexFormat.TextCheckDigit.formattrypeCd());
/*  497 */     this.formattedTextField_Checkdigit.setBounds(12, 146, 119, 29);
/*  498 */     panel_detail.add(this.formattedTextField_Checkdigit);
/*  499 */     this.formattedTextField_Checkdigit.setColumns(10);
/*      */     
/*  501 */     JLabel label_2 = new JLabel("Check Digit");
/*  502 */     label_2.setBounds(143, 138, 97, 16);
/*  503 */     panel_detail.add(label_2);
/*  504 */     panel_detail.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Crypto Properties ...", 4, 2, null, null));
/*      */     
/*  506 */     JLabel label_1 = new JLabel("Key Name");
/*  507 */     label_1.setBounds(143, 36, 97, 16);
/*  508 */     panel_detail.add(label_1);
/*      */     
/*  510 */     this.textFieldcrypto = new JTextField();
/*  511 */     this.textFieldcrypto.setBounds(12, 33, 119, 29);
/*  512 */     panel_detail.add(this.textFieldcrypto);
/*  513 */     this.textFieldcrypto.setColumns(10);
/*      */     
/*  515 */     this.keyblock = false;
/*  516 */     this.chckbxNewCheckBox = new JCheckBox("Key Block");
/*  517 */     this.chckbxNewCheckBox.setActionCommand("KeyBlockFlag");
/*  518 */     this.chckbxNewCheckBox.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  520 */         CryptoAssociated.this.keyblock = CryptoAssociated.this.chckbxNewCheckBox.isSelected();
/*  521 */         CryptoAssociated.this.callActionPerformedCheck(e);
/*  522 */         System.out.println("Despues del Metodo callActionPerformedCheck :" + CryptoAssociated.this.chckbxNewCheckBox.isSelected());
/*  523 */         System.out.println("Despues del Metodo callActionPerformedCheck :" + CryptoAssociated.this.comboBoxkeylenght_kb.getSelectedItem());
/*  524 */         System.out.println("Despues del Metodo callActionPerformedCheck 2 :" + CryptoAssociated.this.comboBoxkeylenght.getSelectedItem());
/*      */       }
/*  526 */     });
/*  527 */     this.chckbxNewCheckBox.setBounds(272, 75, 113, 25);
/*  528 */     panel_detail.add(this.chckbxNewCheckBox);
/*      */     
/*  530 */     this.checkBox = new JCheckBox("Test/Certificate");
/*  531 */     this.checkBox.setBounds(272, 117, 129, 25);
/*  532 */     UseParameters.getEnvriomentUsed();
/*  533 */     if (UseParameters.EnvironmentUsed.EnvironmentUsed().equals("YES")) {
/*  534 */       this.checkBox.setSelected(true);
/*  535 */       this.checkBox.setEnabled(false);
/*      */     }
/*  537 */     panel_detail.add(this.checkBox);
/*      */     
/*  539 */     JButton btnGenerate = new JButton("Verify");
/*  540 */     btnGenerate.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent arg0) {
/*  542 */         String insert_Lenght = null;String insert_keypart1 = null;String insert_keypart2 = null;String insert_keyblock = null;
/*  543 */         String insert_keypart3 = null;String insert_finalkey = null;String insert_keytype = null;
/*      */         try
/*      */         {
/*  546 */           String CheckDigitRespond = null;
/*  547 */           String CheckDigitGenerated = null;
/*  548 */           insert_keytype = (String)CryptoAssociated.this.comboBox_keytype.getSelectedItem();
/*  549 */           if (CryptoAssociated.this.keyblock) {
/*  550 */             insert_keyblock = "YES";
/*  551 */             insert_Lenght = (String)CryptoAssociated.this.comboBoxkeylenght_kb.getSelectedItem();
/*  552 */             insert_keypart1 = CryptoAssociated.this.textFieldHeader.getText();
/*  553 */             insert_keypart2 = CryptoAssociated.this.formattedTextField_CryptoKeyBlock.getText();
/*  554 */             insert_keypart3 = CryptoAssociated.this.formattedTextField_crypto3.getText();
/*  555 */             insert_finalkey = insert_keypart1 + "," + insert_keypart2 + "," + insert_keypart3;
/*      */             
/*  557 */             if (insert_keypart2.isEmpty()) {
/*  558 */               JOptionPane.showMessageDialog(null, "Please Insert a Valid Value for Key Block");
/*      */             }
/*  560 */             else if (insert_keypart3.isEmpty()) {
/*  561 */               JOptionPane.showMessageDialog(null, "Please Insert a Valid Value for MAC");
/*      */             }
/*      */             else {
/*  564 */               UseParameters.getTypeHsm();
/*  565 */               if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/*  566 */                 CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/*  567 */                 CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  568 */                 CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */               }
/*  570 */               if (UseParameters.TypeHSM.typeHsm().equals("Thales")) {
/*  571 */                 CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght, insert_keytype));
/*  572 */                 CheckDigitGenerated = UnpackThalesCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  573 */                 CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */               }
/*      */               
/*  576 */               System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedResponseHsm);
/*      */               
/*  578 */               if (CryptoAssociated.failedResponseHsm) {
/*  579 */                 System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedResponseHsm);
/*  580 */                 JOptionPane.showMessageDialog(null, "Message Error : " + CheckDigitGenerated, "Error Message", 0);
/*  581 */                 CryptoAssociated.failedResponseHsm = false;
/*      */               } else {
/*  583 */                 CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*  584 */                 CryptoAssociated.failedResponseHsm = false;
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  590 */           if (!CryptoAssociated.this.keyblock) {
/*  591 */             insert_keyblock = "NO";
/*  592 */             insert_Lenght = (String)CryptoAssociated.this.comboBoxkeylenght.getSelectedItem();
/*  593 */             if (insert_Lenght.equals("Single")) {
/*  594 */               insert_keypart1 = CryptoAssociated.this.formattedTextFieldcryptogram.getText();
/*  595 */               insert_finalkey = insert_keypart1;
/*      */               
/*  597 */               if (insert_finalkey.isEmpty()) {
/*  598 */                 JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*      */               } else {
/*  600 */                 UseParameters.getTypeHsm();
/*  601 */                 if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/*  602 */                   CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/*  603 */                   CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  604 */                   CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */                 }
/*  606 */                 if (UseParameters.TypeHSM.typeHsm().equals("Thales")) {
/*  607 */                   CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght, insert_keytype));
/*  608 */                   CheckDigitGenerated = UnpackThalesCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  609 */                   CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */                 }
/*      */               }
/*      */             }
/*  613 */             if (insert_Lenght.equals("Double")) {
/*  614 */               insert_keypart1 = CryptoAssociated.this.formattedTextFieldcryptogram.getText();
/*  615 */               insert_keypart2 = CryptoAssociated.this.formattedTextField_crypto2.getText();
/*  616 */               insert_finalkey = insert_keypart1 + insert_keypart2;
/*      */               
/*  618 */               if (insert_finalkey.isEmpty()) {
/*  619 */                 JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*      */               } else {
/*  621 */                 UseParameters.getTypeHsm();
/*  622 */                 if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/*  623 */                   CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/*  624 */                   CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  625 */                   CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */                 }
/*  627 */                 if (UseParameters.TypeHSM.typeHsm().equals("Thales")) {
/*  628 */                   CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght, insert_keytype));
/*  629 */                   CheckDigitGenerated = UnpackThalesCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  630 */                   CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */                 }
/*      */               }
/*      */             }
/*  634 */             if (insert_Lenght.equals("Triple")) {
/*  635 */               insert_keypart1 = CryptoAssociated.this.formattedTextFieldcryptogram.getText();
/*  636 */               insert_keypart2 = CryptoAssociated.this.formattedTextField_crypto2.getText();
/*  637 */               insert_keypart3 = CryptoAssociated.this.formattedTextField_crypto3.getText();
/*  638 */               insert_finalkey = insert_keypart1 + insert_keypart2 + insert_keypart3;
/*      */               
/*  640 */               if (insert_finalkey.isEmpty()) {
/*  641 */                 JOptionPane.showMessageDialog(null, "Please Inser a Valid Value for Key Block");
/*      */               } else {
/*  643 */                 UseParameters.getTypeHsm();
/*  644 */                 if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/*  645 */                   CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/*  646 */                   CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  647 */                   CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */                 }
/*  649 */                 if (UseParameters.TypeHSM.typeHsm().equals("Thales")) {
/*  650 */                   CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght, insert_keytype));
/*  651 */                   CheckDigitGenerated = UnpackThalesCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*  652 */                   CryptoAssociated.this.formattedTextField_Checkdigit.setText(CheckDigitGenerated);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Exception e) {
/*  659 */           e.printStackTrace();
/*      */         }
/*      */         
/*      */       }
/*  663 */     });
/*  664 */     btnGenerate.setEnabled(true);
/*  665 */     btnGenerate.setActionCommand("Save");
/*  666 */     btnGenerate.setBounds(143, 161, 71, 16);
/*  667 */     panel_detail.add(btnGenerate);
/*      */     
/*      */ 
/*  670 */     this.PinBlockFormatBox = new JComboBox(this.pinBlockFormat);
/*  671 */     this.PinBlockFormatBox.setActionCommand("PinBlockFormat");
/*  672 */     this.PinBlockFormatBox.setBounds(12, 206, 119, 22);
/*  673 */     panel_detail.add(this.PinBlockFormatBox);
/*      */     
/*  675 */     this.pinBlockFormatlabel = new JLabel("PIN Block Format");
/*  676 */     this.pinBlockFormatlabel.setBounds(143, 210, 97, 16);
/*  677 */     panel_detail.add(this.pinBlockFormatlabel);
/*      */     
/*  679 */     this.TextFieldAcctNumbOffset = new JTextField();
/*  680 */     this.TextFieldAcctNumbOffset.setColumns(10);
/*  681 */     this.TextFieldAcctNumbOffset.setBounds(272, 226, 36, 29);
/*  682 */     panel_detail.add(this.TextFieldAcctNumbOffset);
/*      */     
/*  684 */     this.labelOffsetAcctNumb = new JLabel("Account Number Offset");
/*  685 */     this.labelOffsetAcctNumb.setBounds(318, 232, 129, 16);
/*  686 */     panel_detail.add(this.labelOffsetAcctNumb);
/*      */     
/*  688 */     this.lblExpDateFormat = new JLabel("Exp. Date Format");
/*  689 */     this.lblExpDateFormat.setBounds(399, 266, 101, 16);
/*  690 */     panel_detail.add(this.lblExpDateFormat);
/*      */     
/*  692 */     this.TextFieldMinpinLenght = new JTextField();
/*  693 */     this.TextFieldMinpinLenght.setColumns(10);
/*  694 */     this.TextFieldMinpinLenght.setBounds(272, 188, 36, 29);
/*  695 */     panel_detail.add(this.TextFieldMinpinLenght);
/*      */     
/*  697 */     this.TextFieldMaxpinLenght = new JTextField();
/*  698 */     this.TextFieldMaxpinLenght.setColumns(10);
/*  699 */     this.TextFieldMaxpinLenght.setBounds(355, 188, 36, 29);
/*  700 */     panel_detail.add(this.TextFieldMaxpinLenght);
/*      */     
/*  702 */     this.lblMinPin = new JLabel("Min.");
/*  703 */     this.lblMinPin.setBounds(317, 193, 29, 16);
/*  704 */     panel_detail.add(this.lblMinPin);
/*      */     
/*  706 */     this.lblMaxPin = new JLabel("Max.");
/*  707 */     this.lblMaxPin.setBounds(403, 194, 29, 16);
/*  708 */     panel_detail.add(this.lblMaxPin);
/*      */     
/*  710 */     this.lblPinLenght = new JLabel("PIN Lenght");
/*  711 */     this.lblPinLenght.setBounds(272, 161, 119, 16);
/*  712 */     panel_detail.add(this.lblPinLenght);
/*      */     
/*  714 */     this.ExpDateFormatBox = new JComboBox(this.expDateFormat);
/*  715 */     this.ExpDateFormatBox.setActionCommand("ExpDateFormat");
/*  716 */     this.ExpDateFormatBox.setBounds(272, 263, 119, 22);
/*  717 */     panel_detail.add(this.ExpDateFormatBox);
/*      */     
/*  719 */     this.lblPinScheme = new JLabel("PIN Scheme");
/*  720 */     this.lblPinScheme.setBounds(143, 267, 97, 16);
/*  721 */     panel_detail.add(this.lblPinScheme);
/*      */     
/*  723 */     this.pinSchemeBox = new JComboBox(this.pinScheme);
/*  724 */     this.pinSchemeBox.setActionCommand("PinScheme");
/*  725 */     this.pinSchemeBox.setBounds(12, 264, 119, 22);
/*  726 */     panel_detail.add(this.pinSchemeBox);
/*      */     
/*  728 */     this.lblPart_keybloc = new JLabel("Key Block");
/*  729 */     this.lblPart_keybloc.setBounds(192, 96, 97, 16);
/*  730 */     panel_crypto.add(this.lblPart_keybloc);
/*  731 */     this.lblPart_keybloc.setVisible(false);
/*      */     
/*  733 */     this.lblPart_MAC = new JLabel("MAC");
/*  734 */     this.lblPart_MAC.setBounds(192, 152, 97, 16);
/*  735 */     panel_crypto.add(this.lblPart_MAC);
/*  736 */     this.lblPart_MAC.setVisible(false);
/*      */     
/*  738 */     this.lblHeader = new JLabel("Header");
/*  739 */     this.lblHeader.setBounds(192, 43, 97, 16);
/*  740 */     panel_crypto.add(this.lblHeader);
/*      */     
/*      */ 
/*  743 */     this.comboBoxTypeOfCvv = new JComboBox(this.typeOfCvv);
/*  744 */     this.comboBoxTypeOfCvv.setEnabled(false);
/*  745 */     this.comboBoxTypeOfCvv.setEditable(false);
/*  746 */     this.comboBoxTypeOfCvv.setActionCommand("TypeOfCvv");
/*  747 */     this.comboBoxTypeOfCvv.setBounds(12, 319, 119, 22);
/*  748 */     panel_detail.add(this.comboBoxTypeOfCvv);
/*      */     
/*  750 */     JLabel lblTypeOfCvv = new JLabel("Type of CVV");
/*  751 */     lblTypeOfCvv.setBounds(143, 323, 97, 16);
/*  752 */     panel_detail.add(lblTypeOfCvv);
/*      */     
/*      */ 
/*  755 */     this.PinBlockFormatBox.setEnabled(false);
/*  756 */     this.PinBlockFormatBox.setEditable(false);
/*      */     
/*  758 */     this.TextFieldAcctNumbOffset.setEnabled(false);
/*  759 */     this.TextFieldAcctNumbOffset.setEditable(false);
/*      */     
/*  761 */     this.ExpDateFormatBox.setEnabled(false);
/*  762 */     this.ExpDateFormatBox.setEditable(false);
/*      */     
/*  764 */     this.TextFieldMinpinLenght.setEnabled(false);
/*  765 */     this.TextFieldMinpinLenght.setEditable(false);
/*      */     
/*  767 */     this.TextFieldMaxpinLenght.setEditable(false);
/*  768 */     this.TextFieldMaxpinLenght.setEnabled(false);
/*      */     
/*  770 */     this.pinSchemeBox.setEnabled(false);
/*  771 */     this.pinSchemeBox.setEditable(false);
/*      */     
/*      */ 
/*      */ 
/*  775 */     JButton btnGenerate_1 = new JButton("Generate Crypto");
/*  776 */     btnGenerate_1.setActionCommand("Generate");
/*  777 */     btnGenerate_1.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent arg0) {
/*  780 */         String commandGenerate = arg0.getActionCommand();
/*      */         
/*      */ 
/*      */ 
/*  784 */         String cryptoId = (String)CryptoAssociated.this.cryptodata[CryptoAssociated.standardrow][0];
/*      */         
/*      */ 
/*      */         try
/*      */         {
/*  789 */           String ClearKeyForUse = null;String ClearKeyDB = null;
/*      */           
/*  791 */           String lenghtResult = null;
/*  792 */           String resultkey = null;String resultCheckDigit = null;
/*  793 */           String insert_keytype = null;String insert_Lenght = null;
/*      */           
/*  795 */           insert_keytype = (String)CryptoAssociated.this.comboBox_keytype.getSelectedItem();
/*  796 */           insert_Lenght = (String)CryptoAssociated.this.comboBoxkeylenght.getSelectedItem();
/*      */           
/*      */ 
/*  799 */           UseParameters.getTypeHsm();
/*  800 */           if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/*  801 */             JOptionPane.showMessageDialog(null, "Function Not Supported for Atalla HSM", "Error Message", 1);
/*      */           } else {
/*  803 */             String cryptoGenerated = null;String cryptoGeneratedRespondUnpack = null;
/*  804 */             cryptoGenerated = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateKey(insert_Lenght, insert_keytype));
/*  805 */             cryptoGeneratedRespondUnpack = UnpackThalesCryptoCommand.unpackGenerateKey(cryptoGenerated);
/*      */             
/*      */ 
/*  808 */             lenghtResult = cryptoGeneratedRespondUnpack.substring(0, 1);
/*      */             
/*  810 */             System.out.println("Lenght Generated Result:" + lenghtResult);
/*      */             
/*  812 */             if (lenghtResult.equals("Z")) {
/*  813 */               System.out.println("Entre Z");
/*  814 */               resultkey = cryptoGeneratedRespondUnpack.substring(1, 17);
/*  815 */               resultCheckDigit = cryptoGeneratedRespondUnpack.substring(17, 21);
/*      */               
/*  817 */               CryptoAssociated.this.formattedTextFieldcryptogram.setText(resultkey);
/*  818 */               CryptoAssociated.this.formattedTextField_Checkdigit.setText(resultCheckDigit);
/*      */             }
/*  820 */             if (lenghtResult.equals("U")) {
/*  821 */               System.out.println("Entre U");
/*  822 */               resultkey = cryptoGeneratedRespondUnpack.substring(1, 33);
/*  823 */               resultCheckDigit = cryptoGeneratedRespondUnpack.substring(33, 37);
/*      */               
/*  825 */               CryptoAssociated.this.formattedTextFieldcryptogram.setText(resultkey.substring(0, 16));
/*  826 */               CryptoAssociated.this.formattedTextField_crypto2.setText(resultkey.substring(16, 32));
/*      */               
/*  828 */               CryptoAssociated.this.formattedTextField_Checkdigit.setText(resultCheckDigit);
/*      */             }
/*  830 */             if (lenghtResult.equals("T")) {
/*  831 */               System.out.println("Entre T");
/*  832 */               resultkey = cryptoGeneratedRespondUnpack.substring(1, 49);
/*  833 */               resultCheckDigit = cryptoGeneratedRespondUnpack.substring(49, 53);
/*      */               
/*  835 */               CryptoAssociated.this.formattedTextFieldcryptogram.setText(resultkey.substring(0, 16));
/*  836 */               CryptoAssociated.this.formattedTextField_crypto2.setText(resultkey.substring(16, 32));
/*  837 */               CryptoAssociated.this.formattedTextField_crypto3.setText(resultkey.substring(32, 48));
/*      */               
/*  839 */               CryptoAssociated.this.formattedTextField_Checkdigit.setText(resultCheckDigit);
/*      */             }
/*      */             
/*  842 */             System.out.println("Cryptogram Generated:" + resultkey);
/*  843 */             System.out.println("Check Digit Generated:" + resultCheckDigit);
/*      */             
/*  845 */             JOptionPane.showMessageDialog(null, "Cryptogram Generated:" + resultkey + ".\n" + "Check Digit" + resultCheckDigit);
/*      */             
/*      */ 
/*      */ 
/*  849 */             System.out.println("Comando Generacion Cryptograma Prueba: " + cryptoGeneratedRespondUnpack);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (Exception e)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  872 */           e.printStackTrace();
/*      */         }
/*      */       }
/*  875 */     });
/*  876 */     btnGenerate_1.setEnabled(true);
/*  877 */     btnGenerate_1.setActionCommand("Save");
/*  878 */     btnGenerate_1.setBounds(39, 198, 126, 25);
/*  879 */     panel_crypto.add(btnGenerate_1);
/*  880 */     this.lblHeader.setVisible(false);
/*      */     
/*      */ 
/*  883 */     JPanel panelOperations = new JPanel();
/*  884 */     panelOperations.setBounds(870, 34, 228, 120);
/*  885 */     this.contentPane.add(panelOperations);
/*  886 */     panelOperations.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operations ...", 4, 2, null, null));
/*  887 */     panelOperations.setLayout(null);
/*      */     
/*      */ 
/*  890 */     this.findbutton = new JButton("Find");
/*  891 */     this.findbutton.setBounds(12, 24, 97, 25);
/*  892 */     panelOperations.add(this.findbutton);
/*  893 */     this.findbutton.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  895 */         CryptoAssociated.this.callactionPerformedFind(e);
/*      */       }
/*  897 */     });
/*  898 */     this.findbutton.setActionCommand("Find");
/*      */     
/*  900 */     this.button_save = new JButton("Save");
/*  901 */     this.button_save.setActionCommand("Save");
/*  902 */     this.button_save.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent arg0) {
/*      */         try {
/*  905 */           CryptoAssociated.this.callactionPerformedSave(arg0);
/*      */         }
/*      */         catch (IOException e) {
/*  908 */           e.printStackTrace();
/*      */         }
/*      */       }
/*  911 */     });
/*  912 */     this.button_save.setBounds(119, 24, 97, 25);
/*  913 */     panelOperations.add(this.button_save);
/*  914 */     this.button_save.setEnabled(true);
/*      */     
/*  916 */     JButton btnExit = new JButton("Exit");
/*  917 */     btnExit.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  919 */         CryptoAssociated.this.CloseFrame();
/*      */       }
/*  921 */     });
/*  922 */     btnExit.setBounds(119, 67, 97, 25);
/*  923 */     panelOperations.add(btnExit);
/*      */     
/*  925 */     this.btnNewButton_1 = new JButton("Delete");
/*  926 */     this.btnNewButton_1.setActionCommand("Delete");
/*  927 */     this.btnNewButton_1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent arg0) {
/*  929 */         CryptoAssociated.this.callActionPerformedDelete(arg0);
/*      */       }
/*  931 */     });
/*  932 */     this.btnNewButton_1.setBounds(12, 67, 97, 25);
/*  933 */     panelOperations.add(this.btnNewButton_1);
/*      */     
/*  935 */     JPanel panelExport = new JPanel();
/*  936 */     panelExport.setBounds(870, 167, 228, 117);
/*  937 */     this.contentPane.add(panelExport);
/*  938 */     panelExport.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Export/Import ...", 4, 2, null, null));
/*  939 */     panelExport.setLayout(null);
/*      */     
/*  941 */     JButton exportButton = new JButton("Export");
/*  942 */     exportButton.setBounds(67, 63, 97, 25);
/*  943 */     panelExport.add(exportButton);
/*  944 */     exportButton.setActionCommand("Export");
/*  945 */     exportButton.setEnabled(false);
/*  946 */     exportButton.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/*  949 */         String commandExport = e.getActionCommand();
/*      */         
/*  951 */         String CryptoExportRespondUnpack = null;
/*      */         
/*  953 */         String exportCrypto = (String)CryptoAssociated.this.cryptodata[CryptoAssociated.standardrow][2];
/*  954 */         String exportcheckdigit = (String)CryptoAssociated.this.cryptodata[CryptoAssociated.standardrow][3];
/*  955 */         String exportLenght = (String)CryptoAssociated.this.cryptodata[CryptoAssociated.standardrow][5];
/*      */         try
/*      */         {
/*  958 */           if ("Export".equalsIgnoreCase(commandExport))
/*      */           {
/*  960 */             System.out.println("KEY TO EXPORT : " + exportCrypto);
/*      */             
/*      */ 
/*  963 */             String kekeForExport = null;
/*  964 */             kekeForExport = ExecuteSP.execLoadCryptoKEK(CryptoAssociated.cryptoconn);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  969 */             UseParameters.getTypeHsm();
/*  970 */             if (UseParameters.TypeHSM.typeHsm().equals("Atalla")) {
/*  971 */               String option = "Swing";
/*  972 */               String[] options = { "AKB", "Non-AKB" };
/*      */               
/*  974 */               int choice = JOptionPane.showOptionDialog(
/*  975 */                 null, 
/*  976 */                 "Please choice Export Format", 
/*  977 */                 "Options to Export", 
/*  978 */                 1, 
/*  979 */                 2, 
/*  980 */                 null, 
/*  981 */                 options, 
/*  982 */                 options[1]);
/*      */               
/*  984 */               option = options[choice];
/*  985 */               String nl = System.getProperty("line.separator");
/*  986 */               JTextArea CopyExportKey = new JTextArea(5, 30);
/*      */               
/*  988 */               if (option.equals("AKB")) {
/*  989 */                 System.out.println("EXPORT FORMAT AKB");
/*  990 */                 String CryptoExportRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.exportKey(kekeForExport, exportCrypto, "AKB"));
/*  991 */                 CryptoExportRespondUnpack = UnpackAtallaCryptoCommand.unpackExportKey(CryptoExportRespond);
/*      */                 
/*  993 */                 System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedExportHsm);
/*      */                 
/*  995 */                 if (CryptoAssociated.failedExportHsm) {
/*  996 */                   System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedExportHsm);
/*  997 */                   JOptionPane.showMessageDialog(null, "Message Error : " + CryptoExportRespondUnpack, "Error Message", 0);
/*  998 */                   CryptoAssociated.failedResponseHsm = false;
/*      */                 } else {
/* 1000 */                   CopyExportKey.setText("Export Cryptogram : " + CryptoExportRespondUnpack.substring(0, 74) + 
/* 1001 */                     nl + "Check Digit : " + CryptoExportRespondUnpack.substring(75, 79));
/*      */                 }
/*      */               }
/* 1004 */               if (option.equals("Non-AKB")) {
/* 1005 */                 System.out.println("EXPORT FORMAT NON-AKB");
/* 1006 */                 String CryptoExportRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.exportKey(kekeForExport, exportCrypto, "Non-AKB"));
/* 1007 */                 CryptoExportRespondUnpack = UnpackAtallaCryptoCommand.unpackExportKey(CryptoExportRespond);
/*      */                 
/* 1009 */                 System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedExportHsm);
/*      */                 
/* 1011 */                 if (CryptoAssociated.failedExportHsm) {
/* 1012 */                   System.out.println("Valor de la Bandera de Error de HSM : " + CryptoAssociated.failedExportHsm);
/* 1013 */                   JOptionPane.showMessageDialog(null, "Message Error : " + CryptoExportRespondUnpack, "Error Message", 0);
/* 1014 */                   CryptoAssociated.failedResponseHsm = false;
/*      */                 }
/*      */                 else {
/* 1017 */                   if (exportLenght.equals("Single")) {
/* 1018 */                     CopyExportKey.setText("Export Cryptogram : " + CryptoExportRespondUnpack.substring(0, 16) + 
/* 1019 */                       nl + "Check Digit : " + CryptoExportRespondUnpack.substring(17, 21));
/*      */                   }
/* 1021 */                   if (exportLenght.equals("Double")) {
/* 1022 */                     CopyExportKey.setText("Export Cryptogram : " + CryptoExportRespondUnpack.substring(0, 32) + 
/* 1023 */                       nl + "Check Digit : " + CryptoExportRespondUnpack.substring(33, 37));
/*      */                   }
/* 1025 */                   if (exportLenght.equals("Triple")) {
/* 1026 */                     CopyExportKey.setText("Export Cryptogram : " + CryptoExportRespondUnpack.substring(0, 48) + 
/* 1027 */                       nl + "Check Digit : " + CryptoExportRespondUnpack.substring(49, 53));
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/* 1033 */               CopyExportKey.setEditable(false);
/* 1034 */               JOptionPane.showMessageDialog(null, new JScrollPane(CopyExportKey), "Export Result", 1);
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1039 */               CryptoAssociated.failedExportHsm = false;
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Exception e1)
/*      */         {
/* 1045 */           e1.printStackTrace();
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1050 */     });
/* 1051 */     this.btnNewButton = new JButton("Load Transport Key");
/* 1052 */     this.btnNewButton.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/*      */         try {
/* 1056 */           LoadTransportKEK.actionCommandCryptoAssociated = CryptoAssociated.this.btnNewButton.getText();
/* 1057 */           LoadTransportKEK transportkey = new LoadTransportKEK(CryptoAssociated.cryptoconn);
/* 1058 */           transportkey.setModal(true);
/* 1059 */           transportkey.setVisible(true);
/*      */         }
/*      */         catch (IOException e1)
/*      */         {
/* 1063 */           e1.printStackTrace();
/*      */         }
/*      */       }
/* 1066 */     });
/* 1067 */     this.btnNewButton.setBounds(12, 25, 204, 25);
/* 1068 */     this.btnNewButton.setEnabled(false);
/* 1069 */     panelExport.add(this.btnNewButton);
/* 1070 */     if (this.confirmation.equals("1")) {
/* 1071 */       this.btnNewButton.setText("Show Transport Key");
/*      */     } else {
/* 1073 */       this.btnNewButton.setText("Load Transport Key");
/*      */     }
/*      */   }
/*      */   
/*      */   private void callactionPerformedFind(ActionEvent e)
/*      */   {
/* 1079 */     System.out.println("*** callActionPerformedFind");
/*      */     
/* 1081 */     if (cryptoconn == null) {
/* 1082 */       JOptionPane.showMessageDialog(this, "You need to Login to Data Base First", 
/* 1083 */         "", 0);
/*      */     } else {
/* 1085 */       String consult = null;
/* 1086 */       String command = e.getActionCommand();
/* 1087 */       System.out.println("command");
/*      */       
/* 1089 */       if ("Find".equalsIgnoreCase(command))
/*      */       {
/*      */         try {
/* 1092 */           this.cryptodata = databasesInfo();
/*      */         }
/*      */         catch (SQLException e1)
/*      */         {
/* 1096 */           JOptionPane.showMessageDialog(this, "No Cryptograms Found for this KEY", 
/* 1097 */             "", 0);
/* 1098 */           e1.printStackTrace();
/*      */         }
/* 1100 */         if (this.scrollPanelcrypto != null)
/*      */         {
/* 1102 */           this.table = new JTable(this.cryptodata, this.colunmNames);
/* 1103 */           this.table.addMouseListener(new MouseAdapter()
/*      */           {
/*      */             public void mouseClicked(MouseEvent m)
/*      */             {
/* 1107 */               int clickcount = 0;
/* 1108 */               clickcount = m.getClickCount();
/* 1109 */               if (clickcount == 1) {
/* 1110 */                 System.out.println("One Click");
/* 1111 */                 JTable table = (JTable)m.getSource();
/* 1112 */                 CryptoAssociated.standardrow = table.getSelectedRow();
/* 1113 */                 System.out.println("Row Selected: " + CryptoAssociated.standardrow);
/* 1114 */                 clickcount = 0;
/*      */               }
/*      */               
/*      */             }
/* 1118 */           });
/* 1119 */           DefaultTableModel tableModel = new DefaultTableModel(this.cryptodata, this.colunmNames)
/*      */           {
/*      */ 
/*      */             public boolean isCellEditable(int row, int column)
/*      */             {
/* 1124 */               return false;
/*      */             }
/*      */             
/* 1127 */           };
/* 1128 */           this.table.setModel(tableModel);
/* 1129 */           this.table.setRowSelectionAllowed(true);
/* 1130 */           this.table.setSelectionMode(0);
/* 1131 */           this.table.setAutoscrolls(true);
/* 1132 */           this.scrollPanelcrypto.setViewportView(this.table);
/*      */         }
/*      */       }
/*      */       try {
/* 1136 */         this.rs.close();
/*      */       }
/*      */       catch (SQLException e1) {
/* 1139 */         e1.printStackTrace();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private Object[][] databasesInfo()
/*      */     throws SQLException
/*      */   {
/* 1147 */     this.estSQL1 = cryptoconn.createStatement();
/*      */     
/*      */ 
/*      */ 
/* 1151 */     Object[][] data = new Object[1][5];
/*      */     
/* 1153 */     String sqlCount = "SELECT count (*) as NumOfReg FROM ok_cryptokeys";
/* 1154 */     this.strConsultaSQL = "SELECT * FROM ok_cryptokeys";
/*      */     
/* 1156 */     this.rs = this.estSQL1.executeQuery(sqlCount);
/* 1157 */     this.rs.next();
/* 1158 */     int NumOfReg = this.rs.getInt("NumOfReg");
/* 1159 */     System.out.println(NumOfReg);
/* 1160 */     this.rs.close();
/* 1161 */     data = new Object[NumOfReg][20];
/* 1162 */     if (NumOfReg > 0)
/*      */     {
/* 1164 */       System.out.println("Entre Consult OK Crypto");
/* 1165 */       System.out.println("sqlConsulta: " + this.strConsultaSQL);
/* 1166 */       this.rs = this.estSQL1.executeQuery(this.strConsultaSQL);
/*      */       
/*      */ 
/* 1169 */       data = new Object[NumOfReg][9];
/* 1170 */       int i = 0;
/* 1171 */       while (this.rs.next())
/*      */       {
/* 1173 */         data[i][0] = this.rs.getString("Crypto_id");
/* 1174 */         data[i][1] = this.rs.getString("Name");
/* 1175 */         data[i][2] = this.rs.getString("Cryptogram");
/* 1176 */         data[i][3] = this.rs.getString("Check_Digit");
/* 1177 */         data[i][4] = this.rs.getString("Key_type");
/* 1178 */         data[i][5] = this.rs.getString("Length");
/* 1179 */         data[i][6] = this.rs.getString("Date_created");
/* 1180 */         data[i][7] = this.rs.getString("Environment");
/* 1181 */         i++;
/*      */       }
/*      */     }
/*      */     
/* 1185 */     this.rs.close();
/* 1186 */     this.estSQL1.close();
/*      */     
/*      */ 
/* 1189 */     return data;
/*      */   }
/*      */   
/*      */   private void callActionPerformedCheck(ActionEvent e) {
/* 1193 */     String command = e.getActionCommand();
/* 1194 */     System.out.println("Comando al Entrar: " + command);
/*      */     
/* 1196 */     if ("KeyLenght".equalsIgnoreCase(command)) {
/* 1197 */       System.out.println("Entre Key Block");
/* 1198 */       if (this.comboBoxkeylenght.getSelectedItem().equals("Single")) {
/* 1199 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 1200 */         this.formattedTextField_crypto2.setEditable(false);
/* 1201 */         this.formattedTextField_crypto3.setEditable(false);
/*      */       }
/* 1203 */       if (this.comboBoxkeylenght.getSelectedItem().equals("Double")) {
/* 1204 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 1205 */         this.formattedTextField_crypto2.setEditable(true);
/* 1206 */         this.formattedTextField_crypto3.setEditable(false);
/*      */       }
/* 1208 */       if (this.comboBoxkeylenght.getSelectedItem().equals("Triple")) {
/* 1209 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 1210 */         this.formattedTextField_crypto2.setEditable(true);
/* 1211 */         this.formattedTextField_crypto3.setEditable(true);
/*      */       }
/*      */     }
/* 1214 */     if ("KeyBlockFlag".equalsIgnoreCase(command)) {
/* 1215 */       System.out.println("Entre Lenght Key");
/* 1216 */       System.out.println(this.keyblock);
/* 1217 */       if (this.keyblock)
/*      */       {
/* 1219 */         this.comboBoxkeylenght.setEnabled(false);
/* 1220 */         this.comboBoxkeylenght.setVisible(false);
/*      */         
/* 1222 */         this.comboBoxkeylenght_kb.setEnabled(true);
/* 1223 */         this.comboBoxkeylenght_kb.setVisible(true);
/*      */         
/* 1225 */         this.formattedTextFieldcryptogram.setEditable(false);
/* 1226 */         this.formattedTextFieldcryptogram.setEnabled(false);
/* 1227 */         this.formattedTextFieldcryptogram.setVisible(false);
/*      */         
/* 1229 */         this.textFieldHeader.setVisible(true);
/* 1230 */         this.textFieldHeader.setEditable(false);
/* 1231 */         repaint();
/*      */         
/* 1233 */         this.formattedTextField_crypto3.setEditable(true);
/*      */         
/* 1235 */         this.formattedTextField_crypto2.setEditable(false);
/* 1236 */         this.formattedTextField_crypto2.setVisible(false);
/*      */         
/* 1238 */         this.formattedTextField_CryptoKeyBlock.setVisible(true);
/* 1239 */         this.formattedTextField_CryptoKeyBlock.setEditable(true);
/*      */         
/* 1241 */         this.lblCryptogram.setVisible(false);
/* 1242 */         this.lblPart.setVisible(false);
/* 1243 */         this.lblPart_1.setVisible(false);
/*      */         
/* 1245 */         this.lblPart_keybloc.setVisible(true);
/* 1246 */         this.lblPart_MAC.setVisible(true);
/* 1247 */         this.lblHeader.setVisible(true);
/*      */       }
/* 1249 */       if (!this.keyblock) {
/* 1250 */         this.comboBoxkeylenght_kb.setEnabled(false);
/* 1251 */         this.comboBoxkeylenght_kb.setVisible(false);
/*      */         
/* 1253 */         this.comboBoxkeylenght.setEnabled(true);
/* 1254 */         this.comboBoxkeylenght.setVisible(true);
/*      */         
/* 1256 */         this.textFieldHeader.setVisible(false);
/* 1257 */         this.textFieldHeader.setEditable(false);
/*      */         
/* 1259 */         this.formattedTextFieldcryptogram.setEditable(true);
/* 1260 */         this.formattedTextFieldcryptogram.setEnabled(true);
/* 1261 */         this.formattedTextFieldcryptogram.setVisible(true);
/*      */         
/* 1263 */         this.formattedTextField_crypto3.setEditable(false);
/* 1264 */         this.formattedTextField_crypto2.setEditable(false);
/* 1265 */         this.formattedTextField_crypto2.setVisible(true);
/*      */         
/* 1267 */         this.formattedTextField_CryptoKeyBlock.setVisible(false);
/* 1268 */         this.formattedTextField_CryptoKeyBlock.setEditable(false);
/*      */         
/* 1270 */         this.formattedTextFieldcryptogram.setText("");
/* 1271 */         this.formattedTextField_crypto2.setText("");
/* 1272 */         this.formattedTextField_crypto3.setText("");
/*      */         
/* 1274 */         this.lblPart_keybloc.setVisible(false);
/* 1275 */         this.lblPart_MAC.setVisible(false);
/* 1276 */         this.lblHeader.setVisible(false);
/*      */         
/* 1278 */         this.lblCryptogram.setVisible(true);
/* 1279 */         this.lblPart.setVisible(true);
/* 1280 */         this.lblPart_1.setVisible(true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 

/*      */ 
/*      */ 
/*      */   private void callactionPerformedSave(ActionEvent arg0)
/*      */     throws IOException
/*      */   {
/* 1449 */     String insert_Lenght = null;String insert_keypart1 = null;String insert_keypart2 = null;
/* 1450 */     String insert_keypart3 = null;String insert_finalkey = null;String insert_checkdigit = null;String insert_typehsm = null;
/* 1451 */     String date = null;String insert_keyblock = null;String insert_keytype = null;String insert_environment = null;String insert_keyname = null;String insert_typeofCvv = null;
/* 1452 */     String insert_ExpDateFormat = null;String insert_pinBlockFormat = null;String insert_pinscheme = null;String insert_minPinLength = null;String insert_maxPinLenght = null;
/* 1453 */     String insert_acctNumOffset = null;
/*      */     
/* 1455 */     String verify = null;
/* 1456 */     String confirmation = null;
/* 1457 */     String command = arg0.getActionCommand();
/*      */     
/* 1459 */     if (cryptoconn == null) {
/* 1460 */       JOptionPane.showMessageDialog(this, "You need to Login to Data Base First", 
/* 1461 */         "", 0);
/*      */     }
/* 1463 */     else if ("Save".equalsIgnoreCase(command)) {
/* 1464 */       if (this.keyblock) {
/* 1465 */         insert_keyblock = "YES";
/* 1466 */         insert_Lenght = (String)this.comboBoxkeylenght_kb.getSelectedItem();
/* 1467 */         insert_keypart1 = this.textFieldHeader.getText();
/* 1468 */         insert_keypart2 = this.formattedTextField_CryptoKeyBlock.getText();
/* 1469 */         insert_keypart3 = this.formattedTextField_crypto3.getText();
/* 1470 */         insert_finalkey = insert_keypart1 + "," + insert_keypart2 + "," + insert_keypart3;
/*      */       }
/* 1472 */       if (!this.keyblock) {
/* 1473 */         insert_keyblock = "NO";
/* 1474 */         insert_Lenght = (String)this.comboBoxkeylenght.getSelectedItem();
/* 1475 */         if (insert_Lenght.equals("Single")) {
/* 1476 */           insert_keypart1 = this.formattedTextFieldcryptogram.getText();
/* 1477 */           insert_finalkey = insert_keypart1;
/*      */         }
/* 1479 */         if (insert_Lenght.equals("Double")) {
/* 1480 */           insert_keypart1 = this.formattedTextFieldcryptogram.getText();
/* 1481 */           insert_keypart2 = this.formattedTextField_crypto2.getText();
/* 1482 */           insert_finalkey = insert_keypart1 + insert_keypart2;
/*      */         }
/* 1484 */         if (insert_Lenght.equals("Triple")) {
/* 1485 */           insert_keypart1 = this.formattedTextFieldcryptogram.getText();
/* 1486 */           insert_keypart2 = this.formattedTextField_crypto2.getText();
/* 1487 */           insert_keypart3 = this.formattedTextField_crypto3.getText();
/* 1488 */           insert_finalkey = insert_keypart1 + insert_keypart2 + insert_keypart3;
/*      */         }
/*      */       }
/* 1491 */       insert_keyname = this.textFieldcrypto.getText();
/* 1492 */       insert_keytype = (String)this.comboBox_keytype.getSelectedItem();
/* 1493 */       insert_checkdigit = this.formattedTextField_Checkdigit.getText();
/* 1494 */       UseParameters.getTypeHsm();
/* 1495 */       insert_typehsm = UseParameters.TypeHSM.typeHsm();
/* 1496 */       UseParameters.getEnvriomentUsed();
/* 1497 */       insert_environment = UseParameters.EnvironmentUsed.EnvironmentUsed();
/* 1498 */       date = SystemDate.GenerateDate().toString();
/* 1499 */       insert_typeofCvv = (String)this.comboBoxTypeOfCvv.getSelectedItem();
/* 1500 */       insert_ExpDateFormat = (String)this.ExpDateFormatBox.getSelectedItem();
/* 1501 */       insert_pinBlockFormat = obtainPinBlockFormat((String)this.PinBlockFormatBox.getSelectedItem());
/* 1502 */       insert_pinscheme = (String)this.pinSchemeBox.getSelectedItem();
/* 1503 */       insert_minPinLength = this.TextFieldMinpinLenght.getText();
/* 1504 */       insert_maxPinLenght = this.TextFieldMaxpinLenght.getText();
/* 1505 */       insert_acctNumOffset = this.TextFieldAcctNumbOffset.getText();
/*      */       
/* 1507 */       if ((insert_keyname.isEmpty()) || (insert_finalkey.isEmpty()) || (insert_checkdigit.isEmpty())) {
/* 1508 */         JOptionPane.showMessageDialog(null, "Please Insert a Valid Data");
/*      */       } else {
/* 1510 */         System.out.println("Primer Mensaje Antes de Insertar Data de Criptograma");
/* 1511 */         System.out.println(insert_finalkey);
/*      */         
/* 1513 */         int dialogButton = 0;
/* 1514 */         int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirmation", dialogButton);
/* 1515 */         if (dialogResult == 0) {
/* 1516 */           System.out.println("Yes option");
/* 1517 */           String CheckDigitGenerated = null;
/*      */           try {
/* 1519 */             String CheckDigitRespond = null;
/* 1520 */             UseParameters.getTypeHsm();
/* 1521 */             if (insert_typehsm.equals("Atalla"))
/*      */             {
/*      */ 
/* 1524 */               CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(AtallaCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght));
/* 1525 */               CheckDigitGenerated = UnpackAtallaCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*      */             }
/*      */             
/* 1528 */             if (insert_typehsm.equals("Thales")) {
/* 1529 */               CheckDigitRespond = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.generateCheckDigit(insert_finalkey, insert_Lenght, insert_keytype));
/* 1530 */               CheckDigitGenerated = UnpackThalesCryptoCommand.unpackGenerateCheckDigit(CheckDigitRespond);
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/* 1536 */             System.out.println("Valor de la Bandera de Error de HSM : " + failedResponseHsm);
/*      */             
/* 1538 */             if (failedResponseHsm) {
/* 1539 */               System.out.println("Valor de la Bandera de Error de HSM : " + failedResponseHsm);
/* 1540 */               JOptionPane.showMessageDialog(null, "Message Error : " + CheckDigitGenerated);
/* 1541 */               failedResponseHsm = false;
/*      */             } else {
/* 1543 */               System.out.println("Check Digit From HSM: " + CheckDigitGenerated);
/* 1544 */               if (insert_checkdigit.equals(CheckDigitGenerated))
/*      */               {
/*      */ 
/* 1547 */                 if (((insert_keytype.equals("PVK")) || (insert_keytype.equals("CVK"))) && 
/* 1548 */                   (insert_ExpDateFormat.isEmpty()) && (insert_typeofCvv.isEmpty()) && (insert_acctNumOffset.isEmpty())) {
/* 1549 */                   JOptionPane.showMessageDialog(null, "Please Complete the ERquired Fields (Exp. Date Format, Type of CVV and Account Numb Offset)");
/*      */                 }
/*      */                 
/*      */ 
/*      */ 
/* 1554 */                 confirmation = ExecuteSP.execInsertCryptogram(insert_keyname, insert_finalkey, insert_Lenght, insert_typehsm, 
/* 1555 */                   insert_keyblock, insert_environment, insert_keytype, insert_checkdigit, date, "ODD", 
/* 1556 */                   insert_acctNumOffset, insert_maxPinLenght, insert_minPinLength, insert_typeofCvv, insert_ExpDateFormat, insert_pinscheme, 
/* 1557 */                   insert_pinBlockFormat, cryptoconn, "SA");
/*      */                 
/*      */ 
/*      */ 
/* 1561 */                 JOptionPane.showMessageDialog(null, "Message : " + confirmation, "Information Message", 1);
/*      */               }
/*      */               else {
/* 1564 */                 JOptionPane.showMessageDialog(null, "An Error Ocurred, Verify the Input Data", "Error Message", 0);
/*      */               }
/* 1566 */               failedResponseHsm = false;
/*      */             }
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/* 1571 */             e.printStackTrace();
/*      */           }
/*      */         }
/*      */         else {
/* 1575 */           System.out.println("No Option");
/* 1576 */           JOptionPane.showMessageDialog(null, "Operation Cancelled");
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void callActionPerformedDelete(ActionEvent d)
/*      */   {
/* 1585 */     System.out.println("*** callActionPerformedDelete");
/* 1586 */     String commanddelete = d.getActionCommand();
/*      */     
/* 1588 */     String delid = (String)this.cryptodata[standardrow][0];
/* 1589 */     String delname = (String)this.cryptodata[standardrow][1];
/* 1590 */     String delCrypto = (String)this.cryptodata[standardrow][2];
/* 1591 */     String delcheckdigit = (String)this.cryptodata[standardrow][3];
/* 1592 */     String dellenght = (String)this.cryptodata[standardrow][5];
/*      */     
/* 1594 */     String verify = null;
/* 1595 */     String confirmation = null;
/*      */     
/* 1597 */     if (cryptoconn == null) {
/* 1598 */       JOptionPane.showMessageDialog(this, "You need to Login to Data Base First", 
/* 1599 */         "", 0);
/*      */     } else {
/* 1601 */       System.out.println("ENTRE Borrar");
/* 1602 */       if ("Delete".equalsIgnoreCase(commanddelete)) {
/*      */         try
/*      */         {
/* 1605 */           System.out.println("Primer Mensaje");
/* 1606 */           int dialogButton = 0;
/* 1607 */           int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirmation", dialogButton);
/* 1608 */           if (dialogResult == 0) {
/* 1609 */             System.out.println("Yes option");
/* 1610 */             confirmation = ExecuteSP.execDeleteCryptogram(delid, delname, delCrypto, delcheckdigit, dellenght, cryptoconn);
/*      */             
/*      */ 
/*      */ 
/* 1614 */             JOptionPane.showMessageDialog(null, "Message : " + confirmation);
/*      */           }
/*      */           else {
/* 1617 */             System.out.println("No Option");
/* 1618 */             JOptionPane.showMessageDialog(null, "Operation Cancelled");
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         catch (SQLException e1)
/*      */         {
/* 1625 */           JOptionPane.showMessageDialog(this, "No Data Found", 
/* 1626 */             "", 0);
/* 1627 */           e1.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void callactionPerformedEcho(ActionEvent e)
/*      */   {
/* 1635 */     String testConnection = null;
/*      */     try {
/* 1637 */       testConnection = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.echoTestMessage());
/*      */     }
/*      */     catch (Exception e1) {
/* 1640 */       e1.printStackTrace();
/*      */     }
/* 1642 */     JOptionPane.showMessageDialog(this, "Echo Test Result: " + UnpackThalesCryptoCommand.unpackechoTestMessage(testConnection), 
/* 1643 */       "", 1);
/*      */   }
/*      */   
/*      */   private void callactionPerformedFirmware(ActionEvent e)
/*      */   {
/* 1648 */     String obtainHSMStatus = null;
/*      */     try {
/* 1650 */       obtainHSMStatus = CryptoConnection.sendAndReceiveToHSM(ThalesCryptoCommand.firmwareCommand());
/*      */     }
/*      */     catch (Exception e1) {
/* 1653 */       e1.printStackTrace();
/*      */     }
/*      */     
/* 1656 */     JOptionPane.showMessageDialog(this, "Firmware Version: " + UnpackThalesCryptoCommand.unpackfirmwareCommand(obtainHSMStatus), 
/* 1657 */       "", 1);
/*      */   }
/*      */   
/*      */   public static String obtainPinBlockFormat(String sourceData) {
/* 1661 */     String resultPinblockformat = null;
/* 1662 */     System.out.println(sourceData);
/*      */     
/* 1664 */     if (sourceData.equals("ISO-0")) {
/* 1665 */       resultPinblockformat = PinBlockFormat[0];
/* 1666 */       System.out.println("ISO-0" + resultPinblockformat);
/*      */     }
/* 1668 */     if (sourceData.equals("ISO-1")) {
/* 1669 */       resultPinblockformat = PinBlockFormat[1];
/* 1670 */       System.out.println("ISO-1" + resultPinblockformat);
/*      */     }
/* 1672 */     if (sourceData.equals("VISA-4")) {
/* 1673 */       resultPinblockformat = PinBlockFormat[2];
/* 1674 */       System.out.println("VISA-4" + resultPinblockformat);
/*      */     }
/* 1676 */     if (sourceData.equals("IBM3624")) {
/* 1677 */       resultPinblockformat = PinBlockFormat[3];
/* 1678 */       System.out.println("IBM3624" + resultPinblockformat);
/*      */     }
/* 1680 */     if (sourceData.equals("DOCUTEL-1")) {
/* 1681 */       resultPinblockformat = PinBlockFormat[4];
/* 1682 */       System.out.println("DOCUTEL-1" + resultPinblockformat);
/*      */     }
/* 1684 */     if (sourceData.equals("AS2805.3 Format8")) {
/* 1685 */       resultPinblockformat = PinBlockFormat[5];
/* 1686 */       System.out.println("AS2805.3 Format8" + resultPinblockformat);
/*      */     }
/*      */     
/* 1689 */     System.out.println("Resultado Final Pin Block Format: " + resultPinblockformat);
/* 1690 */     return resultPinblockformat;
/*      */   }
/*      */   
/*      */   protected void CloseFrame() {
/* 1694 */     super.dispose();
/*      */   }
/*      */ }


/* Location:              /home/usuario/Escritorio/OmniSocket.jar!/Framework/CryptoAssociated.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */