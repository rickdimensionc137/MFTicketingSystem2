package MFTicketReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserRegistration {

	// email validation
	public static boolean isValidEmail(String email) {
		return Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches();
	}

	// customer name validation (alphabets only, 2-25 characters)
	public static boolean isValidName(String name) {
		return Pattern.compile("^[A-Za-z][A-Za-z ]{1,24}$").matcher(name).matches();
	}

	// password validation (at least 8 characters, at least one digit, and one special character)
	public static boolean isValidPassword(String password) {
		return Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$").matcher(password).matches();
	}

	// Method to register a customer
	public static int registerCustomer() {
		Scanner scanner = new Scanner(System.in);
		int customerId = -1; // Default value if registration fails

		String name = "";
		String email = "";
		String password = "";

		// Input validation loop for name
		do {
			System.out.print("Enter your name : ");
			name = scanner.nextLine();
			if (name.isEmpty()) {
				System.out.println("Name cannot be empty. Please try again.");
			} else if (!isValidName(name)) {
				System.out.println("Invalid name. It must be 2-25 characters long and can only contain alphabets.");
			}
		} while (!isValidName(name));

		// Input validation loop for email
		do {
			System.out.print("Enter your email: ");
			email = scanner.nextLine();
			if (email.isEmpty()) {
				System.out.println("Email cannot be empty. Please try again.");
			} else if (!isValidEmail(email)) {
				System.out.println("Invalid email format. Please enter a valid email address.");
			}
		} while (!isValidEmail(email));

		// Input validation loop for password
		do {
			System.out.print(
					"Enter your password (minimum 8 characters, at least one digit, and one special character): ");
			password = scanner.nextLine();
			if (password.isEmpty()) {
				System.out.println("Password cannot be empty. Please try again.");
			} else if (password.length() < 8) {
				System.out.println("Password must be at least 8 characters long. Please try again.");
			} else if (!Pattern.compile("(?=.*[0-9])").matcher(password).find()) {
				System.out.println("Password must contain at least one digit. Please try again.");
			} else if (!Pattern.compile("(?=.*[!@#$%^&*])").matcher(password).find()) {
				System.out.println("Password must contain at least one special character. Please try again.");
			} else if (!isValidPassword(password)) {
				System.out.println("Password does not meet the required criteria. Please try again.");
			}
		} while (!isValidPassword(password));


		// Check if email already exists before saving customer data
		if (emailExists(email)) {
			System.out.println("Email already in use.");
		} else {
			// Insert customer data into the database
			customerId = saveCustomerToDatabase(name, email, password);
		}
		return customerId;
	}

	// Method to check if an email already exists in the database
	public static boolean emailExists(String email) {
		Connection connection = database_connection.connect();

		if (connection != null) {
			String querySQL = "SELECT COUNT(*) FROM customer WHERE email = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setString(1, email);

				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					return resultSet.getInt(1) > 0; // Return true if count > 0
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		}

		return false; // Email does not exist
	}

	// Method to save customer data to the database
	public static int saveCustomerToDatabase(String name, String email, String password) {
	    Connection connection = database_connection.connect();
	    int customerId = -1; // Default value if insertion fails

	    if (connection != null) {
	        String insertSQL = "INSERT INTO customer (customer_name, email, password) VALUES (?, ?, ?)";
	        String querySQL = "SELECT customer_id FROM customer WHERE email = ?";

	        try {
	            // Insert customer data
	            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
	            preparedStatement.setString(1, name);
	            preparedStatement.setString(2, email);
	            preparedStatement.setString(3, password);

	            int rowsInserted = preparedStatement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Registration successful!");

	                // Retrieve the customer_id using the email
	                preparedStatement = connection.prepareStatement(querySQL);
	                preparedStatement.setString(1, email);
	                ResultSet resultSet = preparedStatement.executeQuery();

	                if (resultSet.next()) {
	                    customerId = resultSet.getInt("customer_id");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            database_connection.disconnect();
	        }
	    }
	    
	    return customerId;
	}


}
