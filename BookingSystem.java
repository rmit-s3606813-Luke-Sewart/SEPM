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
	
	
	public static void main(String[] args){
		//to compile will have to change the file path here
		//eventually we will be hosting files on an external server
		userInformation = new File("C:\\Scratch\\jMoSS\\src\\jMoSS\\usersAndPasswords.txt");
		//below will eventually have to load the movies in from a file
		movies = new ArrayList<Movie>();
		movies.add(dora);
		movies.get(0).sessions.add(doraSession);
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
		admin.displayOptions();
		admin.makeBooking(movies);
	}
}

