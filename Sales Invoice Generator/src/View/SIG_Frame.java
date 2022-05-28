package View;


import java.awt.Dimension;
import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Controller.SIG_Listener;
import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLineTableModel;

public class SIG_Frame extends JFrame {
	
	/**
	 * 
	 * @author AHMED
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration
	private JMenuBar menuBar; private JMenu fileMenu;
    private JMenuItem loadMenuItem, saveMenuItem;
    private JTable invoiceTable, invoiceItemTabel;
    private JButton createBTN, deleteBTN, saveBTN, cancelBTN;
    private JTextField customerName, invoiceDate;
    private JTextPane invoiceNumber, invoiceTotal;
    private JLabel tabelInvoiceTitle, nTitle, dTitle, cTitle, tTitle, tabelInvoiceItemTitle;
    private JPanel leftSide, leftTopSidePanel, leftBotSidePanel, rightSide, rightTopSidePanel, rightBotSidePanel,
                   mainPanel, J_inNum, J_inDate, J_cName, J_inTotal, J_item;

	private List<InvoiceHeader> InvoiceList = new ArrayList<>();
	private InvoiceHeaderTableModel invoiceHeaderTableModel;
	private InvoiceLineTableModel invoiceLineTableModel;
	private SIG_HeaderDialog headerDialogSIG;
	private SIG_LineDialog lineDialogSIG;
	private SIG_Listener listener = new SIG_Listener(this);
	
    
   

	public SIG_Frame() {
    	
    	/* Menu setup */
    	
    	 // menu bar --> menu --> menu items
    	 menuBar = new JMenuBar();
    	 fileMenu = new JMenu("File");
    	 
    	 loadMenuItem = new JMenuItem("Load File");
    	 loadMenuItem.setActionCommand("LoadFile");
    	 loadMenuItem.addActionListener(listener);
    	 
    	 saveMenuItem = new JMenuItem("Save File");
    	 saveMenuItem.setActionCommand("SaveFile");
    	 saveMenuItem.addActionListener(listener);

    	 
    	 menuBar.add(fileMenu);
    	 fileMenu.add(loadMenuItem);
    	 fileMenu.add(saveMenuItem);
    	 setJMenuBar(menuBar);
    	 
    	 
   	 /* Left side JPanel setup */
    	 
    	 // Left top side    
    	 leftTopSidePanel = new JPanel();
    	 tabelInvoiceTitle = new JLabel("Invoices Table");
    	 invoiceTable = new JTable();
    	 
    	 leftTopSidePanel.setLayout(new BoxLayout(leftTopSidePanel,BoxLayout.Y_AXIS));
    	 leftTopSidePanel.setPreferredSize(new Dimension(500,650));
    	 leftTopSidePanel.add(Box.createRigidArea(new Dimension(0,13))); 
    	 leftTopSidePanel.add(tabelInvoiceTitle);
    	 leftTopSidePanel.add(Box.createRigidArea(new Dimension(0,20)));
    	 leftTopSidePanel.add(new JScrollPane(invoiceTable));
         
         
         // Left bot side
    	 leftBotSidePanel = new JPanel();
    	 
    	 createBTN = new JButton("Create New Invoice");
    	 createBTN.setActionCommand("CreateInvoice");
    	 createBTN.addActionListener(listener);
    	 createBTN.setPreferredSize(new Dimension(150, 30));
    	 
    	 deleteBTN = new JButton("Delete Invoice");
         deleteBTN.setPreferredSize(new Dimension(150, 30));
         deleteBTN.setActionCommand("DeleteInvoice");
         deleteBTN.addActionListener(listener);
         
         leftBotSidePanel.setLayout(new FlowLayout());
         leftBotSidePanel.setPreferredSize(new Dimension(200,200));
         leftBotSidePanel.add(createBTN);
         leftBotSidePanel.add(Box.createRigidArea(new Dimension(50,0)));
         leftBotSidePanel.add(deleteBTN);
         
         
         // Merge Left side
         leftSide = new JPanel();
         leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
         leftSide.add(leftTopSidePanel);
         leftSide.add(Box.createRigidArea(new Dimension(0,20)));
         leftSide.add(leftBotSidePanel);
         
         
         
         
         // Right top side
         rightTopSidePanel = new JPanel();
         
         J_inNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
         nTitle = new JLabel("Invoice Number");        
         invoiceNumber = new JTextPane();

         J_inDate = new JPanel(new FlowLayout(FlowLayout.LEFT));        
         dTitle = new JLabel("Invoice Date");      
         invoiceDate = new JTextField(35);

         J_cName  = new JPanel(new FlowLayout(FlowLayout.LEFT));      
         cTitle = new JLabel("Customer Name");      
         customerName = new JTextField(35);

         J_inTotal = new JPanel(new FlowLayout(FlowLayout.LEFT));
         tTitle = new JLabel("Invoice Total");  
         invoiceTotal = new JTextPane();

         J_item = new JPanel();
         J_item.setPreferredSize(new Dimension(500,400));
         J_item.setLayout(new BoxLayout(J_item,BoxLayout.Y_AXIS));    
         tabelInvoiceItemTitle = new JLabel("Invoice Items");      
         invoiceItemTabel = new JTable();

