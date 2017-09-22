package com.mec.prouni.resource;

public class ServiceException extends RuntimeException {

	public ServiceException(String exception) {
		System.err.println(exception);
	}
}
