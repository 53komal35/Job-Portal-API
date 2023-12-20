package com.example.jobportal.exceptionhandling;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.jobportal.utility.ErrorStructure;
@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> list = ex.getAllErrors();
		HashMap<String, String> hashMap = new HashMap<>();
		for (ObjectError error : list) {
			FieldError fieldError = (FieldError) error;
			String feildName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			hashMap.put(feildName, message);

		}

		return new ResponseEntity<Object>(hashMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> UserNotFoundById(UserNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" USER WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFoundById(CompanyNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" COMPANY WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(IllegalAccssException.class)
	public ResponseEntity<ErrorStructure<String>> illegalAccessExcp(IllegalAccssException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" USER NOT AUTHORISED FOR THIS OPERATION");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> illegalAccessExcp(JobNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" JOB WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(ResumeNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> resumeNotFoundById(ResumeNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" RESUME WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(SkillNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> skillNotFoundById(SkillNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" SKILL WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	

}
