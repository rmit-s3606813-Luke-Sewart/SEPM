package jMoSS;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Theatre implements java.io.Serializable {
	
	String id;
	String location;
	ArrayList<Movie> movies;
	
	public Theatre(String id, String location) {
		this.id = id;
		this.location = location;
		movies = new ArrayList<Movie>();
	}
	
	public String getId() {
		return id;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setMovie(Movie movie) {
		movies.add(movie);
	}
	
	public ArrayList<Movie> getMovies(){
		return movies;
	}
}
