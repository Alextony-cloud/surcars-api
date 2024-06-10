package io.github.alextony_cloud.surcars.api.service.exceptions;

public class DataIntegrityViolationException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
}