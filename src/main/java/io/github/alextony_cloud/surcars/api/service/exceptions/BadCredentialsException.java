package io.github.alextony_cloud.surcars.api.service.exceptions;

public class BadCredentialsException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadCredentialsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadCredentialsException(String message) {
		super(message);
	}
}