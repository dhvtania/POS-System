package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Price implements Comparable<Price> {

	private BigDecimal price;
	private LocalDate effectiveDate;
	private Item item;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yy");
	
	public Price()
	{
	this.effectiveDate = LocalDate.now();
	}
	
	public Price(String price, String effectiveDate) {
		setPrice (new BigDecimal(price));
		price = "";
		setEffectiveDate(LocalDate.parse(effectiveDate,df));
	}
	
	public Boolean isEffective(LocalDate date) {
		return this.effectiveDate.compareTo(date)<=0;
		
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
  
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public boolean isInEffect(LocalDate date)
	{
		if (getEffectiveDate().compareTo(date) <= 0) 
			return true; 
		else 
			return false;
	}
	
	public int compareTo(Price price){
	      return getEffectiveDate().compareTo(price.getEffectiveDate());
	   }
	
	
	public String toString() {
		return getPrice()+" " +getEffectiveDate();
		
	}
}
