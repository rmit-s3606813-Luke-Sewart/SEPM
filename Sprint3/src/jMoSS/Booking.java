package jMoSS;

@SuppressWarnings("serial")
public class Booking implements java.io.Serializable {
	
	String customerEmail;
	String suburb;
	//class is a booking, is held in MovieSession as part of an array
	
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