         rightTopSidePanel.setLayout(new BoxLayout(rightTopSidePanel,BoxLayout.Y_AXIS));
         rightTopSidePanel.setPreferredSize(new Dimension(500,650));
         rightTopSidePanel.add(Box.createRigidArea(new Dimension(0,10)));

         J_inNum.setPreferredSize(new Dimension(30,10));
         J_inNum.add(nTitle);
         J_inNum.add(invoiceNumber);
         rightTopSidePanel.add(J_inNum);

         J_inDate.setPreferredSize(new Dimension(30,10));
         J_inDate.add(dTitle);
         J_inDate.add(Box.createRigidArea(new Dimension(18,0)));
         J_inDate.add(invoiceDate);
         rightTopSidePanel.add(J_inDate);

         J_cName.setPreferredSize(new Dimension(30,10));
         J_cName.add(cTitle);
         J_cName.add(customerName);
         rightTopSidePanel.add(J_cName);

         J_inTotal.setPreferredSize(new Dimension(30,10));
         J_inTotal.add(tTitle);
         J_inTotal.add(Box.createRigidArea(new Dimension(15,0)));
         J_inTotal.add(invoiceTotal);
         rightTopSidePanel.add(J_inTotal);

         J_item.add(tabelInvoiceItemTitle);
         J_item.add(Box.createRigidArea(new Dimension(0,20)));
         J_item.add(new JScrollPane(invoiceItemTabel));
         rightTopSidePanel.add(J_item);        
         
         
         // Right bot side
         rightBotSidePanel = new JPanel();
         
         saveBTN = new JButton("Save");
         saveBTN.setPreferredSize(new Dimension(100, 30));
         saveBTN.setActionCommand("SaveLine");
         saveBTN.addActionListener(listener);
         
         
         cancelBTN = new JButton("Cancel");
         cancelBTN.setActionCommand("CancelLine");
         cancelBTN.addActionListener(listener);
         cancelBTN.setPreferredSize(new Dimension(100, 30));


         rightBotSidePanel.setLayout(new FlowLayout());
         rightBotSidePanel.setPreferredSize(new Dimension(200,200));

         rightBotSidePanel.add(saveBTN);
         rightBotSidePanel.add(Box.createRigidArea(new Dimension(50,0)));
         rightBotSidePanel.add(cancelBTN);
        
         
         // Merge Right side  
         rightSide = new JPanel(); 
         rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
         rightSide.add(rightTopSidePanel);
         rightSide.add(Box.createRigidArea(new Dimension(0,20)));
         rightSide.add(rightBotSidePanel);
         
         
        /* Merge left side and right side JPanels into the main JPanel */
         
         mainPanel = new JPanel();
         mainPanel.setLayout(new FlowLayout());
         mainPanel.add(leftSide);
         mainPanel.add(Box.createRigidArea(new Dimension(50,0)));
         mainPanel.add(rightSide);
         
         
        /* SIG_JFrame setup */
         
         setSize(1200, 800);
         setLocation(300, 80);
         add(mainPanel);
         setResizable(false);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
            	 
    	 
    }
    
    public static void main(String[] args) {
		
    	java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {new SIG_Frame().setVisible(true);} });
	}

	

	 public JTable getInvoiceTable() {
			return invoiceTable;
		}

		public JTable getInvoiceItemTabel() {
			return invoiceItemTabel;
		}

		public JButton getCreateBTN() {
			return createBTN;
		}

		public JButton getDeleteBTN() {
			return deleteBTN;
		}

		public JButton getSaveBTN() {
			return saveBTN;
		}

		public JButton getCancelBTN() {
			return cancelBTN;
		}

		public JTextField getCustomerName() {
			return customerName;
		}

		public JTextField getInvoiceDate() {
			return invoiceDate;
		}

		public JTextPane getInvoiceNumber() {
			return invoiceNumber;
		}

		public JTextPane getInvoiceTotal() {
			return invoiceTotal;
		}

		public List<InvoiceHeader> getInvoiceList() {
			return InvoiceList;
		}

		public InvoiceHeaderTableModel getInvoiceHeaderTableModel() {
			return invoiceHeaderTableModel;
		}

		public void setInvoiceHeaderTableModel(InvoiceHeaderTableModel invoiceHeaderTableModel) {
			this.invoiceHeaderTableModel = invoiceHeaderTableModel;
		}

		public void setInvoiceLineTableModel(InvoiceLineTableModel invoiceLineTableModel) {
			this.invoiceLineTableModel = invoiceLineTableModel;
		}

		public InvoiceLineTableModel getInvoiceLineTableModel() {
			return invoiceLineTableModel;
		}
		

		public SIG_HeaderDialog getHeaderDialogSIG() {
			return headerDialogSIG;
		}

		public void setHeaderDialogSIG(SIG_HeaderDialog headerDialogSIG) {
			this.headerDialogSIG = headerDialogSIG;
		}

		public void setLineDialogSIG(SIG_LineDialog lineDialogSIG) {
			this.lineDialogSIG = lineDialogSIG;
		}

		public SIG_LineDialog getLineDialogSIG() {
			return lineDialogSIG;
		}

		public SIG_Listener getListener() {
			return listener;
		}

	
		

}

	            


