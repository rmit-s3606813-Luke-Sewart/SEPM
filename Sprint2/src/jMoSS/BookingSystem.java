package jMoSS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
	
	static File userInformation;
	
	static String separator = "---------------------------------------------------------";
	static String filePath = "files//";
	
	static ArrayList<Theatre> theatres;
	static Theatre currTheatre;
	
	static User currentUser = null;
	static User admin = new Admin();
	static User bookingClerk = new BookingClerk();
	
	public static void main(String[] args){
		userInformation = new File(filePath + "usersAndPasswords.txt");
		//instantiate ArrayList of theatre objects
		theatres = new ArrayList<Theatre>();
		
		//read each theatre object from their associated file (stored in files folder)
		Theatre stKilda = readObject("M001");
		Theatre fitzroy = readObject("M002");
		Theatre melbCBD = readObject("M003");
		Theatre sunshine = readObject("M004");
		Theatre lilydale = readObject("M005");
		
		//BELOW is original instantiation of theatre objects, may come in handy to write whole new objects if we change class attributes
//		Theatre lilydale = new Theatre ("M005", "Lilydale");
//		Theatre sunshine = new Theatre ("M004", "Sunshine");
//		Theatre melbCBD = new Theatre ("M003", "Melbourne CBD");
//		Theatre fitzroy = new Theatre ("M002", "Fitzroy");
//		Theatre stKilda = new Theatre ("M001", "St. Kilda");
		
		theatres.add(stKilda);
		theatres.add(fitzroy);
		theatres.add(melbCBD);
		theatres.add(sunshine);
		theatres.add(lilydale);
		
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
		if(currentUser.getClass() == BookingClerk.class)
		{
			bookingClerkHandler(currentUser);
		}
	}

	//format of usersAndPasswords document is user,password,role
	//roles are generic as specs do not state each user must have their own instantiation of User as far as i am aware
	public static void login()
	{
		currentUser = null;
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
				System.out.println("userID and Password verified please continue\n");
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

		Boolean theatreSelect = false;
		while (theatreSelect == false) {
			System.out.println("Please enter your theatres associated number:");
			for (int i = 0; i < theatres.size(); i++) {
				System.out.printf("%d. %s\n", i + 1, theatres.get(i).getLocation());
			}

			while (!s.hasNextInt()) {
				String wrongInput = s.next();
				System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
			}

			int selectedTheatre = s.nextInt();
			if (selectedTheatre <= theatres.size() && selectedTheatre >= 1) {
				currTheatre = theatres.get(selectedTheatre - 1);
				theatreSelect = true;
			}
		}
	}

	// need to add menu options of add Session, and quit...
	public static void adminHandler(User admin)
	{
		int input = admin.displayOptions();
		Boolean logout = false;
		while (logout == false) {
			switch (input) {
			case 1:
				admin.makeBooking(currTheatre);
				break;
			case 2:
				admin.removeBooking(currTheatre);
				break;
			case 3:
				admin.addNewMovie(currTheatre);
				break;
			case 4:
				admin.removeMovie(currTheatre);
				break;
			case 5:
				admin.displayMovies(currTheatre.movies);
				break;
			case 6: 
				admin.searchMovie(currTheatre.movies);
				break;
			case 7:
				admin.searchTheatre(theatres);
				break;
			case 8:
				System.out.println("not yet implemented....");
				break;
			case 9:
				logout();
				break;
			default:
				break;
			}
			if (logout == false) {
				input = admin.displayOptions();
			}
		}
		
		System.exit(1);
	}
	
	// need to add menu options of add Session, and quit...
		public static void bookingClerkHandler(User clerk)
		{
			int input = clerk.displayOptions();
			Boolean logout = false;
			while(logout == false)
			{
				switch (input)
				{
					case 1: clerk.makeBooking(currTheatre);
						break;
					case 2: clerk.removeBooking(currTheatre);
						break;
					case 3:	clerk.displayMovies(currTheatre.movies);
						break;
					case 4: clerk.searchMovie(currTheatre.movies);
						break;
					case 5:	clerk.searchTheatre(theatres);
						break;
					case 6:	System.out.println("not yet implemented....");
						break;
					case 7: logout();
						break;
					default: 
						break;
			}
			if(logout == false)
			{
				input = clerk.displayOptions();
			}
			}
			
			System.exit(1);
		}

	public static void logout() {
		for(Theatre theatre: theatres)
		{
			writeObject(theatre);
		}
		login();
	}
	
	public static void writeObject(Theatre theatre)
	{
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(filePath + theatre.id + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(theatre);
	         out.close();
	         fileOut.close();
	         //System.out.printf("Serialized data is saved in /files/" + theatre.id + ".ser\n");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static Theatre readObject(String theatreId) {
		Theatre t = null;
		try {
			FileInputStream fileIn = new FileInputStream(filePath + theatreId + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			t = (Theatre) in.readObject();
			in.close();
			fileIn.close();
		}catch(Exception e)
		{
			System.out.printf("Exception caught when reading object %s\n", theatreId);
		}
		//System.out.println(theatreId + "has been read successfully");
		return t;
	}
}

