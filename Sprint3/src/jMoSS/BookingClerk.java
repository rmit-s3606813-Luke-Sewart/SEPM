package jMoSS;

import java.util.Scanner;

public class BookingClerk extends User {
	
	
	//prints the menu for the booking clerk
	@Override
	public Integer displayOptions() {
		Boolean validOption = false;
		Integer input = 0;
		Scanner s = new Scanner(System.in);
		while(validOption == false)
		{
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("    BOOKING CLERK");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("1. Make Booking");
			System.out.println("2. Delete Booking");
			System.out.println("3. Display movie sessions and times");
			System.out.println("4. Search by Movie");
			System.out.println("5. Search by / Change Theatre");
			System.out.println("6. Search by Customer Email");
			System.out.println("7. Logout");
			System.out.println("8. Logout And Quit");
			System.out.println("--------------------------------------------------");
			System.out.println("Please enter the associated number of desired action:");
		
			while (!s.hasNextInt()) {
				String wrongInput = s.next();
				System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
			}
			
			input = s.nextInt();
			if(input > 0 && input < 9)
				validOption = true;
			else
				System.out.println("invalid option");
				System.out.println("--------------------------------------------------");
		}
		return input;
	}
	
}
