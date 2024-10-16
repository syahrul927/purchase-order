package com.srtfk.purchase_order.api;

import java.util.List;

import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtfk.purchase_order.api.request.CreatePurchaseOrderRequest;
import com.srtfk.purchase_order.api.request.UpdatePurchaseOrderRequest;
import com.srtfk.purchase_order.api.response.ApiResponse;
import com.srtfk.purchase_order.api.response.PurchaseOrderDetailResponse;
import com.srtfk.purchase_order.api.response.PurchaseOrderResponse;
import com.srtfk.purchase_order.dao.model.PurchaseOrderDetail;
import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;
import com.srtfk.purchase_order.usecase.PurchaseOrderUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/purchase-order")
@RequiredArgsConstructor
@Slf4j
public class PurchaseOrderController {

	private final PurchaseOrderUsecase purchaseOrderUsecase;

	@GetMapping("/")
	public ApiResponse<List<PurchaseOrderResponse>> getPurchaseOrders() {
		return new ApiResponse<>(purchaseOrderUsecase.getPurchaseOrders().stream().map(
				purchaseOrder -> new PurchaseOrderResponse(purchaseOrder.getId(), purchaseOrder.getDateTime(),
						purchaseOrder.getDescription(), purchaseOrder.getTotalPrice(), purchaseOrder.getTotalCost(),
						purchaseOrder.getDetails().stream().map(
								purchaseOrderDetail -> new PurchaseOrderDetailResponse(
										purchaseOrderDetail.getId(),
										purchaseOrderDetail.getItemId().getId(),
										purchaseOrderDetail.getItemQty(),
										purchaseOrderDetail.getItemPrice(),
										purchaseOrderDetail.getItemCost(),
										purchaseOrderDetail.getPurchaseOrderHeaderId().getId()))
								.toList()))
				.toList());
	}

	@GetMapping("/{id}")
	public ApiResponse<PurchaseOrderResponse> getPurchaseOrder(@PathVariable Integer id) {
		PurchaseOrderHeader purchaseOrder = purchaseOrderUsecase.getPurchaseOrder(id);
		return new ApiResponse<>(new PurchaseOrderResponse(purchaseOrder.getId(),
				purchaseOrder.getDateTime(),
				purchaseOrder.getDescription(),
				purchaseOrder.getTotalPrice(),
				purchaseOrder.getTotalCost(),
				purchaseOrder.getDetails().stream().map(
						purchaseOrderDetail -> new PurchaseOrderDetailResponse(
								purchaseOrderDetail.getId(),
								purchaseOrderDetail.getItemId().getId(),
								purchaseOrderDetail.getItemQty(),
								purchaseOrderDetail.getItemPrice(),
								purchaseOrderDetail.getItemCost(),
								purchaseOrderDetail.getPurchaseOrderHeaderId().getId()))
						.toList()));
	}

	@PostMapping("/")
	public ApiResponse<PurchaseOrderResponse> createPurchaseOrder(
			@Valid @RequestBody CreatePurchaseOrderRequest request, @RequestHeader("userId") Integer userId) {
		PurchaseOrderHeader purchaseOrderHeader = purchaseOrderUsecase
				.createPurchaseOrder(new PurchaseOrderHeader(request.getDateTime(), request.getDescription(),
						request.getTotalPrice(), request.getTotalCost()),
						userId);
		List<Pair<PurchaseOrderDetail, Integer>> pairDetail = request.getDetails().stream().map(detail -> {
			return Pair.of(new PurchaseOrderDetail(detail.getItemQty(), detail.getItemPrice(), detail.getItemCost()),
					detail.getItemId());
		}).toList();
		List<PurchaseOrderDetail> details = purchaseOrderUsecase.createPurchaseOrderDetail(pairDetail,
				purchaseOrderHeader, userId);

		return new ApiResponse<>(new PurchaseOrderResponse(purchaseOrderHeader.getId(),
				purchaseOrderHeader.getDateTime(),
				purchaseOrderHeader.getDescription(),
				purchaseOrderHeader.getTotalPrice(),
				purchaseOrderHeader.getTotalCost(),
				details.stream().map(
						purchaseOrderDetail -> new PurchaseOrderDetailResponse(
								purchaseOrderDetail.getId(),
								purchaseOrderDetail.getItemId().getId(),
								purchaseOrderDetail.getItemQty(),
								purchaseOrderDetail.getItemPrice(),
								purchaseOrderDetail.getItemCost(),
								purchaseOrderHeader.getId()))
						.toList()));
	}

	@PutMapping("/")
	public ApiResponse<PurchaseOrderResponse> updatePurchaseOrder(
			@Valid @RequestBody UpdatePurchaseOrderRequest request, @RequestHeader("userId") Integer userId) {
		PurchaseOrderHeader purchaseOrderHeader = purchaseOrderUsecase
				.updatePurchaseOrder(new PurchaseOrderHeader(request.getId(), request.getDateTime(),
						request.getDescription(), request.getTotalPrice(), request.getTotalCost()), userId);
		return new ApiResponse<>(new PurchaseOrderResponse(purchaseOrderHeader.getId(),
				purchaseOrderHeader.getDateTime(),
				purchaseOrderHeader.getDescription(),
				purchaseOrderHeader.getTotalPrice(),
				purchaseOrderHeader.getTotalCost(),
				null));

	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> deletePurchaseOrder(@PathVariable Integer id, @RequestHeader("userId") Integer userId) {
		purchaseOrderUsecase.deletePurchaseOrder(id, userId);
		return new ApiResponse<>(null);
	}
}
