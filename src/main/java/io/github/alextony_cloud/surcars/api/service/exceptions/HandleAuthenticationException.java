package io.github.alextony_cloud.surcars.api.service.exceptions;

public class HandleAuthenticationException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public HandleAuthenticationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public HandleAuthenticationException(String message) {
		super(message);
	}
}