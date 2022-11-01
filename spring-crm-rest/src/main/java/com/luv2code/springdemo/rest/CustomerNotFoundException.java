package com.luv2code.springdemo.rest;

// EXCEPTION KLASE MORAJU DA NASLEDE RuntimeException
public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CustomerNotFoundException(String message) {
		super(message);
		
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);

	}

	
	
	
}
