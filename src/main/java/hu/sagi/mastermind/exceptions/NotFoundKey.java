package hu.sagi.mastermind.exceptions;

public class NotFoundKey extends Exception{

	private static final long serialVersionUID = 2L;
	String message;
	public NotFoundKey(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	
}
