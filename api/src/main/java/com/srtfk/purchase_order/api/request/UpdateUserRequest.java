package com.srtfk.purchase_order.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateUserRequest extends CreateUserRequest {
	@NotNull
	private Integer Id;
}
