package Model;

public class InvoiceLine {
	
	private int number;
	private String item;
	private double price;
	private int count;
	private InvoiceHeader Header;
	
	
	public InvoiceLine() {
	
	}
	
	public InvoiceLine(String item, double price, int count, InvoiceHeader header) {
		this.item = item;
		this.price = price;
		this.count = count;
		Header = header;
	}
	
	public InvoiceLine(int number, String item, double price, int count, InvoiceHeader header) {
		this.number = number;
		this.item = item;
		this.price = price;
		this.count = count;
		Header = header;
	}
	
	public int getNumber() {
		return number;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public InvoiceHeader getHeader() {
		return Header;
	}

	public void setHeader(InvoiceHeader header) {
		Header = header;
	}


	public double getLineTotal() {
		return price * count;
	}
	
	 public String toString() {
	        return "InvoiceLine{" + "item Name: " + item + ", item Price: " + price + ", item Count: " + count + '}';
	    }
	    
	
	public String getDataAsCSV() {
        return "" + getNumber() + "," + getItem() + "," + getPrice() + "," + getCount();
	}

	

}
