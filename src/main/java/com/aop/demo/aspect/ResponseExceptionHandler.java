package com.aop.demo.aspect;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aop.demo.exception.ClientException;
import com.aop.demo.exception.NotClientException;
import com.aop.demo.exception.NotException;
import com.aop.demo.exception.NotServerException;
import com.aop.demo.exception.ServerException;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { NullPointerException.class })
	protected ResponseEntity<Object> handleNPE(NullPointerException ex, WebRequest req) {
		String bodyOfResponse = "Http Status cannot be found!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, req);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<Object> handleIllegal(IllegalArgumentException ex, WebRequest req) {
		String bodyOfResponse = "Http Status cannot be found!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
	}

	@ExceptionHandler(value = { ClientException.class })
	protected ResponseEntity<Object> handleClientException(ClientException ex, WebRequest req) {
		String bodyOfResponse = "This is your client exception what is your deserve!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getStatus(), req);
	}

	@ExceptionHandler(value = { ServerException.class })
	protected ResponseEntity<Object> handleServerException(ServerException ex, WebRequest req) {
		String bodyOfResponse = "This is your server exception what is your deserve!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getStatus(), req);
	}

	@ExceptionHandler(value = { NotException.class })
	protected ResponseEntity<Object> handleNotException(NotException ex, WebRequest req) {
		String bodyOfResponse = "This status code is not compatible with Exception Code Range!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
	}

	@ExceptionHandler(value = { NotClientException.class })
	protected ResponseEntity<Object> handleNotException(NotClientException ex, WebRequest req) {
		String bodyOfResponse = "This status code is not compatible with Client Exception Code Range!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
	}

	@ExceptionHandler(value = { NotServerException.class })
	protected ResponseEntity<Object> handleNotException(NotServerException ex, WebRequest req) {
		String bodyOfResponse = "This status code is not compatible with Server Exception Code Range!";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
	}
}
