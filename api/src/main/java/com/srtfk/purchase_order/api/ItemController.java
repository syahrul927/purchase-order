package com.srtfk.purchase_order.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtfk.purchase_order.api.request.CreateItemRequest;
import com.srtfk.purchase_order.api.request.UpdateItemRequest;
import com.srtfk.purchase_order.api.response.ApiResponse;
import com.srtfk.purchase_order.api.response.ItemResponse;
import com.srtfk.purchase_order.dao.model.Item;
import com.srtfk.purchase_order.usecase.ItemUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
	private final ItemUsecase itemUsecase;

	@GetMapping("/")
	public ApiResponse<List<ItemResponse>> getItems() {
		return new ApiResponse<>(itemUsecase.getItems().stream().map(item -> new ItemResponse(item.getId(),
				item.getName(), item.getDescription(), item.getPrice(), item.getCost())).toList());
	}

	@GetMapping("/{id}")
	public ApiResponse<ItemResponse> getItem(@PathVariable Integer id) {
		Item item = itemUsecase.getItem(id);
		return new ApiResponse<>(new ItemResponse(item.getId(),
				item.getName(), item.getDescription(), item.getPrice(), item.getCost()));
	}

	@PostMapping("/")
	public ApiResponse<ItemResponse> createUser(@Valid @RequestBody CreateItemRequest request,
			@RequestHeader("userId") Integer userId) {
		Item item = itemUsecase.createNewItem(new Item(request.getName(), request.getDescription(), request.getPrice(),
				request.getCost()), userId);
		return new ApiResponse<>(new ItemResponse(item.getId(),
				item.getName(), item.getDescription(), item.getPrice(), item.getCost()));
	}

	@PutMapping("/")
	public ApiResponse<ItemResponse> updateUser(@Valid @RequestBody UpdateItemRequest request,
			@RequestHeader("userId") Integer userId) {
		Item item = itemUsecase
				.updateItem(new Item(request.getId(), request.getName(), request.getDescription(), request.getPrice(),
						request.getCost()), userId);
		return new ApiResponse<>(new ItemResponse(item.getId(),
				item.getName(), item.getDescription(), item.getPrice(), item.getCost()));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<ItemResponse> deleteUser(@Valid @PathVariable Integer id,
			@RequestHeader("userId") Integer userId) {
		itemUsecase.deleteItem(id, userId);
		return new ApiResponse<>(null);
	}
}
