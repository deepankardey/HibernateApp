package com.imcs.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private Integer productID;
	
	private String name;
	
	private String description;
	
	private Integer price;
	
	@OneToMany
	@JoinColumn(name = "product_id")
	private Set<OrderProduct> orderProduct;

	public Products() {
		super();
	}
	
}
