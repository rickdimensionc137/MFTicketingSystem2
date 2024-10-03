package MFTicketReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.events.Event;

public class EventSelection {
	// Method to display available events
	public static void displayEvents() {
		Connection connection = database_connection.connect();

		if (connection != null) {
			String selectSQL = "SELECT * FROM Events";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
				ResultSet resultSet = preparedStatement.executeQuery();

				System.out.println("Available Event(s):");
				System.out.println("=====================================================================");
				System.out.printf("| %-8s | %-30s | %-10s | %-8s |\n", "Event ID", "Event Name", "Date", "Time");
				System.out.println("=====================================================================");

				while (resultSet.next()) {
					int eventId = resultSet.getInt("event_id");
					String eventName = resultSet.getString("event_name");
					String eventDate = resultSet.getDate("event_date").toString();
					String eventTime = resultSet.getTime("event_time").toString();

					// Print formatted event data
					System.out.printf("| %-8d | %-30s | %-10s | %-8s |\n", eventId, eventName, eventDate, eventTime);
				}

				System.out.println("=====================================================================");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		} else {
			System.out.println("Unable to connect to the database.");
		}
	}

	// Method for user to select an event and display event details
	public static void selectEvent(int customer_id) {
		Scanner event_scanner = new Scanner(System.in);
		System.out.print("\nEnter the Event ID to select an event: ");

		if (event_scanner.hasNextInt()) {
			int eventId = event_scanner.nextInt();

			Connection connection = database_connection.connect();
			if (connection != null) {
				String querySQL = "SELECT * FROM Events WHERE event_id = ?";

				try {
					PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
					preparedStatement.setInt(1, eventId);
					ResultSet resultSet = preparedStatement.executeQuery();

					if (resultSet.next()) {
						String eventName = resultSet.getString("event_name");
						String eventDate = resultSet.getDate("event_date").toString();
						String eventTime = resultSet.getTime("event_time").toString();

						// Display the selected event details
						System.out.println("\nYou have selected the following event:");
						System.out.println("==========================================================");
						System.out.printf("| %-30s | %-10s | %-8s |\n", eventName, eventDate, eventTime);
						System.out.println("==========================================================");

						// Display ticket options for the selected event
						displayTicketOptions(eventId, customer_id);
					} else {
						System.out.println("Invalid Event ID. No event found with this ID.");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					database_connection.disconnect();
				}
			} else {
				System.out.println("Unable to connect to the database.");
			}
		} else {
			System.out.println("Invalid input. Please enter a valid Event ID.");
		}

	}

	// Method to display ticket options for the selected event
	private static void displayTicketOptions(int eventId, int customer_id) {
		Connection connection = database_connection.connect();
		if (connection != null) {
			// Get the event name
			String getEventNameSQL = "SELECT event_name FROM Events WHERE event_id = ?";
			String eventName = "";

			try {
				PreparedStatement eventStatement = connection.prepareStatement(getEventNameSQL);
				eventStatement.setInt(1, eventId);
				ResultSet eventResultSet = eventStatement.executeQuery();
				if (eventResultSet.next()) {
					eventName = eventResultSet.getString("event_name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Display ticket options
			String querySQL = "SELECT * FROM Tickets WHERE event_id = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setInt(1, eventId);
				ResultSet resultSet = preparedStatement.executeQuery();

				System.out.println("\nAvailable Ticket Options for " + eventName + ":");
				System.out.println("===================================================================");
				System.out.printf("| %-8s | %-20s | %-10s | %-15s |\n", "Ticket ID", "Ticket Type", "Price($)",
						"Availability");
				System.out.println("==================================================================");

				while (resultSet.next()) {
					int ticketId = resultSet.getInt("ticket_id");
					String ticketType = resultSet.getString("ticket_type");
					double price = resultSet.getDouble("price");
					int availability = resultSet.getInt("availability");

					System.out.printf("| %-8d | %-20s | $%-9.2f | %-15d |\n", ticketId, ticketType, price,
							availability);
				}

				System.out.println("==================================================================");

				// Allow the user to select a ticket
				selectTicket(eventId, eventName, customer_id);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		} else {
			System.out.println("Unable to connect to the database.");
		}
	}

	private static void selectTicket(int eventId, String eventName, int customer_id) {
		Scanner ticketScanner = new Scanner(System.in);
		System.out.print("\nEnter the Ticket ID to select: ");

		if (ticketScanner.hasNextInt()) {
			int ticketId = ticketScanner.nextInt();
			
			// Check ticket availability
	        int availability = checkTicketAvailability(ticketId);
	        
	        if (availability > 0) {
	            System.out.println("You have selected Ticket ID " + ticketId + " for Event: " + eventName);
	            System.out.println("Do you wish to proceed to checkout?");
	            System.out.println("1. Yes");
	            System.out.println("2. No");
	            
	            int checkoutChoice = ticketScanner.nextInt();

			    if (checkoutChoice == 1) {
				   // Proceed with checkout, save the reservation to the database
				   saveReservation(eventId, eventName, ticketId, customer_id);
				   
				   // Decrease ticket availability by 1
	               updateTicketAvailability(ticketId);

				   // Display the reservation details
				   displayReservationDetails(eventId, eventName, ticketId, customer_id);

			    } else if (checkoutChoice == 2) {
				  System.out.println("You have cancelled the reservation.");
			    } else {
				  System.out.println("Invalid choice. Please select 1 for Yes or 2 for No."); 
			    }
	        } else {
	            System.out.println("Sorry, the selected ticket is not available.");
	            System.out.println("Please select another ticket.");
	        }
		 } else {
		        System.out.println("Invalid input. Please enter a valid Ticket ID.");
		    }

	}
	
	// Method to update ticket availability
	private static void updateTicketAvailability(int ticketId) {
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
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            database_connection.disconnect();
	        }
	    }
	}

	// Method to check ticket availability
	private static int checkTicketAvailability(int ticketId) {
		Connection connection = database_connection.connect();
		int availability = 0;

		if (connection != null) {
			String querySQL = "SELECT availability FROM Tickets WHERE ticket_id = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setInt(1, ticketId);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					availability = resultSet.getInt("availability");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		}

		return availability;
	}

	// Method to save the reservation in the database
	private static void saveReservation(int eventId, String eventName, int ticketId, int customer_id) {
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
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		} else {
			System.out.println("Unable to connect to the database.");
		}
	}

	// Method to display reservation details
	private static void displayReservationDetails(int eventId, String eventName, int ticketId, int customer_id) {
		Connection connection = database_connection.connect();

		if (connection != null) {
			String querySQL = "SELECT c.customer_name, e.event_name, e.event_date, e.event_time, t.ticket_type, t.price "
					+ "FROM Reservations r " + "JOIN Events e ON r.event_id = e.event_id "
					+ "JOIN Tickets t ON r.ticket_id = t.ticket_id "
					+ "JOIN Customer c ON r.customer_id = c.customer_id "
					+ "WHERE r.event_id = ? AND r.ticket_id = ? AND r.customer_id = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setInt(1, eventId);
				preparedStatement.setInt(2, ticketId);
				preparedStatement.setInt(3, customer_id);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					String customerName = resultSet.getString("customer_name");
					String reservedEventName = resultSet.getString("event_name");
					String eventDate = resultSet.getDate("event_date").toString();
					String eventTime = resultSet.getTime("event_time").toString();
					String ticketType = resultSet.getString("ticket_type");
					double price = resultSet.getDouble("price");

					System.out.println("\nReceipt:");
					System.out.println("=================================================================");
					System.out.println("Name: " + customerName);
					System.out.println("Event: " + reservedEventName);
					System.out.println("Date: " + eventDate);
					System.out.println("Time: " + eventTime);
					System.out.println("Ticket Type: " + ticketType);
					System.out.println("Price: $" + price);
					System.out.println("=================================================================");
				} else {
					System.out.println("No reservation found.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				database_connection.disconnect();
			}
		} else {
			System.out.println("Unable to connect to the database.");
		}
	}

	

}
