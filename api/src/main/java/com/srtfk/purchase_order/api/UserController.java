package com.srtfk.purchase_order.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtfk.purchase_order.api.request.CreateUserRequest;
import com.srtfk.purchase_order.api.request.UpdateUserRequest;
import com.srtfk.purchase_order.api.response.ApiResponse;
import com.srtfk.purchase_order.api.response.UserResponse;
import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.usecase.UserUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserUsecase userUsecase;

	@GetMapping("/")
	public ApiResponse<List<UserResponse>> getUsers() {
		return new ApiResponse<>(userUsecase.getUsers().stream().map(user -> new UserResponse(user.getId(),
				user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone())).toList());
	}

	@GetMapping("/{id}")
	public ApiResponse<UserResponse> getUsers(@PathVariable Integer id) {
		User user = userUsecase.getUser(id);
		return new ApiResponse<>(
				new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
						user.getPhone()));
	}

	@PostMapping("/")
	public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
		User user = userUsecase.createNewUser(
				new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhone()));
		return new ApiResponse<>(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPhone()));
	}

	@PutMapping("/")
	public ApiResponse<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest request) {
		User user = userUsecase.updateUser(new User(request.getId(), request.getFirstName(), request.getLastName(),
				request.getEmail(), request.getPhone()));
		return new ApiResponse<>(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPhone()));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<UserResponse> deleteUser(@Valid @PathVariable Integer id) {
		userUsecase.deleteUser(id);
		return new ApiResponse<>(null);
	}
}