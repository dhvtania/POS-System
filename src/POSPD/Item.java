package POSPD;

import java.math.BigDecimal;
import POSPD.TaxCategory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.TreeSet;

public class Item {
	
	private TaxCategory taxCategory;
	public TreeSet<Price> prices;
	private TreeMap<String, UPC> upcs;
	private String number;
	private String description;
	private ArrayList<SaleLineItem > saleLineItems;
	
	public Item()
	{
		saleLineItems = new ArrayList<SaleLineItem>();
		prices = new TreeSet<Price>();
		upcs = new TreeMap<String, UPC>();
	}
	
	public Item(Store store, String number, String description, String taxCatagory)
	{
		this();
		setNumber(number);
		setDescription(description);
		setTaxCategory(store.findTaxCategoryForCategory(taxCatagory));
		store.addItem(this);
	}
	public boolean isUsed()
	{
		return !getSaleLineItems().isEmpty();
	}
	
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
		
	}
	public TreeMap<String, UPC> getUpcs()
	{
		return this.upcs;
	}
	
	public TreeSet<Price> getPrices()
	{
		return this.prices;
	}
	
	public TaxCategory getTaxCategory()
	{
		return this.taxCategory;
	}
	
	
	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}
	
	public void addSaleLineItem (SaleLineItem sli)
	{
		if (sli != null)
		{
			getSaleLineItems().add(sli);
		}
	}
	
	public void addPrice (Price price)
	{
		if (price != null)
		{
			getPrices().add(price);
		}
	}
	
	public void removePrice (Price price)
	{
		if (price != null)
		{
			getPrices().remove(price);
		}
	}
	
	public void addUPC (UPC upc)
	{
		if (upc != null)
		{
			getUpcs().put(upc.getUpc(),upc);
		}
	}
	
	
	public void removeUPC (UPC upc)
	{
		if (upc != null)
		{
			getUpcs().remove(upc.getUpc());
		}
	}
	
	public BigDecimal getPriceForDate(LocalDate date)
	{
		
		Price currentPrice = null;
		for (Price p : prices)
		{ 
			if (p.isInEffect(date))
			{ 
				currentPrice = p;
			}
		}
		if (currentPrice == null) return new BigDecimal("0"); 
				else return currentPrice.getPrice();
	}

	public BigDecimal calcTotal(int quantity, LocalDate date)
	{
		BigDecimal total;

		total = getPriceForDate(date).multiply(new BigDecimal(quantity));
		return(total);
	}
	
	public TaxRate getTaxRateForDate(LocalDate date) {
		return getTaxCategory().getTaxRateForEffectiveDate(date);
	}
	
	
	public int calcItemSoldCount(LocalDate date)
	{
		int count =0;
	
		for (SaleLineItem sli : getSaleLineItems())
		{
			if(sli.getSale().getDateTime().getYear() == date.getYear() && sli.getSale().getDateTime().getDayOfYear() == date.getDayOfYear())
			{
				count+= sli.getQuantity();
			}
		}
		return count;
	}
	
	
	public String toString()
	{
		return getNumber()+" "
				+getDescription()+" "
				+getPriceForDate(LocalDate.now())+" "
				+getTaxCategory().getTaxRate();
	}
}
