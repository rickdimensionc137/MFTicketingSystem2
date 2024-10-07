package MFTicketReservation;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventSelectionTest {

	@Test
	public void testCheckTicketAvailability() {
		int ticketId = 4;
		int availability = EventSelection.checkTicketAvailability(ticketId);
		assertEquals(199, availability);
	}

	@Test
	public void testUpdateTicketAvailability_Success() {
		int ticketId = 5;
		EventSelection.updateTicketAvailability(ticketId);
		int availability = EventSelection.checkTicketAvailability(ticketId);
		assertEquals(39, availability);
	}

}
