package com.imcs.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Entity
@Table(name="order_product")
public class OrderProduct {
	@Id
	@Column(name="order_product_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderProductId;
	
	private Integer quantity;

	public OrderProduct() {
		super();
	}
	
}
