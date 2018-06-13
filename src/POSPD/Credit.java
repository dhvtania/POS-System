package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Credit extends AuthorizedPayment
{

	private String cardType;
	private String acctNumber;
	private LocalDate expireDate;
	
	public Credit()
	{

	}
	
	public Credit(Sale sale, String amount, String cardType, String acctNumber, String expireDate)
	{
		setAmount(new BigDecimal(amount));
		setCardType(cardType);
		setAcctNumber(acctNumber);
		String[] ed;
		ed = expireDate.split("/");
		sale.addPayment(this);
	}
	
	public String getCardType()
	{
		return this.cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}
	
	public String getAcctNumber()
	{
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber)
	{
		this.acctNumber = acctNumber;
	}
	
	public LocalDate getExpireDate()
	{
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate)
	{
		this.expireDate = expireDate;
	}

	@Override
	public Boolean isAuthorized() {
		// TODO Auto-generated method stub
		return null;
	}
}