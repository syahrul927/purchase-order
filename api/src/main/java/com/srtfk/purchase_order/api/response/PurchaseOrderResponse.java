package com.srtfk.purchase_order.api.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseOrderResponse {

	private Integer id;

	private LocalDateTime dateTime;

	private String description;

	private Integer totalPrice;

	private Integer totalCost;

	private List<PurchaseOrderDetailResponse> details;

}
