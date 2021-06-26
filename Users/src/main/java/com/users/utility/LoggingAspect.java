package com.users.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.users.exception.UserException;

@Component
@Aspect
public class LoggingAspect
{

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	//Logging after running EmployeeServiceImpl methods
	@AfterThrowing(pointcut = "execution(* com.users.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(UserException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
	}

}
