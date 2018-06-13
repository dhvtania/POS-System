package POSPD;

public class Person {
	
	private String name;
	private String address;
	private String phone;
	private String ssn;
	private Cashier cashier;
	private String city;
	private String state;
	private String zip;
	
	public Person()
	{
	}
	
	public Person(String name, String address, String phone, String ssn)
	{
		setName(name);
		setAddress(address);
		setPhone(phone);
	}
	
	public Person(String name, String address, String city, String state, String Zip, String phone, String ssn) 
	{
		setName( name);
		setAddress (address);
		setPhone ( phone);
		setSsn(ssn);
		setCity(city);
		setState(state);
		setZip(Zip);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String toString() {
		return  "\n" +getName() ;
	}

}
