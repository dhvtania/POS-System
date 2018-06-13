package POSPD;

import POSPD.Store;

public class Register {
	
	private String number;
	private CashDrawer cashDrawer;
	Store store = new Store();
	
	public Register()
	{
		setCashDrawer(new CashDrawer());
	}
	
	public Register(String number) {
		this();
		setNumber(number);
		store.addRegister(this);
	}
	
	public Register(Store store, String number)
	{
		this();
		setNumber(number);
		store.addRegister(this);
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber( String number) {
		this.number = number;
	}

	public CashDrawer getCashDrawer() {
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) {
		this.cashDrawer = cashDrawer;
	}
	
	public String toString() {
		return getNumber();
	}

}
