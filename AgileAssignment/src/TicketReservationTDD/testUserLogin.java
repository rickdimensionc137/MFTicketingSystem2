package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testUserLogin {

//	@Test
//	public void testlogin() {
//		assertEquals(1000,userLogin.loginUser());
//	}
	
	@Test
	public void testloginWithEmailAndPassword() {
		assertEquals(1000,userLogin.loginUser("roshansrk@yahoo.com", "c@t$1234"));
	}
	
	@Test
	public void testloginWithEmailAndPasswordWithDb() {
//		a.eathally@gmailcom user exists in database
		assertEquals(1003,userLogin.loginUser("a.eathally@gmail.com", "#ello123"));
	}

}
