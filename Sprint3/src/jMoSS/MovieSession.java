package jMoSS;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class MovieSession implements java.io.Serializable {
	
	//class is instance of movie session, is held in Movie as an array and contains bookings
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
		if (bookings.size() < 20) {
			bookings.add(booking);
			return true;
		} else {
			return false;
		}
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
