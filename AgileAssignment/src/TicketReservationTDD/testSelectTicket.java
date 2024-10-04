package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testSelectTicket {

	@Test
	public void testSelectTicket() {
		assertTrue(manageEvents.selectTicket(3, "BTS Special Stage", 1005));
	}

	
}
