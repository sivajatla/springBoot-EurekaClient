package com.exception.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		//logger.info("SQLException Occured:: URL="+request.getRequestURL());
		return "database_error";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		System.out.println("IOException handler executed");
		//returning 404 error code
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String nullPointerExceptionHandler(HttpServletRequest request, Exception ex){
		System.out.println("inside null pointer exception");
		String url = request.getRequestURI();
		System.out.println("url::"+url);
		return "redirect:database_error.jsp";
		
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> resourceNotFoundHandler(HttpServletRequest request,HttpServletResponse response, Exception e) {
		System.out.println("inside resource not found exception handler");
		ResponseEntity<String> custresponse = new ResponseEntity<String>("no reqeust mapping",HttpStatus.NOT_FOUND);
		
		return custresponse;
	}
}