package jMoSS;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User {
	
	public void makeBooking(Theatre theatre)
	{	
		//this is coded as best case
		//need to also expand to catch certain errors here
		Scanner sc = new Scanner(System.in);
		Movie bookingMovie = null;
		while(bookingMovie == null)
		{
			System.out.println("Choose a movie:");
			
			for (int i = 0; i < theatre.getMovies().size(); i++) {
				System.out.printf("%d. %s\n", i + 1, theatre.getMovies().get(i).getMovieName());
			}
			
			String input = sc.nextLine();
			if(Integer.valueOf(input) <=  theatre.getMovies().size() && Integer.valueOf(input) >= 1)
			{
				bookingMovie = theatre.getMovies().get(Integer.valueOf(input) - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		MovieSession movieSession = null;
		while (movieSession == null) {
			System.out.printf("Following sessions are available for %s:\n", bookingMovie.getMovieName());
			for (int i = 0; i < bookingMovie.getSessions().size(); i++) {
				System.out.printf("%d. %s (%d)\n", i + 1, bookingMovie.getSessions().get(i), 
						bookingMovie.getSessions().get(i).getBookings().size());
			}
			System.out.println("Please input session number:");
			String sessionNumber = sc.nextLine();
			if (Integer.valueOf(sessionNumber) <= bookingMovie.getSessions().size() && Integer.valueOf(sessionNumber) >= 1) {
				movieSession = bookingMovie.getSessions().get(Integer.valueOf(sessionNumber) - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		System.out.println("What is customer email?");
		String email = sc.nextLine();
		System.out.println("What is customer suburb?");
		String suburb = sc.nextLine();
		
		boolean test = false;
		while (test == false) {
			System.out.printf("Confirm booking for %s on %s? (Y/N)\n", bookingMovie.getMovieName(), movieSession.toString());
			String input = sc.nextLine();
			
			if ((input.equals("Y")) || (input.equals("y"))) {
				
				Booking booking = new Booking(email, suburb);
				if(movieSession.add(booking))
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
				break;
				
			} else if ((input.equals("N")) || (input.equals("n"))) {
				break;
			} else {
				System.out.print("Invalid input, try again.\n");
				continue;
			}
		}	
	}
	
	
	public void addNewMovie(Theatre theatre) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the name of the new movie?");
		String input = sc.nextLine();
		Movie movie = new Movie(input);
		theatre.setMovie(movie);
		addSessions(movie);
		
	/*	while (test == false) {
			System.out.printf("Add a session for %s (Y/N)\n", movie.getMovieName());
			String input = sc.nextLine();
			
			if ((input.equals("Y")) || (input.equals("y"))) {
				test = true;
			} else if ((input.equals("N")) || (input.equals("n"))) {
				return;
			} else {
				System.out.print("Invalid input, try again.\n");
			}
		}*/
		
	}
	
	public void addSessions(Movie movie) {
		Scanner sc = new Scanner(System.in);
		boolean doAgain = true;
		boolean test = false;
		
		System.out.printf("Add a session for %s\n", movie.getMovieName());
		while (doAgain == true) {
			System.out.print("Date: (dd/mm/yyyy)\n");
			String dateString = sc.nextLine();
			System.out.print("24-Hour Time: (HH:mm)\n");
			String timeString = sc.nextLine();

			MovieSession movieSession = new MovieSession(dateString, timeString);
			movie.addSession(movieSession);
			
			while (test == false) {
				System.out.printf("Add another session for %s? (Y/N)\n", movie.getMovieName());
				String input = sc.nextLine();
				
				if ((input.equals("Y")) || (input.equals("y"))) {
					doAgain = true;
					break;
				} else if ((input.equals("N")) || (input.equals("n"))) {
					doAgain = false;
					break;
				} else {
					System.out.print("Invalid input, try again.\n");
					continue;
				}
			}
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
	
	public void searchTheatre(ArrayList<Theatre> theatres)
	{
		for (int i = 0; i < theatres.size(); i++) {
			System.out.printf("%d. %s\n", i + 1, theatres.get(i).getLocation());
		}
		
		Boolean validOption = false;
		Integer input = 0;
		Scanner s = new Scanner(System.in);
		while(validOption == false)
		{
			System.out.println("Please enter the associated number of desired theatre:");
			input = s.nextInt();
			if(input > 0 && input <= theatres.size())
				validOption = true;
			else
				System.out.println("invalid option");
				System.out.println("--------------------------------------------------");
		}
		
		Theatre selectedTheatre = theatres.get(input - 1);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(selectedTheatre.getLocation());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for (int i = 0; i < selectedTheatre.getMovies().size(); i++) {
			System.out.printf("%d. %s\n", i + 1, selectedTheatre.getMovies().get(i).getMovieName());
		}
		
		validOption = false;
		while(validOption == false)
		{
			System.out.println("Please enter the associated number of desired movie:");
			input = s.nextInt();
			if(input > 0 && input <= selectedTheatre.getMovies().size())
				validOption = true;
			else
				System.out.println("invalid option");
				System.out.println("--------------------------------------------------");
		}
		
		Movie selectedMovie = selectedTheatre.getMovies().get(input - 1);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(selectedMovie.getMovieName());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for (int i = 0; i < selectedMovie.getSessions().size(); i++) {
			System.out.printf("%d. %s\n", i + 1, selectedMovie.getSessions().get(i).toString());
		}
		
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
