package TicketReservationTDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MFTicketReservation.database_connection;

public class manageEvents {
	
//	public static int checkTicketAvailability(int ticketId) {
//	    // Simulate database check
//	    if (ticketId == 999) {
//	        return 0;  // Ticket not found
//	    }
//	    return 50;  // Existing ticket
//	}
	public static int checkTicketAvailability(int ticketId) {
	    Connection connection = database_connection.connect();
	    int availability = 0;

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

	    return availability;  // Returns 0 if ticket ID doesn't exist or availability from DB
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
    
}
