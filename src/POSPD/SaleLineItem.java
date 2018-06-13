package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleLineItem {
	
	private Sale sale;
	private Item item;
	private int quantity;
	
	public SaleLineItem()
	{
		
	}
	
	public SaleLineItem(Store store, Sale sale, String itemNumber, String quantity)
	{
		setQuantity(Integer.parseInt(quantity));
		setItem(store.findItemForNumber(itemNumber));
		sale.addSaleLineItem(this);
		getItem().addSaleLineItem(this);
		setSale(sale);
	}
	
	public SaleLineItem(Sale sale, Item item, int quantity)
	{
		setQuantity(quantity);
		setItem(item);
		sale.addSaleLineItem(this);
		getItem().addSaleLineItem(this);
		setSale(sale);
	}
	
	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public void setSale(Sale sale)
	{
		this.sale = sale;
	}
    
	public int getQuantity()
	{
		return this.quantity;
	}
    
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public BigDecimal calcSubTotal()
	{
		BigDecimal subtotal = getItem().calcTotal(getQuantity(), getSale().getDateTime());
		return(subtotal);
	}
	
	public BigDecimal calcTax()
	{
		BigDecimal amount = calcSubTotal();
		BigDecimal taxRate = getItem().getTaxRateForDate(getSale().getDateTime()).getTaxRate();
		BigDecimal tax = amount.multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		return tax;
	}
	
	public String toString() {
		LocalDate date =  sale.getDateTime();
		return getItem().getNumber()+" "+getItem().getDescription()+" "+getQuantity()+"@$"+getItem().getPriceForDate(date)+" $"+calcSubTotal().toString();
	}
}
