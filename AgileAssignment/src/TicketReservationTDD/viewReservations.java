package TicketReservationTDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MFTicketReservation.database_connection;

public class viewReservations {
	public static boolean viewCustomerReservations(String eventName, String eventDate, String eventTime, String ticketType, double price, int customer_id) {
		Connection connection = database_connection.connect();

        if (connection != null) {
            String querySQL = "SELECT e.event_name, e.event_date, e.event_time, t.ticket_type, t.price "
                            + "FROM Reservations r "
                            + "JOIN Events e ON r.event_id = e.event_id "
                            + "JOIN Tickets t ON r.ticket_id = t.ticket_id "
                            + "WHERE r.customer_id = ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
                preparedStatement.setInt(1, customer_id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) { // No reservations found
                    System.out.println("No reservations found.");
                } else {
                    System.out.println("\nYour Reservations:");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-30s | %-10s | %-8s | %-10s | %-6s |\n", 
                                      "Event Name", "Date", "Time", "Ticket Type", "Price");
                    System.out.println("=====================================================================");

                    while (resultSet.next()) {
                        eventName = resultSet.getString("event_name");
                        eventDate = resultSet.getDate("event_date").toString();
                        eventTime = resultSet.getTime("event_time").toString();
                        ticketType = resultSet.getString("ticket_type");
                        price = resultSet.getDouble("price");

                        System.out.printf("| %-30s | %-10s | %-8s | %-10s | %-6.2f |\n", 
                                          eventName, eventDate, eventTime, ticketType, price);
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database_connection.disconnect();
            }
            return true;
        }
		return false;
	}
        

}
