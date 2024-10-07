package TicketReservationTDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import MFTicketReservation.database_connection;

public class userRegister {

	public static int registerUser(String name, String email, String password) {
		if(Pattern.compile("^[A-Za-z][A-Za-z ]{1,24}$").matcher(name).matches()) {
			if(Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches()) {
				if(Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$").matcher(password).matches()) {
					if(!emailExists(email)) {
						int customer_id = saveCustomerToDatabase(name, email, password);
						return customer_id;
					}
					return -4;
				}
				return -3;
			}
			return -2;
		}		
		return -1;
	}
	
	private static int saveCustomerToDatabase(String name, String email, String password) {
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
	
	private static boolean emailExists(String email) {
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

	public static int processRegisterUser() {
		Scanner scanner = new Scanner(System.in);
		int customerId = -1; // Default value if registration fails

		String name = "";
		String email = "";
		String password = "";
		
		System.out.print("Enter your name : ");
		name = scanner.nextLine();
		
		System.out.print("Enter your email: ");
		email = scanner.nextLine();
		
		System.out.print(
				"Enter your password (minimum 8 characters, at least one digit, and one special character): ");
		password = scanner.nextLine();
		
		customerId = registerUser(name, email, password);
		
		while((customerId == -1) || (customerId == -2)|| (customerId == -3)|| (customerId == -4)){
			if (customerId == -1) {
				System.out.println("Invalid name. Please re-enter: ");
				System.out.print("Enter your name : ");
				name = scanner.nextLine();
				customerId = registerUser(name, email, password);
			} else if(customerId == -2) {
				System.out.println("Invalid email. Please re-enter: ");
				System.out.print("Enter your email : ");
				email = scanner.nextLine();
				customerId = registerUser(name, email, password);
			}else if(customerId == -3) {
				System.out.println("Invalid password. Please re-enter (minimum 8 characters, at least one digit, and one special character): ");
				System.out.print("Enter your password : ");
				password = scanner.nextLine();
				customerId = registerUser(name, email, password);
			}else if (customerId == -4) {
				System.out.println("Email already exists.");
				System.out.print("Enter your name : ");
				name = scanner.nextLine();				
				System.out.print("Enter your email: ");
				email = scanner.nextLine();				
				System.out.print(
						"Enter your password (minimum 8 characters, at least one digit, and one special character): ");
				password = scanner.nextLine();				
				customerId = registerUser(name, email, password);
			}			
		}
		return customerId;
	}
	

}
