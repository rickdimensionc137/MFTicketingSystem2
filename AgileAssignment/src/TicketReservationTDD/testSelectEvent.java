package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testSelectEvent {

//	@Test
//	public void testSelectEvent() {
//		assertEquals("BTS Special Stage", manageEvents.selectEvent(3));
//	}
	
//	@Test
//	public void testSelectEventHardcoded() {
//		assertEquals("BTS Special Stage", manageEvents.selectEvent(3));
//	}
	
//	@Test
//	public void testSelectEventFromDb() {
//		assertTrue(manageEvents.selectEvent(3));
//	}
	
	@Test
	public void testSelectEventFromDbWithUserInput() {
		assertTrue(manageEvents.selectEvent(1005));
	}



}
