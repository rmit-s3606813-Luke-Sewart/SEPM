package jMoSS;

public class MovieSession implements java.io.Serializable {
//	Movie movie;
	String date;
	String time;
	Booking[] bookings;
	int count = 0;
	
	public MovieSession(String date, String time)
	{
//		this.movie = movie;
		this.date = date;
		this.time = time;
		bookings = new Booking[20];
	}
	
	public Boolean add(Booking booking) {
		if(count < 20)
		{
			bookings[count] = booking;
			return true;
		}
		else
			return false;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public Booking[] getBookings() {
		return bookings;
	}

	@Override
	public String toString() {
		return String.format("%s at %s", date, time);
	}
	
}
