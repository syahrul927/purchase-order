package com.srtfk.purchase_order.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDetailResponse {

	private Integer id;
	private Integer itemId;
	private Integer itemQty;
	private Integer itemPrice;
	private Integer itemCost;
	private Integer purchaseOrderId;

}
