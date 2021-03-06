package jMoSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
	
	static User currentUser = null;
	static String separator = "---------------------------------------------------------";
	static File userInformation;
	static ArrayList<Movie> movies;
	static User admin = new Admin();
	static User bookingClerk = new BookingClerk();
	static Movie dora = new Movie("Dora The Explorer");
	static MovieSession doraSession = new MovieSession(dora, "20/4", "10:30AM");
	static MovieSession doraSession2 = new MovieSession(dora, "20/4", "12:30AM");
	static MovieSession doraSession3 = new MovieSession(dora, "20/4", "2:30PM");
	static Movie mov1  = new Movie("Dora Strikes Back");
	static MovieSession mov1Session1 = new MovieSession(mov1, "21/4", "12:30AM");
	static MovieSession mov1Session2 = new MovieSession(mov1, "21/4", "2:30PM");
	static MovieSession mov1Session3 = new MovieSession(mov1, "21/4", "4:30PM");
	
	public static void main(String[] args){
		//to compile will have to change the file path here
		//eventually we will be hosting files on an external server
		userInformation = new File("C:\\Users\\lukes\\Desktop\\Uni\\Software Engineering Project Management\\Assignment2\\SEPM-master\\src\\usersAndPasswords.txt");
		//below will eventually have to load the movies in from a file
		movies = new ArrayList<Movie>();
		addMoviesTemporary();
		Theatre stKilda = new Theatre("St.Kilda", movies);
		Theatre fitzroy = new Theatre ("Fitzroy", movies);
		Theatre melbCBD = new Theatre ("Melbourne CBD", movies);
		Theatre sunshine = new Theatre ("Sunshine", movies);
		Theatre lilydale = new Theatre ("Lilydale", movies);
		System.out.println("Welcome to jMoSS movie search and booking system");
		System.out.println(separator);
		while(currentUser == null)
		{
			login();
		}
		if(currentUser.getClass() == Admin.class)
		{
			adminHandler(currentUser);
		}
	}

	//format of usersAndPasswords document is user,password,role
	//roles are generic as specs do not state each user must have their own instantiation of User as far as i am aware
	public static void login()
	{
		Scanner s = new Scanner(System.in);
		Scanner f = null;
		try {
			f = new Scanner(userInformation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter your userID to continue:");
		String userID = s.nextLine();
		System.out.println("Please enter password:");
		String password = s.nextLine();
		while(f.hasNext())
		{
			f.useDelimiter(",");
			String user = f.next();
			String pass = f.next();
			String role = f.nextLine();
			role = role.replaceFirst("\\,", "");
			// System.out.println(String.format("User: %s Username: %s Pass: %s  Password: %s Role: %s", user, userID, pass, password, role));
			if(userID.equals(user) && password.equals(pass))
			{
				System.out.println(separator);
				System.out.println("userID and Password verified please continue");
				if(role.equals("admin"))
				{
					currentUser = admin;
				}
				else if(role.equals("bookingclerk"))
				{
					currentUser = bookingClerk;
				}
				
			}
		}	
		if(currentUser == null)
		{
			System.out.println(separator);
			System.out.println("Incorrect username and password please try again");
			System.out.println(separator);
		}
	}
	public static void adminHandler(User admin)
	{
		int input = admin.displayOptions();
		Boolean logout = false;
		while(logout == false)
		{
			switch (input)
			{
				case 1: admin.makeBooking(movies);
					break;
				case 2: System.out.println("not yet implemented....");
					break;
				case 3:	System.out.println("not yet implemented....");
					break;
				case 4:	System.out.println("not yet implemented....");
					break;
				case 5:	admin.displayMovies(movies);;
					break;
				case 6:	admin.searchMovie(movies);
					break;
				case 7:	System.out.println("not yet implemented....");
					break;
				case 8:	System.out.println("not yet implemented....");
					break;
				case 9: logout = true;
						logout();
					break;
				default: 
					break;
		}
		if(logout == false)
		{
			input = admin.displayOptions();
		}
		
		}
		
		System.exit(1);
	}
	public static void addMoviesTemporary()
	{
		movies.add(dora);
		movies.get(0).sessions.add(doraSession);
		movies.get(0).sessions.add(doraSession2);
		movies.get(0).sessions.add(doraSession3);
		movies.add(mov1);
		movies.get(1).sessions.add(mov1Session1);
		movies.get(1).sessions.add(mov1Session2);
		movies.get(1).sessions.add(mov1Session3);
	}
	public static void logout() {
		login();
	}
}

