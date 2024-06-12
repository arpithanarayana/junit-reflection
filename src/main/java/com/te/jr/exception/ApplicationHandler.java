package com.te.jr.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.jr.response.ErrorResponse;

@RestControllerAdvice
public class ApplicationHandler {
	
	@ExceptionHandler(value = {UserIdNotFoundException.class})
	public ResponseEntity<ErrorResponse> handle(UserIdNotFoundException e){
		return ResponseEntity.ofNullable(ErrorResponse.builder().message(e.getMessage())
				.status(HttpStatus.BAD_REQUEST)
				.timestamp(LocalDateTime.now())
				.build());
	}

}
