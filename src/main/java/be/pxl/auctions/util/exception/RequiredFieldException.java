package be.pxl.auctions.util.exception;

public class RequiredFieldException extends Exception {

	public RequiredFieldException(String field) {
		super("[" + field + "] is required.");
	}
}
