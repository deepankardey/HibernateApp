package com.imcs.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_product")
public class OrderProduct {
	
	@Id
	@GeneratedValue
	@Column(name="order_product_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	private Orders orders;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Products products;
	
	private  Integer quantity;

	public OrderProduct() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Orders getOrders() {
		return orders;
	}


	public void setOrders(Orders orders) {
		this.orders = orders;
	}


	public Products getProducts() {
		return products;
	}


	public void setProducts(Products products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OrderProduct ["
				+ "\n\t\t id=" + id + ","
				+ "\n\t\t orders=" + orders + ","
				+ "\n\t\t products=" + products + ","
				+ "\n\t\t quantity=" + quantity
				+ "\n ]";
		
	}
	
	
	
}

/*@Id
	@Column(name="order_product_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderProductId;
	
	private Integer quantity;*/