package POSPD;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;



public class PromoPrice extends Price
{
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yy");
	private LocalDate endDate;
	
	public PromoPrice()
	{
		
	}
	
	public PromoPrice(String price, String effectiveDate, String endDate) {
		super (price, effectiveDate);
		this.endDate = LocalDate.parse(endDate,df);	
	}
	
	public PromoPrice(Item item, String price, String effectiveDate, String endDate)
	{
		this(price, effectiveDate,endDate);
		setItem(item);
		setPrice(new BigDecimal(price));
	
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public boolean isInEffect(LocalDate date)
	{
		//System.out.println(date);
		if ((getEffectiveDate().compareTo(date) <= 0) && (getEndDate().compareTo(date) >= 0)) {
			return true; 
		}
		else 
			return false;
	}
	
	public String toString()
	   {
		   return getPrice().toPlainString()+" " +getEffectiveDate().toString()+"-"+getEndDate().toString();
	   }
}
