package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TaxRate implements Comparable<TaxRate> { 

	private BigDecimal taxRate;
	private LocalDate effectiveDate;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yy");

	public TaxRate(String taxVar, String effectiveDate) {
		this.taxRate = new BigDecimal(taxVar);
		this.effectiveDate = LocalDate.parse(effectiveDate,df);
		
	}
	
	public TaxRate()
	{
		this.taxRate = new BigDecimal("0.00");
		this.effectiveDate = LocalDate.now();
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	
	public String toString() {
		return  getTaxRate().toString();
	}


	public int compareTo(TaxRate o ) {
		return getEffectiveDate().compareTo(o.getEffectiveDate());
	}
}