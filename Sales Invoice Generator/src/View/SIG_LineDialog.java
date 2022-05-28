package View;

import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SIG_LineDialog extends JDialog {
	
	/**
	 * 
	 * @author AHMED
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nTitle, cTitle, pTitle;
	private JTextField itemName, itemCount, itemPrice;
	private JButton saveBTN, cancelBTN;

	
	public SIG_LineDialog(SIG_Frame sig_frame) {
			
			nTitle = new JLabel("Item Name");      
	        itemName = new JTextField(20);
	        cTitle = new JLabel("Item Count");      
	        itemCount = new JTextField(20);
	        pTitle = new JLabel("Item Price");      
	        itemPrice = new JTextField(20);
	        saveBTN = new JButton("Save");
	        saveBTN.setActionCommand("LineDialogSave");
	        saveBTN.addActionListener(sig_frame.getListener());
	        cancelBTN = new JButton("Cancel");       
	        cancelBTN.setActionCommand("LineDialogCancel");
	        cancelBTN.addActionListener(sig_frame.getListener());
	        
	        setLayout(new GridLayout(4, 2));
	        
	        add(nTitle);
	        add(itemName);
	        add(cTitle);
	        add(itemCount);
	        add(pTitle);
	        add(itemPrice);
	        add(saveBTN);
	        add(cancelBTN);
			setLocationByPlatform(true);
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	
	        pack();
		}
	
		public JTextField getItemName() {
			return itemName;
		}
		
		public JTextField getItemCount() {
			return itemCount;
		}
		
		public JTextField getItemPrice() {
			return itemPrice;
		}


}
