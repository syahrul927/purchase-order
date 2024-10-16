package com.srtfk.purchase_order.api.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	private T data;
	private String message;
	private int status;

	public ApiResponse(T data) {
		this.data = data;
		this.status = HttpStatus.OK.value();
	}

	public ApiResponse(HttpStatus status, String message) {
		this.status = status.value();
		this.message = message;
	}

}
