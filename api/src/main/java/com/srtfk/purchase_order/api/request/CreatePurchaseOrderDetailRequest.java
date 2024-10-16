package com.srtfk.purchase_order.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseOrderDetailRequest {
	@NotNull
	private Integer itemId;

	@NotNull
	private Integer itemQty;

	@NotNull
	private Integer itemCost;

	@NotNull
	private Integer itemPrice;

	private Integer purchaseOrderHeaderId;
}
