package TicketReservationTDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPassword {

	@Test
	public void passwordMinLength() {
	    assertFalse(user_registration.isValidPassword("Short1!")); // Less than 8 characters
	}
	
	@Test
	public void passwordSpecialCharacter() {
	    assertFalse(user_registration.isValidPassword("Password1")); // No special character
	    assertTrue(user_registration.isValidPassword("Password1!")); // Has special character
	}
	
	@Test
	public void passwordNumericCharacter() {
	    assertFalse(user_registration.isValidPassword("Password!"));  // No numeric character
	    assertTrue(user_registration.isValidPassword("Password1!")); // Has numeric character
	}



}
