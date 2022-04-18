package hu.sagi.mastermind.exceptions;


public class FullStorage extends Exception{

	private static final long serialVersionUID = 1L;
	String message;

	public FullStorage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
