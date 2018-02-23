package com.imcs.hibernate.pojo;

import com.imcs.hibernate.entity.OrderProduct;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDetails {
	private OrderProduct orders;
	private Long price;
	public OrderDetails(OrderProduct orders, Long price) {
		super();
		this.orders = orders;
		this.price = price;
	}
}
