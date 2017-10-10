package com.movie.booking.errors;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidRequestException.class })
	protected ResponseEntity<Object> handleInvalidRequest(InvalidRequestException e, WebRequest request) {
		Error error=new Error();
		error.setMessage(e.getMessage());
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json"));
		return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<Object> handleNotFoundRequest(NotFoundException e, WebRequest request) {
		Error error=new Error();
		error.setMessage(e.getMessage());
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json"));
		return new ResponseEntity<>(error, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({InvalidFormatException.class})
    protected ResponseEntity<Object> handleInvalidRequest(InvalidFormatException e, WebRequest request) {
        Error error=new Error();
		error.setMessage(e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error=new Error();
		error.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error=new Error();
		error.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error=new Error();
		error.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
	}
	
	
}
