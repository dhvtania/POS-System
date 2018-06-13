package POSPD;

public abstract class AuthorizedPayment extends Payment
{

	private String authorizationCode;

	public String getAuthorizationCode()
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
	}

	abstract public Boolean isAuthorized();
	

}