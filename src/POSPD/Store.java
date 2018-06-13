package POSPD;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import POSDM.StoreDM;

public class Store {
	
	private String number;
	private String name;
	private TreeMap<String,Register> registers;
	private TreeMap<String,TaxCategory> taxCategories;
	private TreeMap<String, Item > items;
	private TreeMap<String, Cashier> cashiers;
	private TreeMap<String, UPC> upcs;
	private TreeSet<Session> sessions;
	
	public Store()
	{
		items = new TreeMap <String, Item>();
		cashiers = new TreeMap <String, Cashier>();
		registers = new TreeMap <String, Register>();
		upcs = new TreeMap <String, UPC>();
		taxCategories = new TreeMap <String, TaxCategory>();
		sessions = new TreeSet<Session>();
	}
	
	public Store(String number, String name)
	{
		this.setNumber(number);
		this.setName(name);
		items = new TreeMap <String, Item>();
		cashiers = new TreeMap <String, Cashier>();
		registers = new TreeMap <String, Register>();
		upcs = new TreeMap <String, UPC>();
		taxCategories = new TreeMap <String, TaxCategory>();
	}
	
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeMap<String, Cashier> getCashiers() {
		return cashiers;
	}

	public TreeMap<String, UPC> getUPCs() {
		return this.upcs;
	}

	public void setUPCs(TreeMap<String, UPC> uPCs) {
		this.upcs = uPCs;
	}

	public TreeMap<String, TaxCategory> getTaxCategories() {
		return taxCategories;
	}

	public void setTaxCategories(TreeMap<String, TaxCategory> taxCategories) {
		this.taxCategories = taxCategories;
	}

	public TreeMap<String, Register> getRegisters() {
		return  this.registers;
	}

	public void setRegisters(TreeMap<String, Register> registers) {
		this.registers =  registers;
	}

	public void setItems(TreeMap<String, Item> items) {
		this.items = items;
	}

	public TreeSet<Session> getSessions() {
		return  this.sessions;
	}

	public void setSessions(TreeSet<Session> sessions) {
		this.sessions = (TreeSet<Session>) sessions;
	}
	
	public ArrayList getCashierList()
	{
		ArrayList cashierList = new ArrayList();
		for (Entry <String, Cashier> cashierEntry : getCashiers().entrySet())
		{
			cashierList.add(cashierEntry.getValue());
		}
		return cashierList;
	}
	
	public ArrayList getRegisterList()
	{
		ArrayList registerList = new ArrayList();
		for (Entry <String, Register> registerEntry : getRegisters().entrySet())
		{
			registerList.add(registerEntry.getValue());
		}
		return registerList;
	}
	
	public ArrayList getTaxCategoryList()
	{
		ArrayList tcList = new ArrayList();
		for (Entry <String, TaxCategory> tcEntry : getTaxCategories().entrySet())
		{
			tcList.add(tcEntry.getValue());
		}
		return tcList;
	}
	
	public void addItem(Item item)
	{
		if (item != null)
		{
			getItems().put(item.getNumber(), item);
		}
	}
	
	public void removeItem(Item item) {
		if (item != null)
		{
			getItems().remove(item.getNumber());
		}
	}
	
	public void addUPC(UPC upc)
	{
		if (upc != null)
		{
			getUPCs().put(upc.getUpc(), upc);
		}
	}
	
	public void removeUPC(UPC upc)
	{
		if (upc != null)
		{
			getUPCs().remove(upc.getUpc());
		}
	}
	
	public void addTaxCategory(TaxCategory taxCategory)
	{
		if (taxCategory != null)
		{
			getTaxCategories().put(taxCategory.getCategory(), taxCategory);
		}
	}
	
	public void removeTaxCategory(TaxCategory taxCategory)
	{
		if (taxCategory != null)
		{
			getTaxCategories().remove(taxCategory.getCategory());
		}
	}
	
	public void addRegister(Register register)
	{
		if (register != null)
		{
			getRegisters().put(register.getNumber(), register);
		}
	}
	
	public void removeRegister(Register register)
	{
		if (register != null)
		{
			getRegisters().remove(register.getNumber());
		}
	}
	
	public void addCashier(Cashier cashier)
	{
		if (cashier != null)
		{
			getCashiers().put(cashier.getNumber(), cashier);
		}
	}
	
	public void removeCashier(Cashier cashier)
	{
		if (cashier != null)
		{
			getCashiers().remove(cashier.getNumber());
		}
	}

	public void addSession(Session session)
	{
		if (session != null)
		{
			getSessions().add(session);
		}
	}

	public void removeSession(Session session) {
		if (sessions != null)
		{
			getSessions().remove(sessions);
		}
	}
	
	public Item findItemForUPC(String upc)
	{
		if (upc.length() >0) 
		{ 
			if (getUPCs().get(upc) == null) return null;
			
			else return getUPCs().get(upc).getItem();
		}
		else return null;
	}
	
	public Cashier findCashierForNumber(String number)
	{
		if (number.length() >0) 
		{ 
			return getCashiers().get(number);
		}
		else return null;
	}
	
	public Register findRegisterForNumber(String number)
	{
		if (number.length() >0) 
		{ 
			return getRegisters().get(number);
		}
		else 
			return null;
	}
	
	public boolean isRegisterUsed(Register register)
	{
		boolean isUsed = false;
		for (Session session : getSessions()) 
		{
			if (session.getRegister() == register) isUsed =true;
		}
		return isUsed;
	}
	
	public TaxCategory findTaxCategoryForCategory(String category)
	{
		if (category.length() >0) 
		{ 
			return taxCategories.get(category);
		}
		else return null;
	}
	
	public Item findItemForNumber(String number)
	{
		if (number.length() >0) 
		{ 
			return getItems().get(number);
		}
		else return null;
	}
	
	public TreeMap<String,Item> getItems()
	{
		return this.items;
	}
	
	public boolean isTaxCategoryUsed(TaxCategory taxCategory)
	{
		boolean isUsed = false;
		for (Entry <String, Item> itemEntry : getItems().entrySet())
			if (taxCategory == itemEntry.getValue().getTaxCategory()) isUsed =true;
		return isUsed;
	}
	
	public void openStore()
	{
		try {
			StoreDM.LoadStore(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
