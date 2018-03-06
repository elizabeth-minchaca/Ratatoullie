package ar.edu.unlp.lifia.ratatoullie.exception;

public class NotPermissionException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotPermissionException(String message) {
		setMessage(message);
	}

}
