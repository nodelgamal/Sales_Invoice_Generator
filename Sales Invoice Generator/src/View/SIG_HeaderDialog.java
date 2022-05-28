package View;


import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SIG_HeaderDialog extends JDialog{
	
	/**
	 * 
	 * @author AHMED
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField customerName, invoiceDate;
	private JLabel dTitle, cTitle;
	private JButton saveBTN, cancelBTN;
	
	public SIG_HeaderDialog(SIG_Frame sig_frame) {
		
		cTitle = new JLabel("Customer Name");      
        customerName = new JTextField(20);
        dTitle = new JLabel("Invoice Date");      
        invoiceDate = new JTextField(20);
        saveBTN = new JButton("Save");
        saveBTN.setActionCommand("HeaderDialogSave");
        saveBTN.addActionListener(sig_frame.getListener());
        cancelBTN = new JButton("Cancel");       
        cancelBTN.setActionCommand("HeaderDialogCancel");
        cancelBTN.addActionListener(sig_frame.getListener());
        
        setLayout(new GridLayout(3, 2));
        
        add(cTitle);
        add(customerName);
        add(dTitle);
        add(invoiceDate);
        add(saveBTN);
        add(cancelBTN);
		setLocationByPlatform(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        pack();
	}

	public JTextField getCustomerName() {
		return customerName;
	}

	public JTextField getInvoiceDate() {
		return invoiceDate;
	}

}
