package com.users.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.users.exception.UserException;

@RestControllerAdvice
public class ExceptionControllerAdvice
{

	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);

	@Autowired
	private Environment environment;

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(UserException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setResMsg(environment.getProperty(exception.getMessage()));
		errorInfo.setUserId(exception.getId());

		List<ValidationErrorInfo> valerrorList = new ArrayList<ValidationErrorInfo>();
		ValidationErrorInfo valerror = new ValidationErrorInfo();
		valerror.setCode(HttpStatus.BAD_REQUEST.value());
		valerror.setField("null");
		valerror.setValidationMsg("Please try again!");
		valerrorList.add(valerror);

		errorInfo.setValError(valerrorList);
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setResMsg(environment.getProperty(exception.getMessage()));
		errorInfo.setUserId(null);

		List<ValidationErrorInfo> valerrorList = new ArrayList<ValidationErrorInfo>();
		ValidationErrorInfo valerror = new ValidationErrorInfo();
		valerror.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		valerror.setField("Null");
		valerror.setValidationMsg(environment.getProperty("General.EXCEPTION_MESSAGE"));
		valerrorList.add(valerror);

		errorInfo.setValError(valerrorList);
		return new ResponseEntity<>(errorInfo,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//when data is missing

	@ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, DataIntegrityViolationException.class})
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		List<ValidationErrorInfo> valerrorList = new ArrayList<ValidationErrorInfo>();
		ValidationErrorInfo valerror = new ValidationErrorInfo();
		ErrorInfo errorInfo = new ErrorInfo();
		if (exception instanceof MethodArgumentNotValidException)
		{
			MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
			errorInfo.setResMsg(environment.getProperty("Service.VALIDATION_ERROR"));
			errorInfo.setUserId("ID not created or not found!!");
			String val = manvException.getBindingResult()
					.getAllErrors()
					.stream()
					.map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(", "));
			valerror.setValidationMsg(val);
			String field = manvException.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(x->x.getField())
					.collect(Collectors.joining(", "));
			valerror.setField(field);
			valerror.setCode(HttpStatus.BAD_REQUEST.value());
			valerrorList.add(valerror);
		}
		else
		{
			if(exception instanceof DataIntegrityViolationException) {
				//				DataIntegrityViolationException dataException = (DataIntegrityViolationException) exception;
				errorInfo.setResMsg(environment.getProperty("Service.CONSTRAINT"));
				errorInfo.setUserId("ID not created or not found!!");
				valerror.setValidationMsg("Unique index or primary key violation in email field");
				valerror.setField("UserID or Email");
				valerror.setCode(HttpStatus.BAD_REQUEST.value());
				valerrorList.add(valerror);

			}
			//					else {
			//						ConstraintViolationException cvException = (ConstraintViolationException) exception;
			//						errorMsg = cvException.getConstraintViolations()
			//								.stream()
			//								.map(ConstraintViolation::getMessage)
			//								.collect(Collectors.joining(", "));
			//					}
		}
		errorInfo.setValError(valerrorList);
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
}
