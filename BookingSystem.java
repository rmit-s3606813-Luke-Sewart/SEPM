package jMoSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
	
	static User currentUser = null;
	static String separator = "------------------------------------------------";
	static File userInformation;
	static ArrayList<Movie> movies;
	static User admin = new Admin();
	static User bookingClerk = new BookingClerk();
	static Movie dora = new Movie("Dora The Explorer");
	static MovieSession doraSession = new MovieSession(dora, "20/4", "10:30AM");
	static Movie mov1  = new Movie("Dora Strikes Back");
	static MovieSession mov1Session1 = new MovieSession(mov1, "21/4", "12:30AM");
	
	public static void main(String[] args){
		//to compile will have to change the file path here
		//eventually we will be hosting files on an external server
		userInformation = new File("C:\\Scratch\\jMoSS\\src\\jMoSS\\usersAndPasswords.txt");
		//below will eventually have to load the movies in from a file
		movies = new ArrayList<Movie>();
		movies.add(dora);
		movies.get(0).sessions.add(doraSession);
		movies.add(mov1);
		movies.get(1).sessions.add(mov1Session1);
		System.out.println("Welcome to jMoSS movie search and booking system");
		System.out.println(separator);
		while(currentUser == null)
		{
			login();
		}
		System.out.println(separator);
		if(currentUser.getClass() == Admin.class)
		{
			adminHandler(currentUser);
		}
	}

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
			String role = f.next();
			if(userID.equals(user) && password.equals(pass))
			{
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
		case 5:	System.out.println("not yet implemented....");
				break;
		case 6:	admin.searchMovie(movies);
				break;
		case 7:	System.out.println("not yet implemented....");
				break;
		case 8:	System.out.println("not yet implemented....");
				break;
		case 9: logout = true;
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
}

