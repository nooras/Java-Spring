package infy.ems.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import infy.ems.exception.EMSException;

@Component
@Aspect
public class LoggingAspect
{

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	//Logging after running EmployeeServiceImpl methods
	@AfterThrowing(pointcut = "execution(* infy.ems.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(EMSException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
	}

}
