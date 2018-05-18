package jMoSS;

import java.util.Scanner;

public class Admin extends User {
	
	//prints the menu for the administrator user
	//returns the users selected task
	@Override
	public Integer displayOptions() {
		Boolean validOption = false;
		Integer input = 0;
		Scanner s = new Scanner(System.in);
		while(validOption == false)
		{
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println("    ADMIN");
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println("1. Make Booking");
			System.out.println("2. Delete Booking");
			System.out.println("3. Add Movie");
			System.out.println("4. Delete Movie");
			System.out.println("5. Display movie sessions and times");
			System.out.println("6. Add Movie Session");
			System.out.println("7. Delete Movie Session");
			System.out.println("8. Search by Movie");
			System.out.println("9. Search by / Change Theatre");
			System.out.println("10. Search by Customer Email");
			System.out.println("11. Logout");
			System.out.println("12. Logout And Quit");
			System.out.println("--------------------------------------------------");
			System.out.println("Please enter the associated number of desired action:");
			
			while (!s.hasNextInt()) {
				String wrongInput = s.next();
				System.out.printf("\"%s\" is not a valid number.\n", wrongInput);
			}
			
			input = s.nextInt();
			if(input > 0 && input < 13)
				validOption = true;
			else
				System.out.println("invalid option");
				System.out.println("--------------------------------------------------");
		}
		return input;
	}
}
