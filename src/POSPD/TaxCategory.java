package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

public class TaxCategory {
	
	private String category;
	private LocalDate effectiveDate;
	private TreeSet<TaxRate> taxRates;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
 
	public TaxCategory()
	{
		taxRates = new TreeSet<TaxRate>();
		
	}

	public TaxCategory(String category)
	{
		this();
		setCategory(category);
		this.taxRates = taxRates;
		

	}
	
	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public LocalDate getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = LocalDate.parse(effectiveDate,df);
	}
	
	public TreeSet<TaxRate> getTaxRate() {
		return taxRates;
	}
	
	public void setTaxRate(TreeSet<TaxRate> taxRate) {
		this.taxRates = taxRate;
	}
	
	public void removeTaxRate(TaxRate taxRate) {
		taxRates.remove(taxRate);
	}

	public String toString() {
		return getCategory();
	}

	public void addTaxRate(TaxRate taxRate) {
		taxRates.add(taxRate);
	}
	
	public TaxRate getTaxRateForEffectiveDate(LocalDate date) {
		String taxVar="0";
		String effectiveDate="1/1/20";
		TaxRate currentTax = new TaxRate( taxVar,  effectiveDate);
		TaxRate temp;
		Iterator<TaxRate> iterator = taxRates.iterator();
		while (iterator.hasNext()) {
			temp= iterator.next();
			if(temp.getEffectiveDate().compareTo(date)<=0){
				currentTax = temp;
			}
		}
		return currentTax;

	}

}
