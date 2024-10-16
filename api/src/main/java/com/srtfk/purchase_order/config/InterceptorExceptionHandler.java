package com.srtfk.purchase_order.config;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.srtfk.purchase_order.api.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
class InterceptorsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	ApiResponse<String> handle(IllegalArgumentException e) {
		log.info(e.getMessage(), e);
		return new ApiResponse<String>(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		List<String> errorMessages = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> String.format("Field '%s': %s", error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());

		String userFriendlyMessage = String.join(", ", errorMessages);

		ApiResponse<String> apiResponse = new ApiResponse<>(
				HttpStatus.BAD_REQUEST,
				"Validation failed: " + userFriendlyMessage);

		return new ResponseEntity<>(apiResponse, status);
	}

	@ExceptionHandler(NoSuchElementException.class)
	ApiResponse<String> handle(NoSuchElementException e) {
		log.info(e.getMessage(), e);
		return new ApiResponse<String>(HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	ApiResponse<String> handle(AccessDeniedException e) {
		log.info(e.getMessage(), e);
		return new ApiResponse<String>(HttpStatus.FORBIDDEN, e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	ApiResponse<String> handle(Exception e) {
		log.error(e.getMessage(), e);
		return new ApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR, "Please contact the administrator.");
	}
}
