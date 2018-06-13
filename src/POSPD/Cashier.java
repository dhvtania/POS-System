package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cashier {
	
	private String number;
	private Person person;
	private ArrayList<Session> sessions;
	private String password;
	
	public Cashier()
	{ 
		this.number= "0";
		this.password = "Password";
	    this.sessions = new ArrayList<Session>();
		this.person = new Person();
	}
	
	public Cashier(String number,  String password) {
		this();
		this.number= number;
		this.password = password;
	    this.sessions = new ArrayList<Session>();
		
	}
	
	public Cashier(String number, Person person, String password)
	{
		this.number = number;
		this.person = person;
		this.password = password;
		this.sessions = new ArrayList<Session>();
	}
	
	public Boolean isUsed()
	{
		return getSessions().size() != 0;
	}
	
	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public Person getPerson()
	{
		return this.person;
	}
	
	public void setPerson(Person person){
		this.person = person;
	}
	
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void addSession(Session session) {
		sessions.add(session);
	}
	
	public void removeSession(Session session) {
		sessions.remove(session);
	}
	
	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}
	
	public Boolean isAuthorized(String password) {
		return getPassword().compareTo(password)==0; 
	}
	
	public String toString()
	{
		return  getNumber() +getPerson();
	}
	
	public BigDecimal calcDollarSales(LocalDate date)
	{
		BigDecimal dollarSales= new BigDecimal("0.00");
		for (Session session : getSessions())
		{
			
			if (session.getStartDateTime().getYear() == date.getYear() &&
					session.getStartDateTime().getDayOfYear() == date.getDayOfYear())
				{
				
					dollarSales = dollarSales.add(session.calcTotal());
				}
		}
		 
		return dollarSales;
	}
	
	public int calcNumberSales(LocalDate date)
	{
		int numSales= 0;
		for (Session session : getSessions())
		{
			
			if (session.getStartDateTime().getYear() == date.getYear() &&
					session.getStartDateTime().getDayOfYear() == date.getDayOfYear())
				{
					numSales+=session.getSales().size();
				}
		}
		return numSales;
	}
	
}
