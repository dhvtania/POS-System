package POSPD;

import java.math.BigDecimal;

public class Cash extends Payment {
	private BigDecimal amtTendered;
	Sale sale = new Sale();
	
	public Cash ()
	{
		
	}
	
	public Cash(String amount, String amtTendered)
	{
		this.amtTendered = new BigDecimal(amtTendered);
		this.amount = new BigDecimal(amount);
		sale.addPayment(this);
	}
	
	public Cash(Sale sale, String amount, String amtTendered)
	{
		this();
		setAmount(new BigDecimal(amount));
		setAmtTendered(new BigDecimal(amtTendered));
		sale.addPayment(this);
	}
	
	public BigDecimal calcChange() {
		BigDecimal change;
		return change = this.amtTendered.subtract(this.amount);
	}
	
	public boolean hasCash() {
		return true;
	}
	
	public String toString() {
		String payment = null;
		return payment;
	}
	
	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}

}
