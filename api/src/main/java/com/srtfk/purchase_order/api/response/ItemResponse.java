package com.srtfk.purchase_order.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
	private Integer id;
	private String name;
	private String description;
	private Integer price;
	private Integer cost;
}
