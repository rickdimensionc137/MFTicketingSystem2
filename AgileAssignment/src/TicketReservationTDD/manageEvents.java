package TicketReservationTDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MFTicketReservation.database_connection;

public class manageEvents {

	public static int checkTicketAvailability(int ticketId) {
	    Connection connection = database_connection.connect();
	    int availability = -1;

		if (connection != null) {
			String querySQL = "SELECT availability FROM Tickets WHERE ticket_id = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setInt(1, ticketId);
				ResultSet resultSet = preparedStatement.executeQuery();

				// Check if a result exists
				if (resultSet.next()) {
					availability = resultSet.getInt("availability");
				} else {
					// Ticket ID doesn't exist, return 0 availability
					availability = 0;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		}
		return availability; 
	}


	public static int updateTicketAvailability(int ticketId) {
		Connection connection = database_connection.connect();

		if (connection != null) {
			String updateSQL = "UPDATE Tickets SET availability = availability - 1 WHERE ticket_id = ? AND availability > 0";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setInt(1, ticketId);
				int rowsUpdated = preparedStatement.executeUpdate();

				if (rowsUpdated > 0) {
					System.out.println("Ticket availability updated successfully.");
				} else {
					System.out.println("Error: Unable to update ticket availability.");
				}
				return rowsUpdated;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();

			}
			return -1;
		}
		return -1;

	}

	public static boolean saveReservation(int eventId, String eventName, int ticketId, int customer_id) {
		Connection connection = database_connection.connect();

		if (connection != null) {
			String insertSQL = "INSERT INTO Reservations (event_id, ticket_id, customer_id) VALUES (?, ?, ?)";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, eventId);
				preparedStatement.setInt(2, ticketId);
				preparedStatement.setInt(3, customer_id);

				int rowsInserted = preparedStatement.executeUpdate();

				if (rowsInserted > 0) {
					System.out.println("Reservation successfully!");
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		} else {
			System.out.println("Unable to connect to the database.");
			return false;
		}
		return false;
	}
//	public static boolean saveReservation(int eventId, String eventName, int ticketId, int customer_id) {
//		if (eventId == 3 && eventName.equals("BTS Special Stage") && ticketId == 11 && customer_id == 1004) {
//			return true;
//		}
//		return false;
//	}


}
