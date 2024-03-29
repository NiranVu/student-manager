package com.example.demo.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ResponseDTO;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler({NoResultException.class})
	public ResponseDTO<String> notFound(NoResultException e) {
		logger.info("INFO", e);
		
		ResponseDTO<String> responseDTO = new ResponseDTO<>(400, "No Data");
		return responseDTO;
	}
	
	@ExceptionHandler({BindException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> badRequest(BindException e) {
		logger.info("bad request", e);
		
		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		
		String msg = "";
		for (ObjectError err : errors) {
			FieldError fieldError = (FieldError) err;
			
			msg += fieldError.getField() + ":" + err.getDefaultMessage() + ";";
		}
				
		return ResponseEntity.badRequest().body(msg);
	}
	
	@ExceptionHandler({SQLIntegrityConstraintViolationException.class})
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ResponseDTO<String> duplicate(SQLIntegrityConstraintViolationException e) {
		logger.info("INFO", e);
		
		ResponseDTO<String> responseDTO = new ResponseDTO<>(400, "Duplicated Data");
		return responseDTO;
	}
}
	