package com.srtfk.purchase_order.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

}
