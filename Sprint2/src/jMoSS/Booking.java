package jMoSS;

public class Booking implements java.io.Serializable {
	String customerEmail;
	String suburb;

	public Booking(String email, String suburb)
	{
		customerEmail = email;
		this.suburb = suburb;
	}
	
	public String getEmail() {
		return customerEmail;
	}
	
	public String getSuburb() {
		return suburb;
	}
	
}
