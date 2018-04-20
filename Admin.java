package jMoSS;

import java.util.Scanner;

public class Admin extends User {
	
	@Override
	public Integer displayOptions() {
		Boolean validOption = false;
		Integer input = 0;
		Scanner s = new Scanner(System.in);
		while(validOption == false)
		{
			System.out.println("As an admin you can execute the following commands:");
			System.out.println("1. Make Booking");
			System.out.println("2. Delete Booking");
			System.out.println("3. Add Movie");
			System.out.println("4. Delete Movie");
			System.out.println("5. Display movie sessions and times");
			System.out.println("6. Search by Movie");
			System.out.println("7. Search by theatre");
			System.out.println("8. Search by customer email");
			System.out.println("9. logout");
			System.out.println("--------------------------------------------------");
			System.out.println("Please enter the associated number of desired action:");
			input = s.nextInt();
			if(input > 0 && input < 10)
				validOption = true;
			else
				System.out.println("invalid option");
				System.out.println("--------------------------------------------------");
		}
		return input;
	}
}
