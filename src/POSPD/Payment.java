package POSPD;

import java.math.BigDecimal;

public abstract class  Payment
{

	protected BigDecimal amount;
	private BigDecimal amtTendered;

	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmtTendered()
	{ 
		return getAmount();
	}
	
	public BigDecimal calcChange() {
		BigDecimal amtChangeBack = new BigDecimal("0");
		return amtChangeBack = this.amtTendered.subtract(this.amount);
	}

	public boolean hasCash()
	{
		return true;
	}
}