package com.srtfk.purchase_order.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtfk.purchase_order.api.request.CreatePurchaseOrderDetailRequest;
import com.srtfk.purchase_order.api.request.UpdatePurchaseOrderDetailRequest;
import com.srtfk.purchase_order.api.response.ApiResponse;
import com.srtfk.purchase_order.api.response.PurchaseOrderDetailResponse;
import com.srtfk.purchase_order.dao.model.PurchaseOrderDetail;
import com.srtfk.purchase_order.usecase.PurchaseOrderUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/purchase-order-detail")
@RequiredArgsConstructor
public class PurchaseOrderDetailController {

	private final PurchaseOrderUsecase purchaseOrderUsecase;

	@PostMapping("/")
	public ApiResponse<PurchaseOrderDetailResponse> createPurchaseOrderDetail(
			@Valid @RequestBody CreatePurchaseOrderDetailRequest request,
			@RequestHeader("userId") Integer userId) {
		PurchaseOrderDetail purchaseOrderDetail = purchaseOrderUsecase.createPurchaseOrderDetail(
				new PurchaseOrderDetail(null, request.getItemQty(), request.getItemPrice(), request.getItemCost()),
				request.getPurchaseOrderHeaderId(),
				request.getItemId(), userId);
		return new ApiResponse<>(new PurchaseOrderDetailResponse(
				purchaseOrderDetail.getId(),
				purchaseOrderDetail.getItemId().getId(),
				purchaseOrderDetail.getItemQty(),
				purchaseOrderDetail.getItemPrice(),
				purchaseOrderDetail.getItemCost(),
				null));
	}

	@PutMapping("/")
	public ApiResponse<PurchaseOrderDetailResponse> updatePurchaseOrderDetail(
			@Valid @RequestBody UpdatePurchaseOrderDetailRequest request,
			@RequestHeader("userId") Integer userId) {
		PurchaseOrderDetail purchaseOrderDetail = purchaseOrderUsecase.updatePurchaseOrderDetail(
				new PurchaseOrderDetail(request.getId(), request.getItemQty(), request.getItemPrice(),
						request.getItemCost()),
				request.getItemId(), userId);
		return new ApiResponse<>(new PurchaseOrderDetailResponse(
				purchaseOrderDetail.getId(),
				purchaseOrderDetail.getItemId().getId(),
				purchaseOrderDetail.getItemQty(),
				purchaseOrderDetail.getItemPrice(),
				purchaseOrderDetail.getItemCost(),
				request.getPurchaseOrderHeaderId()));
	}

	@GetMapping("/{id}")
	public ApiResponse<PurchaseOrderDetailResponse> getPurchaseOrderDetail(@PathVariable Integer id) {
		PurchaseOrderDetail purchaseOrderDetail = purchaseOrderUsecase.getPurchaseOrderDetail(id);
		return new ApiResponse<>(new PurchaseOrderDetailResponse(purchaseOrderDetail.getId(),
				purchaseOrderDetail.getItemId().getId(),
				purchaseOrderDetail.getItemQty(),
				purchaseOrderDetail.getItemPrice(),
				purchaseOrderDetail.getItemCost(),
				null));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> deletePurchaseOrderDetail(@PathVariable Integer id,
			@RequestHeader("userId") Integer userId) {
		purchaseOrderUsecase.deletePurchaseOrderDetail(id, userId);
		return new ApiResponse<>(null);

	}

}
