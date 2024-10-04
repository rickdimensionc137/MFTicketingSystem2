package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testRegisterUser {

//	@Test
//	public void testRegisterUser() {
//		assertEquals(1004, userRegister.registerUser());
//	}
	
//	@Test
//	public void testRegisterUser() {
//		assertEquals(1004, userRegister.registerUser("malikah"));
//	}
	
//	@Test
//	public void testRegister() {
//		assertEquals(1004, userRegister.registerUser("malikah", "mallu@gmail.com"));
//	}
	
//	@Test
//	public void testRegister() {
//		assertEquals(1004, userRegister.registerUser("malikah", "mallu@gmail.com", "M@likah213"));
//	}

//	@Test
//	public void testRegisterAndCheckifExists() {
////		User malikah does not exist in database
//		assertEquals(-1, userRegister.registerUser("malikah", "mallu@gmail.com", "M@likah213"));
//	}
	
	@Test
	public void testRegisterAndsaveInDB() {
//		User malikah does not exist in database
		assertEquals(1004, userRegister.registerUser("malikah", "mallu@gmail.com", "M@likah213"));
	}


}
