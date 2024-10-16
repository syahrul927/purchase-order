package com.srtfk.purchase_order.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequest {
	@NotBlank
	private String name;
	private String description;
	@NotNull
	private Integer price;
	@NotNull
	private Integer cost;
}
