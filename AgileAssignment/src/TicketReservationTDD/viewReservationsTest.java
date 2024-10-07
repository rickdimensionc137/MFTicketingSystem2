package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class viewReservationsTest {

//	@Test
//	public void test() {
//		assertTrue(viewReservations.viewCustomerReservations());
//	}

//	@Test
//	public void testwithParameters() {
//		assertTrue(viewReservations.viewCustomerReservations(1001));
//	}

	@Test
	public void testwithDb() {
		assertTrue(viewReservations.viewCustomerReservations(1001));
	}

}