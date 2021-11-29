package com.aop.demo.service;

import static com.aop.demo.cache.ExampleCaches.addStr;
import static com.aop.demo.cache.ExampleCaches.getStr;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aop.demo.exception.ClientException;
import com.aop.demo.exception.NotClientException;
import com.aop.demo.exception.NotException;
import com.aop.demo.exception.NotServerException;
import com.aop.demo.exception.ServerException;

@Service
public class AopService {

	public String getText(String key) {
		return getStr(key);
	}

	public void handleTextWithAdvice(String key){
		addStr(key, "Service Str - ");
	}

	public void handleText(String key) {
		addStr(key, "Service Str - ");
	}

	public void throwException(Integer codeStatus, boolean client)
			throws NullPointerException, IllegalArgumentException, NotException, ClientException, ServerException {
		if (codeStatus == null)
			throw new NullPointerException();

		if (codeStatus < 400)
			throw new NotException();
		else if (client && codeStatus > 499)
			throw new NotClientException();
		else if (!client && (codeStatus < 499 || codeStatus > 599))
			throw new NotServerException();

		if (client)
			throw new ClientException(HttpStatus.valueOf(codeStatus));
		else
			throw new ServerException(HttpStatus.valueOf(codeStatus));

	}
}
