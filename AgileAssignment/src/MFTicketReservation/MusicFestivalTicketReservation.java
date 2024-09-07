package MFTicketReservation;

import java.util.Scanner;

public class MusicFestivalTicketReservation {

	public static void main(String[] args) {
		System.out.println("Welcome to the Music Festival Ticket Reservation System!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// Register or login
		int customer_id = handleUserRegistrationOrLogin();
			
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
        if (customer_id != -1) {
            Menu(customer_id);
        } else {
            System.out.println("An error occurred during registration or login. Please try again.");
        }
	}

	// Method to handle user registration or login
	private static int handleUserRegistrationOrLogin() {
		Scanner scanner = new Scanner(System.in);
		int customer_id = -1;

		System.out.println("Are you already registered?");
		System.out.println("1. Yes");
		System.out.println("2. No");

		int choice = scanner.nextInt();

		if (choice == 2) {
			System.out.println("Please register to continue.");
			customer_id = UserRegistration.registerCustomer();
		} else if (choice == 1) {
			System.out.println("Please login to continue.");
			customer_id = UserLogin.loginUser();
		} else {
			System.out.println("Invalid choice. Please try again.");
		}

		return customer_id;
	}

	// Method to display the menu and handle user choices
	private static void Menu(int customer_id) {
		Scanner scanner = new Scanner(System.in);
		boolean exitMenu = false;

		while (!exitMenu) {
			System.out.println("Menu:");
			System.out.println("1. Browse Events");
			System.out.println("2. View Reservations");
			System.out.println("3. Exit");

			System.out.print("Enter your choice: ");
			int menuChoice = scanner.nextInt();

			switch (menuChoice) {
			case 1:
				EventSelection.displayEvents();
				EventSelection.selectEvent(customer_id);
				break;
			case 2:
				ViewReservation.viewCustomerReservations(customer_id);
				break;
			case 3:
				System.out.println("Goodbye. Thank you for using our Music Festival Ticket Reservation System!");
				exitMenu = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}

		scanner.close();
	}

}
