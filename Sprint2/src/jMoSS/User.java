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
			System.out.println("What is the name of the movie you wish to make a booking for?");
			String input = sc.nextLine();
			for(Movie movie: theatre.getMovies())
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
	
	
	public void addNewMovie(ArrayList<Theatre> theatres) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the name of the new movie?");
		String input = sc.nextLine();
		Movie movie = new Movie(input);
		for(Theatre theatre: theatres)
		{
			theatre.setMovie(movie);
		}
		/*
		try {
			// gets path
			Path path = Paths.get("/Users/markdidio/Documents/workspace/SEPM/src/jMoSS/movies.txt");

			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

			// creates new line to append
			int position = lines.size();
			
			Movie movie = new Movie(String.format("M%04d", lines.size() + 1), input);
			String extraLine = String.format("%s;%s", movie.getId(), movie.getMovieName());

			// writes the to txt file
			lines.add(position, extraLine);
			Files.write(path, lines, StandardCharsets.UTF_8);

			allMovies.add(movie);
			System.out.printf("%s was successfully created.", input);

		} catch (IOException ex) {
			System.out.println("Error");
		}
		*/
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
