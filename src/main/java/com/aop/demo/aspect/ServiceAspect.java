package com.aop.demo.aspect;

import static com.aop.demo.cache.ExampleCaches.addStr;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE + 1)
public class ServiceAspect {

	@Pointcut("execution(* *.handleTextWithAdvice(..))")
	private void getPointcut() {
	}
	
	@Around("getPointcut()")
	public void giveAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		String key = getKey(proceedingJoinPoint);
		addStr(key, "Around Beginning - ");
		try {
			proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		addStr(key, "Around Ending");
	}

	@Before("getPointcut()")
	public void giveBeforeAdvice(JoinPoint jp) {
		String key = getKey(jp);
		addStr(key, "Before - ");
	}

	@After("execution(* *.handleTextWithAdvice(..))")
	public void giveAfterAdvice(JoinPoint jp) {
		String key = getKey(jp);
		addStr(key, "After - ");
	}

	@AfterReturning(pointcut = "execution(* com.aop.demo.service.AopService.handleTextWithAdvice(..))")
	public void giveAfterReturningAdvice(JoinPoint jp) throws RuntimeException {
		String key = getKey(jp);
		addStr(key, "After Returning - ");
	}

	@AfterThrowing(pointcut = "execution(* com.aop.demo.service.AopService.handleTextWithAdvice(..))")
	public void giveAfterThrowingAdvice(JoinPoint jp) {
		String key = getKey(jp);
		addStr(key, "After Throw - ");
	}

	private String getKey(JoinPoint jp) {
		MethodSignature methodSig = (MethodSignature) jp.getSignature();
		int idx = Arrays.asList(methodSig.getParameterNames()).indexOf("key");
		return String.valueOf(jp.getArgs()[idx]);
	}
}
