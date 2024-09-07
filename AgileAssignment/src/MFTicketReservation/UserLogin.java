package MFTicketReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserLogin {
	// Method to check user credentials and log in
	public static int loginUser() {
		Scanner input = new Scanner(System.in);
		int customerId = -1; // Default value if login fails
		boolean isLoggedIn = false;

		while (!isLoggedIn) {
			System.out.print("Enter your email: ");
			String email = input.nextLine();

			System.out.print("Enter your password: ");
			String password = input.nextLine();

			// Check credentials against the database
	        customerId = validateCredentials(email, password); 
	        
			// Check credentials against the database
			if (customerId!= -1) {
				System.out.println("Login successful!");
				isLoggedIn = true; // Exit the loop
			} else {
				System.out.println("Invalid email or password. Please try again.");
			}
		}
		return customerId;
	}

	// Method to validate credentials against the database
	private static int validateCredentials(String email, String password) {
		Connection connection = database_connection.connect();
		int customerId = -1; // Default value if no matching user is found

		if (connection != null) {
			String querySQL = "SELECT customer_id FROM customer WHERE email = ? AND password = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);

				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					// User exists with the provided email and password
					customerId = resultSet.getInt("customer_id");
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
