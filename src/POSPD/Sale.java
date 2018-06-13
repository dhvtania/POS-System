package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sale {
	
	private List<Payment> payments;
	private List<SaleLineItem> saleLineItems;
	private LocalDate dateTime;
	private Boolean taxFree;
	private Session session;
     
	public Sale() {
		payments =new ArrayList<Payment>();
		saleLineItems =new ArrayList<SaleLineItem>();
		dateTime = LocalDate.now();
		setTaxFree(false);
	}
	
	public Sale(Session session, String taxFree) {
		this();
		if (taxFree.equals("N")){
			setTaxFree(true);
		}
		else
		{
			setTaxFree(false);
		}
		dateTime = LocalDate.now();
		session.addSale(this);
		setSession(session);
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public LocalDate getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree() {
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree) {
		this.taxFree = taxFree;
	}
	
	public void addPayment(Payment payment)
	{
		getPayments().add(payment);
	}
	
	private ArrayList<Payment> getPayments() {
		return (ArrayList<Payment>) this.payments;
	}
	
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}
	
	public void addSaleLineItem(SaleLineItem sli)
	{
		getSaleLineItems().add(sli);
		sli.setSale(this);
	}
	
	public ArrayList<SaleLineItem> getSaleLineItems() {
		return (ArrayList<SaleLineItem>) saleLineItems;
	}
	
	public BigDecimal calcTotal() {
		return calcSubTotal().add(calcTax());
	}

	public BigDecimal calcSubTotal() {
		BigDecimal subTotal = new BigDecimal ("0");
		for (SaleLineItem sli : saleLineItems)
		{ 
			subTotal = subTotal.add(sli.calcSubTotal());
		}
		return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal calcTax() {
		BigDecimal tax = new BigDecimal ("0");
		if (!getTaxFree() )
		{
			for (SaleLineItem sli : saleLineItems)
			{ tax = tax.add(sli.calcTax());}
		}
		return tax.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public boolean isPaymentEnough() {
		return (calcTotalPayment().compareTo(calcTotal()) >= 0);
	}

	public BigDecimal calcChange() {
		return calcAmtTendered().subtract(calcTotal());
	}
	
	public BigDecimal calcAmtTendered() {
		BigDecimal amtTendered = new BigDecimal ("0");
		for (Payment p : payments)
		{ amtTendered = amtTendered.add(p.getAmtTendered());}
		
		return amtTendered.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal calcAmount(BigDecimal amountTendered)
	{
		BigDecimal calcAmount = calcTotal().subtract(calcTotalPayment());
		if (calcAmount.compareTo(amountTendered) > 0)
		{
			calcAmount = amountTendered;
		}
		return calcAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	} 
	
	public BigDecimal calcTotalPayment()
	{
		BigDecimal payment = new BigDecimal ("0");
		for (Payment p : payments)
		{ payment = payment.add(p.getAmount());}
		
		return payment.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public String toString() 
	{
		String slis = "";
		   for (SaleLineItem sli : getSaleLineItems()) {
			   slis += "     "+sli.toString()+"\r\n";
			   }
		return "Sale : SubTotal = "+calcSubTotal().toPlainString() 
				+" Tax = "+calcTax().toPlainString()
				+" Total = "+calcTotal().toPlainString()
				+" Payment = "+calcTotal().toPlainString()
				+" Change = "+calcChange()+"\r\n"+slis;
	}
	
	public BigDecimal calcCash()
	{
		BigDecimal cash = new BigDecimal ("0");
		for (Payment payment : payments)
		{ if (payment.hasCash()) cash = cash.add(payment.getAmount());}
		
		return cash;
	}

}
