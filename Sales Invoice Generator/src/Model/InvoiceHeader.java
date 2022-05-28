package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader {

	private int number;
	private String customer;
	private Date date;
	private ArrayList<InvoiceLine> lines = new ArrayList<InvoiceLine>();
	
	public InvoiceHeader() {
		
	}
	
	public InvoiceHeader(int number, String customer, Date date) {
		this.number = number;
		this.customer = customer;
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<InvoiceLine> getLines() {
		if (lines == null)
			lines = new ArrayList<>(); 
		return lines;
	}

	public void setLines(ArrayList<InvoiceLine> lines) {
		this.lines = lines;
	}
	

	public double getInvoiceTotal() {
		double total = 0.0;
		for(int i = 0; i < lines.size(); i++) {
			total += lines.get(i).getLineTotal();
		}
		return total;
	}
	
	
	public void addInvoiceLine(InvoiceLine inLine) {
		getLines().add(inLine);
	}
	
	 @Override
	    public String toString() {
	        String str = "InvoiceHeader{" + "Invoice Number: " + number + ", Customer Name: " + customer + ", Date: " + date + '}';
	        for (InvoiceLine line : getLines()) {
	            str += "\n\t" + line;
	        }
	        return str;
	    }
	
	
	public String getDataAsCSV() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getNumber() + "," + dateFormat.format(getDate()) + "," + getCustomer();
    }
    
	
	
	
	
}
