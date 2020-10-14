package org.secureauth.sarestapi.exception;

public class SARestAPIException extends RuntimeException {

	private static final long serialVersionUID = 2201204523946051388L;

	public SARestAPIException(String message) {
		super(message);
	}

	public SARestAPIException(String s, Exception e) {
		super(s,e);
	}

	public SARestAPIException(Exception exception) {
		super("Exception occurred executing REST query:\n" + exception.getMessage(), exception);
	}
}