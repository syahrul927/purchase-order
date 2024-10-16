package com.srtfk.purchase_order.api.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseOrderRequest {
	@NotNull
	private LocalDateTime dateTime;
	private String description;
	@NotNull
	private Integer totalPrice;
	@NotNull
	private Integer totalCost;

	private List<CreatePurchaseOrderDetailRequest> details = new ArrayList<>();

}
