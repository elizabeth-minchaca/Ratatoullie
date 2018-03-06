package ar.edu.unlp.lifia.ratatoullie.exception;

public class ImpossibleToCreateEntityException extends MyException {
	private String message; 
	public ImpossibleToCreateEntityException(String message) {
		setMessage(message);
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
