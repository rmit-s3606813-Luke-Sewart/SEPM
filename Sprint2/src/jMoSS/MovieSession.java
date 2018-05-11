package jMoSS;

import java.util.ArrayList;

public class MovieSession implements java.io.Serializable {
//	Movie movie;
	String date;
	String time;
	ArrayList<Booking> bookings;
	
	public MovieSession(String date, String time)
	{
		this.date = date;
		this.time = time;
		this.bookings = new ArrayList<Booking>();
	}
	
	public Boolean add(Booking booking) {
		if(bookings.size() < 20)
		{
			bookings.add(booking);
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

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	@Override
	public String toString() {
		return String.format("%s at %s", date, time);
	}
	
}
