package POSPD;

import java.math.BigDecimal;

import POSPD.*;

public class Check extends AuthorizedPayment 
{

	private String routingNumber;
	private String accountNumber;
	private String checkNumber;
	private BigDecimal amtTendered;
	
	public Check()
	{
		
	}
	
	public Check(Sale sale, String amount, String accountNumber, String checkNumber, String amtTendered)
	{
		setAmount(new BigDecimal(amount));
		setAccountNumber (accountNumber);
		setCheckNumber(checkNumber);
		setAmtTendered(new BigDecimal(amtTendered));
		sale.addPayment(this);
	}
	
	public String getRoutingNumber()
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}
	
	public String getAccountNumber()
	{
		return this.accountNumber;
	}
	
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	
	public String getCheckNumber()
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}
	
	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}
	
	@Override
	public Boolean isAuthorized() {
		// TODO Auto-generated method stub
		return null;
	}
}
