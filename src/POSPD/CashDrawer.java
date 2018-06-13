package POSPD;

import java.math.BigDecimal;

public class CashDrawer {
	
	private Register cashDrawer;
	private BigDecimal cashAmount;
	private int position;
	
	public CashDrawer(){
	    
		this.cashAmount = new BigDecimal(0);
		this.position = 0;
	}
	public BigDecimal getCashAmount()
	{
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount)
	{
		this.cashAmount = cashAmount;
	}
	
	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public void addCash(BigDecimal cash) {
		cashAmount = cashAmount.add(cash);
	}
	
	public void removeCash(BigDecimal cash) {
		cashAmount = cashAmount.subtract(cash);
	}
	
	public String toString() {
		return " CashAmout : "+getCashAmount()+" Position : "+getPosition();
	}

}
