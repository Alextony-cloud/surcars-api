package io.github.alextony_cloud.surcars.api.controller.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long timestamp;
	private Integer error;
	private String message;
	private String path;
	
	public StandardError() {
		super();
	}
	public StandardError(Long timestamp, Integer error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
