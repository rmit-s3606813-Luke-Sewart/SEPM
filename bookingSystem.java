package jMoSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class bookingSystem {
	
	static User currentUser = null;
	static String separator = "------------------------------------------------";
	static File userInformation;
	static User admin = new Admin();
	static User bookingClerk = new BookingClerk();
	public static void main(String[] args){
		//to compile will have to change the file path here
		//eventually we will be hosting files on an external server
		userInformation = new File("C:\\Scratch\\jMoSS\\src\\jMoSS\\usersAndPasswords.txt");
		System.out.println("Welcome to jMoSS movie search and booking system");
		System.out.println(separator);
		while(currentUser == null)
		{
			login();
		}
		System.out.println(separator);
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
		f.useDelimiter(",");
		System.out.println("Please enter your userID to continue:");
		String userID = s.nextLine();
		System.out.println("Please enter password:");
		String password = s.nextLine();
		while(f.hasNext())
		{
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
		s.close();
		f.close();
		
	}
}

