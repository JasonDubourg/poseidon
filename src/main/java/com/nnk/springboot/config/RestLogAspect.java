package com.nnk.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestLogAspect {

	@After("allControllerPointCut()")
	public void logAfterQuery(JoinPoint joinPoint) {
		System.out.println(" ---Method--- " + joinPoint.getSignature().getName() + "()");
	}

	@Pointcut("execution(* com.nnk.springboot.services.*.*(..))")
	public void allControllerPointCut() {
	}

}