package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLine;
import View.SIG_Frame;

public class SIG_FilesOpreations {
	
	private SIG_Frame sig_frame;
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public SIG_FilesOpreations(SIG_Frame sig_frame) {
		this.sig_frame = sig_frame;
	}
	
	 void Loadfile() {
		int result;
		JOptionPane.showMessageDialog(sig_frame, "Please, select a header file.", "Header File", JOptionPane.WARNING_MESSAGE);
		JFileChooser loadFile = new JFileChooser();
		result = loadFile.showOpenDialog(sig_frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File headerFile = loadFile.getSelectedFile();
			
			try {
				 
				try (BufferedReader headerBReader = new BufferedReader(new FileReader(headerFile))) {
					String linesHeader = null;
					
					while ((linesHeader = headerBReader.readLine()) != null) {
						String [] headerParts = linesHeader.split(",");
						String inNumStr = headerParts[0];
						String inDateStr = headerParts[1];
						String cName = headerParts[2];
						
						int inNum = Integer.parseInt(inNumStr);
						Date inDate = dateFormat.parse(inDateStr);
						
						InvoiceHeader inHeader = new InvoiceHeader(inNum, cName, inDate);
						sig_frame.getInvoiceList().add(inHeader);
					}
				}
				JOptionPane.showMessageDialog(sig_frame, "Please, select a lines file.", "Lines File", JOptionPane.WARNING_MESSAGE);
				result = loadFile.showOpenDialog(sig_frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					File linesFile = loadFile.getSelectedFile();

					
					try (BufferedReader linesBReader = new BufferedReader(new FileReader(linesFile))) {
						String linesLine = null;
						
						while ((linesLine = linesBReader.readLine()) != null) {
							String [] lineParts = linesLine.split(",");
							String inNumStr = lineParts[0];
							String itemName = lineParts[1];
							String itemPriceStr = lineParts[2];
							String itemCountStr = lineParts[3];
							
							int inNum = Integer.parseInt(inNumStr);
							double itemPrice = Double.parseDouble(itemPriceStr);
							int itemCount = Integer.parseInt(itemCountStr);
							InvoiceHeader header = findInNum(inNum);
							InvoiceLine inLine = new InvoiceLine(inNum, itemName, itemPrice, itemCount, header);
							header.getLines().add(inLine);
						}
					}
					sig_frame.setInvoiceHeaderTableModel( new InvoiceHeaderTableModel(sig_frame.getInvoiceList()));
					sig_frame.getInvoiceTable().setModel(sig_frame.getInvoiceHeaderTableModel());
					sig_frame.getInvoiceTable().validate();
					sig_frame.getInvoiceTable().getSelectionModel().addListSelectionListener(sig_frame.getListener());
					
				}
			} catch (Exception ex){
				JOptionPane.showMessageDialog(sig_frame, "Error: " + ex.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE);
			}							
		}
		displayInvoices();
	}
	 
	 
	 private InvoiceHeader findInNum(int inNum) {
			InvoiceHeader header = null;
			for (InvoiceHeader in : sig_frame.getInvoiceList()) {
				if(inNum == in.getNumber()) {
					header = in;
					break;
				}
			}
			return header;
			
		}
	
	
	
	 void Savefile() {
		 String headers = "";
	        String lines = "";
	        for (InvoiceHeader header : sig_frame.getInvoiceList()) {
	            headers += header.getDataAsCSV();
	            headers += "\n";
	            for (InvoiceLine line : header.getLines()) {
	                lines += line.getDataAsCSV();
	                lines += "\n";
	                }
	        }
	        
	        JOptionPane.showMessageDialog(sig_frame, "Please, select file to save header data.", "Header File", JOptionPane.WARNING_MESSAGE);
	        JFileChooser fileChooser = new JFileChooser();
	        int result = fileChooser.showSaveDialog(sig_frame);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File headerFile = fileChooser.getSelectedFile();
	            try {
	                FileWriter filewriterl = new FileWriter(headerFile);
	                filewriterl.write(headers);
	                filewriterl.flush();
	                filewriterl.close();

	                JOptionPane.showMessageDialog(sig_frame, "Please, select file to save lines data.", "Lines File", JOptionPane.WARNING_MESSAGE);
	                result = fileChooser.showSaveDialog(sig_frame);
	                if (result == JFileChooser.APPROVE_OPTION) {
	                    File linesFile = fileChooser.getSelectedFile();
	                    FileWriter filewriterh = new FileWriter(linesFile);
	                    filewriterh.write(lines);
	                    filewriterh.flush();
	                    filewriterh.close();
	                }
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(sig_frame, "Error: " + ex.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        
	

	    }
	 
	 private void displayInvoices() {
	        System.out.println("''''''''''''''''''''''''''''''''''''''''''");
	        for (InvoiceHeader header : sig_frame.getInvoiceList()) {
	            System.out.println(header);
	        }
	        System.out.println("''''''''''''''''''''''''''''''''''''''''''");
	    }
	

}
