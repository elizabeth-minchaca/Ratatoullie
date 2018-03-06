package ar.edu.unlp.lifia.ratatoullie.exception;


public class LoginFailedException extends MyException {
	public LoginFailedException(String message) {
		setMessage(message);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
