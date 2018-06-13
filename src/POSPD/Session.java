package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Session implements Comparable<Session> {
	
	private Cashier cashier;
	private Register register;
	private ArrayList<Sale> sales;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	
	public Session() {
		startDateTime = LocalDateTime.now();
		sales = new ArrayList<Sale>();
	}
	
	public Session(Store store, String cashier, String register)
	{
		this();
		Cashier c = store.findCashierForNumber(cashier);
		setCashier(c);
		setRegister(store.findRegisterForNumber(register));
		store.addSession(this);
		c.addSession(this);
		
	}
	
	public Session(Store store, Cashier cashier, Register register)
	{
		this();
		setCashier(cashier);
		setRegister(register);
		store.addSession(this);
		cashier.addSession(this);
		
	}

	public LocalDateTime getStartDateTime() {
		return this.startDateTime;
	}

	public void setStartDate(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return this.endDateTime;
	}

	public void setEndDate(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Register getRegister() {
		return this.register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public Cashier getCashier() {
		return this.cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public ArrayList<Sale> getSales() {
		return this.sales;
	}

	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}
	
	public void addSale(Sale sale) {
		if (sale != null)
		{
			getSales().add(sale);
		}
	}

	public void removeSale(Sale sale) {
		sales.remove(sale);
	}
	
	public int getNumberSales()
	{
		return getSales().size();
	}
	
	public BigDecimal calcCashCountDiff(BigDecimal cashCount)
	{
		return (getRegister().getCashDrawer().getCashAmount().add(getTotalCash()).subtract(cashCount)).setScale(2, BigDecimal.ROUND_UP);
	}
	
	public BigDecimal getTotalCash()
	{
		BigDecimal totalCash = new BigDecimal("0");
		for (Sale sale: getSales())
		{
			totalCash = totalCash.add(sale.calcCash());
		}
		return totalCash.setScale(2, BigDecimal.ROUND_UP);
	}
	
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal ("0");
		for (Sale  sale : sales)
		{ total = total.add(sale.calcTotal());}
		
		return total.setScale(2, BigDecimal.ROUND_UP);
	}
	
	 public String toString()
	   {
		   String sales = "";
		   for (Sale sale : getSales())
		   { 
			   sales += "  "+sale.toString()+"\n";
		   }
		   return "Session : Cashier :"+getCashier().getPerson().getName() +
				  " Register :" +getRegister().getNumber()+
				  " Date : " + getStartDateTime().toString()+
		          " Total : " +calcTotal() +"\n"+ sales;
	   }

	@Override
	public int compareTo(Session o) {
		// TODO Auto-generated method stub
		return getStartDateTime().compareTo(o.startDateTime);
	}
	
}
