package jMoSS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
	
	public void makeBooking(ArrayList<Movie> movies)
	{	
		//this is coded as best case
		//need to also expand to catch certain errors here
		Scanner sc = new Scanner(System.in);
		Movie bookingMovie = null;
		while(bookingMovie == null)
		{
			System.out.println("What is the name of the movie you wish to make a booking for?");
			String input = sc.nextLine();
			for(Movie movie: movies)
			{
				if(movie.getMovieName().equals(input))
				{
					bookingMovie = movie;
					break;
				}
				
			}
		}
		System.out.println("Following sessions are available for " + bookingMovie.getMovieName() + "");
		int count = 1;
		for(MovieSession session : bookingMovie.getSessions())
		{
			System.out.print(count + ". ");
			System.out.println(session);
			count++;
		}
		System.out.println("Please input session number:");
		String sessionNumber = sc.nextLine();
		System.out.println("What is customer email?");
		String email = sc.nextLine();
		System.out.println("What is customer suburb?");
		String suburb = sc.nextLine();
		Booking booking = new Booking(email, suburb);
		if(bookingMovie.getSession(Integer.valueOf(sessionNumber) - 1).add(booking))
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("The booking has been made successfully");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		else 
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("	  The movie session is full!"	   );
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		
			
	}
	public void searchMovie(ArrayList<Movie> movies)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the name of the movie:");
		String name = sc.nextLine();
		for(Movie i : movies)
		{
			if(name.equals(i.getMovieName()))
			{
				System.out.println("Movie has been found following sessions are available:");
				displaySessions(i);
				return;
			}
		}
		System.out.println("Movie has not been found");
	}
	public void searchTheatre(String theatre, Array[][] theatres)
	{
		//implement here
	}
	public void searchEmail(String email)
	{
		//implement here
	}
	public void displayMovies(ArrayList<Movie> movies) 
	{
		System.out.println("\n~~~~~~~~ MOVIES AND SESSIONS ~~~~~~~~");
		for(Movie movie: movies)
		{
			System.out.println("----------------------------------------");
			System.out.println(movie.getMovieName());
			System.out.println("----------------------------------------");
			for(MovieSession session : movie.getSessions())
			{
				System.out.println(session);
			}
		}
	}
	public void displaySessions(Movie movie)
	{
		for(MovieSession session: movie.getSessions())
		{
			System.out.println(session);
		}
	}
	public abstract Integer displayOptions();
}
