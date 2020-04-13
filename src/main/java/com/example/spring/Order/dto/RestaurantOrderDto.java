package com.example.spring.Order.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class RestaurantOrderDto {
		
	private String customerId;
	private String orderDetails;
	private String message;
	private Boolean status = Boolean.TRUE;


}
