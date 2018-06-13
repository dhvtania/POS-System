package POSPD;

public class UPC
{

	private String upc;
	private Item item;
 
	public UPC()
	{
		 
	}

	public UPC(String upc, Item item)
	{
		
		setUpc(upc);
		setItem(item);
	}

	public String getUpc()
	{
		return this.upc;
	}

	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public String toString()
	{
		return this.getUpc();
	}
}