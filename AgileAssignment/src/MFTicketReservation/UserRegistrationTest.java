package MFTicketReservation;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserRegistrationTest {

	@Test
    public void testValidEmail() {
        assertTrue(UserRegistration.isValidEmail("test@example.com"));
        assertFalse(UserRegistration.isValidEmail("test.com"));
    }
	
	@Test
    public void testValidName() {
        assertTrue(UserRegistration.isValidName("John Doe")); 
        assertFalse(UserRegistration.isValidName("J")); 
        assertFalse(UserRegistration.isValidName("John123")); 
        assertFalse(UserRegistration.isValidName("John_Doe")); 
    }
	
	@Test
    public void testValidPassword() {
        assertTrue(UserRegistration.isValidPassword("Password1!")); 
        assertFalse(UserRegistration.isValidPassword("password")); 
        assertFalse(UserRegistration.isValidPassword("Password1")); 
        assertFalse(UserRegistration.isValidPassword("Password!")); 
        assertFalse(UserRegistration.isValidPassword("Pass!1")); 
    }

    @Test
    public void testRegisterCustomerWithDuplicateEmail() {
        assertFalse(UserRegistration.emailExists("duplicate@example.com"));
    }

}
