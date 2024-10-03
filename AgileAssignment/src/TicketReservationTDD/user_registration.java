package TicketReservationTDD;

public class user_registration {
	
	  private static final int MIN_LENGTH = 8;
	    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";

	    public static boolean isValidPassword(String password) {
	        if (password == null || password.length() < MIN_LENGTH) {
	            return false;
	        }

	        boolean hasSpecialChar = containsSpecialCharacter(password);
	        boolean hasNumeric = containsNumericCharacter(password);

	        return hasSpecialChar && hasNumeric;
	    }

	    private static boolean containsSpecialCharacter(String password) {
	        return password.chars().anyMatch(ch -> SPECIAL_CHARACTERS.indexOf(ch) >= 0);
	    }

	    private static boolean containsNumericCharacter(String password) {
	        return password.chars().anyMatch(Character::isDigit);
	    }
}
