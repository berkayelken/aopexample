package com.aop.demo.exception;

import org.springframework.http.HttpStatus;

public class ClientException extends Exception {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public ClientException(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
