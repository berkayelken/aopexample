package com.aop.demo.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends Exception {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public ServerException(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
