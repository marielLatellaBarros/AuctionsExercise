package be.pxl.auctions.util.exception;

public class InvalidEmailException extends Exception {

	public InvalidEmailException(String email) {
		super("[" + email + "] is not a valid email.");
	}
}
