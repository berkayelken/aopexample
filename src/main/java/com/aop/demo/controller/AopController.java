package com.aop.demo.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aop.demo.exception.ClientException;
import com.aop.demo.exception.NotException;
import com.aop.demo.exception.ServerException;
import com.aop.demo.service.AopService;

@RestController
@RequestMapping("/api")
public class AopController {

	@Autowired
	private AopService service;

	@GetMapping("/text")
	public ResponseEntity<String> getCached() {
		String key = String.valueOf(Instant.now().toEpochMilli());
		service.handleText(key);
		return ok(service.getText(key));
	}

	@GetMapping("/text/adviced")
	public ResponseEntity<String> getAdvicedCached() {
		String key = String.valueOf(Instant.now().toEpochMilli());
		service.handleTextWithAdvice(key);
		return ok(service.getText(key));
	}

	@GetMapping("/exception/{codeStatus}")
	public ResponseEntity<String> getCached(@PathVariable(required = false) Integer codeStatus,
			@RequestParam(required = false) boolean client)
			throws NullPointerException, IllegalArgumentException, NotException, ClientException, ServerException {
		service.throwException(codeStatus, client);
		return ok("Ok");
	}
}
