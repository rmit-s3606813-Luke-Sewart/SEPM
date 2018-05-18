package jMoSS;


import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
	
	//method called for the user to make a booking
	//takes theatre as parameter in order to display movies for current Theatre selected
	public void makeBooking(Theatre theatre)
	{	
		
		Scanner sc = new Scanner(System.in);
		Movie bookingMovie = null;
		//loops while the movie has not been selected
		while(bookingMovie == null)
		{
			System.out.println("Choose a movie: (or 'r' to return)");
			
			for (int i = 0; i < theatre.getMovies().size(); i++) {
				System.out.printf("%d. %s\n", i + 1, theatre.getMovies().get(i).getMovieName());
			}
			
			while (!sc.hasNextInt()) {
				String wrongInput = sc.next();
				if(wrongInput.equals("r"))
				{
					return;
				}
				else
				{
					System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
				}
			}
			
			int input = sc.nextInt();
			if(input <=  theatre.getMovies().size() && input >= 1)
			{
				bookingMovie = theatre.getMovies().get(input - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		MovieSession movieSession = null;
		
		//loops while movie session has not been selected
		while (movieSession == null) {
			System.out.printf("Following sessions are available for %s:\n", bookingMovie.getMovieName());
			for (int i = 0; i < bookingMovie.getSessions().size(); i++) {
				System.out.printf("%d. %s (%d)\n", i + 1, bookingMovie.getSessions().get(i), 
						bookingMovie.getSessions().get(i).getBookings().size());
			}
			System.out.println("Please input session number: (or 'r' to return)");
			
			while (!sc.hasNextInt()) {
				String wrongInput = sc.next();
				if(wrongInput.equals("r"))
				{
					return;
				}
				else
				{
					System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
				}
			}
			
			int sessionNumber = sc.nextInt();
			if (sessionNumber <= bookingMovie.getSessions().size() && sessionNumber >= 1) {
				movieSession = bookingMovie.getSessions().get(sessionNumber - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		//gets customer email
		sc.nextLine();
		System.out.println("What is customer email?(or 'r' to return)");
		
		String email = sc.nextLine();
		if(email.equals("r"))
			return;
		System.out.println("What is customer suburb?(or 'r' to return)");
		  //gets customer suburb
		String suburb = sc.nextLine();
		if(suburb.equals("r"))
			return;
		boolean test = false;
		
		//confirmation of booking completed here
		while (test == false) {
			System.out.printf("Confirm booking for %s on %s? (Y/N)\n", bookingMovie.getMovieName(), movieSession.toString());
			String input = sc.nextLine();
			
			if (input.equalsIgnoreCase("Y")) {
				
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
				
			} else if (input.equalsIgnoreCase("N")) {
				break;
			} else {
				System.out.print("Invalid input, try again.\n");
				continue;
			}
		}	
	}
	
	//method to remove a booking
	//takes theatre as parameter
	public void removeBooking(Theatre theatre)
	{	
		Scanner sc = new Scanner(System.in);
		Movie bookedMovie = null;
		
		//loops while movie has not been selected
		while(bookedMovie == null)
		{
			System.out.println("Choose the movie you wish to remove the booking from:(or 'r' to return)");
			
			for (int i = 0; i < theatre.getMovies().size(); i++) {
				System.out.printf("%d. %s\n", i + 1, theatre.getMovies().get(i).getMovieName());
			}
			
			while (!sc.hasNextInt()) {
				String wrongInput = sc.next();
				if(wrongInput.equals("r"))
				{
					return;
				}
				else
				{
					System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
				}
			}
			
			int input = sc.nextInt();
			if(input <=  theatre.getMovies().size() && input >= 1)
			{
				bookedMovie = theatre.getMovies().get(input - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		MovieSession movieSession = null;
		
		//loops while movieSession has not been selected
		while (movieSession == null) {
			System.out.printf("Choose the session for %s you wish to remove the booking from:(or 'r' to return)\n", 
					bookedMovie.getMovieName());
			for (int i = 0; i < bookedMovie.getSessions().size(); i++) {
				System.out.printf("%d. %s (%d)\n", i + 1, bookedMovie.getSessions().get(i), 
						bookedMovie.getSessions().get(i).getBookings().size());
			}
			System.out.println("Please input session number:");
			
			while (!sc.hasNextInt()) {
				String wrongInput = sc.next();
				if(wrongInput.equals("r"))
				{
					return;
				}
				else
				{
					System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
				}
			}
			
			int sessionNumber = sc.nextInt();
			if (sessionNumber <= bookedMovie.getSessions().size() && sessionNumber >= 1) {
				movieSession = bookedMovie.getSessions().get(sessionNumber - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		sc.nextLine();
		System.out.println("What is customer email?(or 'r' to return)");
		String email = sc.nextLine();
		if(email.equals("r"))
		{
			return;
		}
		for (Booking booking : movieSession.getBookings()) {
			if (booking.getEmail().equalsIgnoreCase(email)) {
				if (movieSession.getBookings().remove(booking)) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("     The booking has been removed    ");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					return;
				}
			}
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("         Email was not found...       ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	}
	
	//method called in order to add new movie to current theatre array
	public void addNewMovie(Theatre theatre) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the name of the new movie? (or 'r' to return)");
		String input = sc.nextLine();
		if(input.equals("r"))
		{
			return;
		}
		Movie movie = new Movie(input);
		theatre.setMovie(movie);
	}
	
	//method called in order to remove a movie for current theatre array
	public void removeMovie(Theatre theatre) {
		
		Scanner sc = new Scanner(System.in);
		Movie movieToRemove = null;
		
		while(movieToRemove == null)
		{
			System.out.println("What is the name of the movie you want to remove? (or 'r' to return)");
			
			for (int i = 0; i < theatre.getMovies().size(); i++) {
				System.out.printf("%d. %s\n", i + 1, theatre.getMovies().get(i).getMovieName());
			}
			
			while (!sc.hasNextInt()) {
				String wrongInput = sc.next();
				if(wrongInput.equals("r"))
				{
					return;
				}
				System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
			}
			
			int input = sc.nextInt();
			if(input <=  theatre.getMovies().size() && input >= 1)
			{
				movieToRemove = theatre.getMovies().get(input - 1);
			} else {
				System.out.println("Invalid input, try again");
			}
		}
		
		sc.nextLine();
		boolean test = false;
		while (test == false) {
			System.out.printf("Are you sure you want to delete %s and all its sessions at %s? (Y/N)\n", 
					movieToRemove.getMovieName(), theatre.getLocation());
			String input = sc.nextLine();
			
			if (input.equalsIgnoreCase("Y")) {
				theatre.getMovies().remove(movieToRemove);
				break;
			} else if (input.equalsIgnoreCase("N")) {
				break;
			} else {
				System.out.print("Invalid input, try again.\n");
				continue;
			}
		}	
	}
	
	//method called to search for a movies through the current theatres array of movies
	public void searchMovie(ArrayList<Movie> movies)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the name of the movie: (or 'r' to return)");
		String name = sc.nextLine();
		if(name.equals("r"))
		{
			return;
		}
		for(Movie i : movies)
		{
			if(name.equalsIgnoreCase(i.getMovieName()))
			{
				System.out.println("Movie has been found with the following sessions are available:");
				displaySessions(i);
				return;
			}
		}
		System.out.println("Movie has not been found");
	}
	
	//method called to search for sessions at other theatres
	//returns a theatre object that can be used to change the current theatre
	public Theatre searchTheatre(ArrayList<Theatre> theatres, Theatre currTheatre)
	{
		Theatre t = currTheatre;
		for (int i = 0; i < theatres.size(); i++) {
			System.out.printf("%d. %s\n", i + 1, theatres.get(i).getLocation());
		}
		
		Boolean validOption = false;
		Integer input = 0;
		Scanner s = new Scanner(System.in);
		
		
		while(validOption == false)
		{
			System.out.println("Please enter the associated number of desired theatre: (or 'r' to return)");
			
			while (!s.hasNextInt()) {
				String wrongInput = s.next();
				if(wrongInput.equals("r"))
				{
					return t;
				}
				System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
			}
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
			System.out.println();
			System.out.println("Please enter the associated number of desired movie: (or 'r' to return)");
			while (!s.hasNextInt()) {
				String wrongInput = s.next();
				if(wrongInput.equals("r"))
				{
					return t;
				}
				System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
			}
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
			System.out.printf("%d. %s, number of bookings available: %s\n", i + 1, selectedMovie.getSessions().get(i).toString(), 20 - selectedMovie.getSession(i).getBookings().size());
		}

		Boolean valid = false;
		System.out.println("Would you like to change current theatre to selected theatre? (Y/N):");
		while(valid == false)
		{
			String input2 = "N";
			input2 = s.nextLine();
			if(input2.equals("y") || input2.equals("Y"))
			{
				currTheatre = selectedTheatre;
				System.out.println("Current Theatre has been changed to " + currTheatre.getLocation());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				return currTheatre;
			}
			if(input2.equals("N") || input2.equals("n"))
			{
				return currTheatre;
			}
		}
		return currTheatre;
	}
	
	//method called to search by email through all bookings that exist and print those made by a customer identified by email
	public void searchEmail(ArrayList<Movie> movies)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter customer email you wish to search: (or 'r' to return)");
		String email = sc.nextLine();
		if(email.equals("r"))
		{
			return;
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Following bookings found:");
		int count = 0;
		for(Movie movie: movies)
		{
			for(MovieSession session : movie.getSessions())
			{
				for(Booking booking : session.getBookings())
				{
					if(email.equals(booking.customerEmail))
					{
						System.out.println(movie.getMovieName() + ", " + session);
						count++;
					}
				}
			}
		}
		if(count == 0)
		{
			System.out.println("NO BOOKINGS FOUND");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	//method called to display the movies
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
	
	//method called to display the sessions of a movie
	public void displaySessions(Movie movie)
	{
		for(MovieSession session: movie.getSessions())
		{
			System.out.println(session);
		}
	}
	
	//method called to add movie sessions to a selected movie
	public void addMovieSession(ArrayList<Movie> movies)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter name of movie you wish to add session for: (or 'r' to return)");
		Movie tarMovie = null;
		String movSearch = sc.nextLine();
		if(movSearch.equals("r"))
		{
			return;
		}
		for(Movie movie : movies)
		{
			if(movie.getMovieName().equals(movSearch))
			{
				tarMovie = movie;
			}
		}
		if(tarMovie != null)
		{
			System.out.println("Please enter date of movie session (DD/MM/YY): (or 'r' to return)");
			String date = sc.nextLine();
			if(date.equals("r"))
			{
				return;
			}
			System.out.println("Please enter time of movie session: (or 'r' to return)");
			String time = sc.nextLine();
			if(time.equals("r"))
			{
				return;
			}
			MovieSession movSession = new MovieSession(date, time);
			tarMovie.addSession(movSession);
			System.out.println("Movie Session has been added!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		else
		{
			System.out.println("Movie could not be found");
		}
	}
	
	//method called to delete a movie session
	public void deleteMovieSession(ArrayList<Movie> movies)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter name of movie you wish to delete session for: (or 'r' to return)");
		Movie tarMovie = null;
		String movSearch = sc.nextLine();
		if(movSearch.equals("r"))
		{
			return;
		}
		for(Movie movie : movies)
		{
			if(movie.getMovieName().equals(movSearch))
			{
				tarMovie = movie;
			}
		}
		if(tarMovie != null)
		{
			int index = 1;
			System.out.println("Following sessions exist for selected movie:");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(MovieSession session: tarMovie.getSessions())
			{
				System.out.print(index + ". ");
				System.out.println(session);
				index++;
			}
			if(index == 1)
			{
				System.out.println("No Sessions found");
				return;
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Please enter associated number of session you want to delete: (or 'r' to return)");
			String input = sc.nextLine();
			if(input.equals("r"))
			{
				return;
			}
			index = Integer.valueOf(input);
			tarMovie.deleteSession(index - 1);
			System.out.println("Movie session has been removed!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		else
		{
			System.out.println("Movie could not be found");
		}
	}
	
	
	public abstract Integer displayOptions();
}
