package com.aop.demo.aspect;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class LoggingAspect {
	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("within(com.aop.demo..*) && !within(com.aop.demo.aspect..*)")
	private void loggingPointcut() {
	}

	@Around("loggingPointcut()")
	public Object collectInfoLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Instant begin = Instant.now();
		logger.info("Beginning time: " + begin.toString());
		logger.info("Invoked method: " + proceedingJoinPoint.getSignature().toLongString());
		Object ret = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		Instant end = Instant.now();
		logger.info("Ending time: " + end.toString());
		logger.info("Method duration: " + Duration.between(begin, end).getNano() + " nanosecond.");
		return ret;
	}

	@AfterThrowing(pointcut = "loggingPointcut()", throwing = "ex")
	public void collectErrorLog(JoinPoint jp, Exception ex) {
		logger.error("Error occur on " + jp.getSignature().toLongString(), ex);
	}
}
