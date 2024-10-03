package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testSaveReservation {

//	@Test
//	public void testSaveReservation() {
//		assertTrue(manageEvents.saveReservation());
//	}
////	
//	@Test
//	public void testSaveReservationWithParameters() {
//		assertTrue(manageEvents.saveReservation(3,"BTS Special Stage", 11, 1004));
//	}

//
	@Test
	public void testSaveReservationWithDb() {
		assertTrue(manageEvents.saveReservation(3,"BTS Special Stage", 11, 1005));
	}
}
