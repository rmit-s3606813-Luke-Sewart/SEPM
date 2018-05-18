package jMoSS;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Movie implements java.io.Serializable {
	
	//class is instance of a movie, contains movie sessions
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
	
	public void addSession(MovieSession movieSession) {
		sessions.add(movieSession);
	}
	
	public void deleteSession(int index)
	{
		sessions.remove(index);
	}
	
	public ArrayList<MovieSession> getSessions() {
		return sessions;
	}
	
	public MovieSession getSession(int index) {
		return sessions.get(index);
	}
	
}
