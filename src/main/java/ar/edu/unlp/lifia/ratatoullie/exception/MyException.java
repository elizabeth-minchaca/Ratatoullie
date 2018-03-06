package ar.edu.unlp.lifia.ratatoullie.exception;

import org.springframework.http.HttpStatus;

public abstract class MyException extends Exception {
	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}