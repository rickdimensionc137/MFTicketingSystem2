package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testTicketAvailability {

	@Test
	public void testAvailabilityTicket() {
	    // Assuming ticket ID 1 exists in the database and has an availability of 50.
	    assertEquals(50, manageEvents.checkTicketAvailability(1));
	}
	
	@Test
	public void testNonExistentTicket() {
	    // Assuming ticket ID 999 does not exist in the database.
		// Expect 0 when ticket doesn't exist
	    assertEquals(0, manageEvents.checkTicketAvailability(999));  
	}
	
	@Test
	public void testDatabaseTicketAvailability() {
		//In database ticket ID 2 has availability 100
	    assertEquals(100, manageEvents.checkTicketAvailability(2));  
	}


}
