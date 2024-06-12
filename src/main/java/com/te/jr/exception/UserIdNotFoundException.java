package com.te.jr.exception;

public class UserIdNotFoundException extends RuntimeException{
	String message;
	
	public UserIdNotFoundException(String message) {
		this.message = message;
	}
}
