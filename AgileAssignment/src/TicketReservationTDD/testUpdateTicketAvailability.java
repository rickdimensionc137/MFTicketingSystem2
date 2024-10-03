package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testUpdateTicketAvailability {

//	@Test
//	public void testUpdateTicketAvailability() {
//		assertTrue(manageEvents.updateTicketAvailability());
//	}
	
//	@Test
//	public void testRowsUpdated() {
//		assertTrue(manageEvents.updateTicketAvailability(1));
//	}

//	@Test
//	public void testRowsUpdatedDb() {
//		assertEquals(1,manageEvents.updateTicketAvailability(1));
//	}
	
	@Test
	public void testRowsUpdatedDb() {
		assertEquals(-1,manageEvents.updateTicketAvailability(1));
	}
}
