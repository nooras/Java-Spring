package com.users.exception;

public class UserException extends Exception {
	private static final long serialVersionUID = 1L;

	private String id;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public UserException(String message, String id) {
		super(message);
		this.id = id;
	}
}
