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
	                // Ticket ID doesn't exist, return -1 availability
	                availability = -1;
	                System.out.println("Invalid input. Please enter a valid Ticket ID.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            database_connection.disconnect();
	        }
	    }

	    return availability;  // Returns -1 if ticket ID doesn't exist or availability from DB
	}
	
	


}
