package be.pxl.auctions.util.exception;

public class DuplicateEmailException extends Exception {

	public DuplicateEmailException(String email) {
		super("Email [" + email + "] is already in use.");
	}
}
