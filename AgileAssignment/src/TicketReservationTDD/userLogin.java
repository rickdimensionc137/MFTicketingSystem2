package TicketReservationTDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MFTicketReservation.database_connection;

public class userLogin {

//	public static int loginUser(String email, String password) {
//		if (email.equals("roshansrk@yahoo.com") & password.equals("c@t$1234")) {
//			return 1000;
//		}
//		return -1;
//	}
	
	public static int loginUser(String email, String password) {
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
