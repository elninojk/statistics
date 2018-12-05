package com.n26.statistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TransactionInvalidException.class)
	public ResponseEntity<Object> handleTransactionInvalidException(TransactionInvalidException exception) {
		return new ResponseEntity<>("Old Transaction", HttpStatus.NO_CONTENT);
	}
}
