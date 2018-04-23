package jMoSS;

import java.util.ArrayList;

public class Theatre {
	String location;
	ArrayList<Movie> movies;
	public Theatre(String location, ArrayList<Movie> movies) {
		this.location = location;
		this.movies = movies;
	}
	
	public ArrayList<Movie> getMovies(){
		return movies;
	}
}
