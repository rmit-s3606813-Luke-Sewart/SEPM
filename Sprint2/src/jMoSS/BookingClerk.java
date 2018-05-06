package jMoSS;

import java.util.Scanner;

public class BookingClerk extends User {

	@Override
	public Integer displayOptions() {
		Boolean validOption = false;
		Integer input = 0;
		Scanner s = new Scanner(System.in);
		while(validOption == false)
		{
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println("    Booking Clerk");
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println("1. Test showing sessions");
			System.out.println("--------------------------------------------------");
			System.out.println("Please enter the associated number of desired action:");
			input = s.nextInt();
			if(input > 0 && input < 2)
				validOption = true;
			else
				System.out.println("invalid option");
				System.out.println("--------------------------------------------------");
		}
		return input;
	}
	
}
