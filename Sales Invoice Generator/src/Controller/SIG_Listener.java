package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLine;
import Model.InvoiceLineTableModel;
import View.SIG_Frame;
import View.SIG_HeaderDialog;
import View.SIG_LineDialog;

public class SIG_Listener implements ActionListener, ListSelectionListener{
	
	private SIG_Frame sig_frame;
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	SIG_FilesOpreations sig_file;
	
	public SIG_Listener(SIG_Frame sig_frame) {
		this.sig_frame = sig_frame;
		this.sig_file = new SIG_FilesOpreations(sig_frame);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		
		case "CreateInvoice":
			displayHeaderDialog();
			break;
			
		case "DeleteInvoice":
			deleteInvoice();
			break;
			
		case "SaveLine":
			displayLineDialog();
			break;
			
		case "CancelLine":
			cancelLine();
			break;
			
		case "LoadFile":
			sig_file.Loadfile();
			break;
		
		case "SaveFile":
			sig_file.Savefile();
			break;
			
		case "HeaderDialogSave":
			headerDialogSave();
			break;
		case "HeaderDialogCancel":
			headerDialogCancel();
			break;
			
		case "LineDialogSave":
			lineDialogSave();
			break;
		case "LineDialogCancel":
			lineDialogCancel();
			break;

		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		invoiceTableRowSelection();
		
	}
	
	private void invoiceTableRowSelection() {
	 
		int rowIndexSelection = sig_frame.getInvoiceTable().getSelectedRow();
		if(rowIndexSelection >= 0) {
			InvoiceHeader row = sig_frame.getInvoiceHeaderTableModel().getInvoicesList().get(rowIndexSelection);
			sig_frame.getCustomerName().setText(row.getCustomer());
			sig_frame.getInvoiceDate().setText(dateFormat.format(row.getDate()));
			sig_frame.getInvoiceNumber().setText(""+row.getNumber());
			sig_frame.getInvoiceTotal().setText(""+row.getInvoiceTotal());
			ArrayList<InvoiceLine> lines = row.getLines();
			sig_frame.setInvoiceLineTableModel(new InvoiceLineTableModel(lines));
			sig_frame.getInvoiceItemTabel().setModel(sig_frame.getInvoiceLineTableModel());
			sig_frame.getInvoiceLineTableModel().fireTableDataChanged();
			
		}
	}

	
	
	
	
	private void displayHeaderDialog() {
		sig_frame.setHeaderDialogSIG(new SIG_HeaderDialog(sig_frame));
		sig_frame.getHeaderDialogSIG().setVisible(true);
		
	}
	
	private void displayLineDialog() {
		sig_frame.setLineDialogSIG(new SIG_LineDialog(sig_frame));
		sig_frame.getLineDialogSIG().setVisible(true);
	}
	
	
	@SuppressWarnings("unused")
	private void deleteInvoice() {
		int invoiceIndex = sig_frame.getInvoiceTable().getSelectedRow();
        InvoiceHeader header = sig_frame.getInvoiceHeaderTableModel().getInvoicesList().get(invoiceIndex);
        sig_frame.getInvoiceHeaderTableModel().getInvoicesList().remove(invoiceIndex);
        sig_frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        sig_frame.setInvoiceLineTableModel(new InvoiceLineTableModel(new ArrayList<InvoiceLine>()));
        sig_frame.getInvoiceItemTabel().setModel(sig_frame.getInvoiceLineTableModel());
        sig_frame.getInvoiceLineTableModel().fireTableDataChanged();
        sig_frame.getCustomerName().setText("");
        sig_frame.getInvoiceDate().setText("");
        sig_frame.getInvoiceNumber().setText("");
        sig_frame.getInvoiceTotal().setText("");
	
	}

	private void cancelLine() {
		int lineIndex = sig_frame.getInvoiceItemTabel().getSelectedRow();
		InvoiceLine line = sig_frame.getInvoiceLineTableModel().getInvoiceLine().get(lineIndex);
		sig_frame.getInvoiceLineTableModel().getInvoiceLine().remove(lineIndex);
		sig_frame.getInvoiceLineTableModel().fireTableDataChanged();
		sig_frame.getInvoiceHeaderTableModel().fireTableDataChanged();
		sig_frame.getInvoiceTotal().setText("" + line.getHeader().getInvoiceTotal());
		
	}
	
	
	private void headerDialogSave() {
		String dialogcName = sig_frame.getHeaderDialogSIG().getCustomerName().getText();
		String dialoginDateStr = sig_frame.getHeaderDialogSIG().getInvoiceDate().getText();
		sig_frame.getHeaderDialogSIG().setVisible(false);
		sig_frame.getHeaderDialogSIG().dispose();
		sig_frame.setHeaderDialogSIG(null);
		try {
			Date dialoginDate = dateFormat.parse(dialoginDateStr);
			int dialogInNum = checkInvoiceNumbers();
			InvoiceHeader inHeader = new InvoiceHeader(dialogInNum, dialogcName, dialoginDate);
			sig_frame.getInvoiceList().add(inHeader);
			sig_frame.getInvoiceHeaderTableModel().fireTableDataChanged();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(sig_frame, "Wrong date format", "Date Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();}
	}
	
	 private void lineDialogSave() {
			int itemNum = InvoiceHeaderTableModel.inItemNo;
			String itemName = sig_frame.getLineDialogSIG().getItemName().getText();
			String itemCountStr = sig_frame.getLineDialogSIG().getItemCount().getText();
			String itemPriceStr = sig_frame.getLineDialogSIG().getItemPrice().getText();
			
			sig_frame.getLineDialogSIG().setVisible(false);
			sig_frame.getLineDialogSIG().dispose();
			sig_frame.setLineDialogSIG(null);
			
			int itemCount = Integer.parseInt(itemCountStr);
			double itemPrice = Double.parseDouble(itemPriceStr);
			int hIndex = sig_frame.getInvoiceTable().getSelectedRow();

			InvoiceHeader header = sig_frame.getInvoiceHeaderTableModel().getInvoicesList().get(hIndex);
			InvoiceLine inLine = new InvoiceLine(itemNum, itemName, itemPrice, itemCount, header);
			header.addInvoiceLine(inLine);
			sig_frame.getInvoiceLineTableModel().fireTableDataChanged();
			sig_frame.getInvoiceHeaderTableModel().fireTableRowsUpdated(0, hIndex);
			sig_frame.getInvoiceTotal().setText("" + header.getInvoiceTotal());
			displayInvoices();
		}
	 
	
	
	
	private int checkInvoiceNumbers() {
		int max = 0;
		for (InvoiceHeader header : sig_frame.getInvoiceList()) {
			if(header.getNumber() > max) {
				max = header.getNumber();
			}
		}
		return max+1;
	}
	
	private void headerDialogCancel() {
		sig_frame.getHeaderDialogSIG().setVisible(false);
		sig_frame.getHeaderDialogSIG().dispose();
		sig_frame.setHeaderDialogSIG(null);
	}
	
	
	private void lineDialogCancel() {
		sig_frame.getLineDialogSIG().setVisible(false);
		sig_frame.getLineDialogSIG().dispose();
		sig_frame.setLineDialogSIG(null) ;
		
	}




	private void displayInvoices() {
	    System.out.println("***************************");
	    for (InvoiceHeader header : sig_frame.getInvoiceList()) {
	        System.out.println(header);
	    }
	    System.out.println("***************************");
		}

}