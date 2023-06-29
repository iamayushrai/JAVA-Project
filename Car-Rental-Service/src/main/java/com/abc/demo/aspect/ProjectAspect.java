package com.abc.demo.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;


@Aspect
@Component
public class ProjectAspect {

	Logger logger  =LoggerFactory.getLogger(ProjectAspect.class);
	
	@Pointcut("execution(* com.abc.demo.*.*.*(..))")
	public void h1() {}
	
	//write an advice :: centralizing login using AOP
		@Around("h1()")    //links advice to pointcut
		public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
			ObjectMapper mapper  =new ObjectMapper();
			String methodName = joinPoint.getSignature().getName();
			String className = joinPoint.getTarget().getClass().toString();
			Object [] array= joinPoint.getArgs(); 
			logger.info("method invoked "+ className + " : "+ methodName + "()"+ "arguments :" + Arrays.toString(array));
			Object object = joinPoint.proceed();
			logger.info(className+ " : "+ methodName + "()"+ "Response :" + Arrays.toString(array));
			return object;
		}
	
}
