package jMoSS;

import java.util.ArrayList;

public class Movie {
	String movieName;
	ArrayList<MovieSession> sessions;
	public Movie(String movieName)
	{
		this.movieName = movieName;
		sessions = new ArrayList<MovieSession>();
	}
	public String getMovieName() {
		return movieName;
	}
	public ArrayList<MovieSession> getSessions() {
		return sessions;
	}
	public MovieSession getSession(int index) {
		return sessions.get(index);
	}
	
}
