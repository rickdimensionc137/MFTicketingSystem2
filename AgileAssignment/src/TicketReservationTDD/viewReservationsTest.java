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
//		assertTrue(viewReservations.viewCustomerReservations("BTS Special Stage", "2024-09-2015", "14:30:00", "Silver", 200, 1001));
//	}

	@Test
	public void testwithDb() {
		assertTrue(viewReservations.viewCustomerReservations("BTS Special Stage", "2024-09-2015", "14:30:00", "Silver",
				200, 1001));
	}

}