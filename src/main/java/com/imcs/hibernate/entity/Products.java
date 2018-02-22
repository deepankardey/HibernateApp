package com.imcs.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue
	@Column(name="product_id")
	private Integer productID;
	
	private String name;
	
	private String description;
	
	private Integer price;
	
	@OneToMany(mappedBy = "products",fetch = FetchType.EAGER)
	private Set<OrderProduct> orderProducts;
	
	public Products() {
		super();
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	
	public void addOrderProducts(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }

	@Override
	public String toString() {
		return "Products ["
				+ "\n\t\t productID=" + productID + ","
				+ "\n\t\t name=" + name + ","
				+ "\n\t\t description=" + description + ","
				+ "\n\t\t price="+ price + ","
				+"  \n ]";
				/*+ "\n\t\t orderProducts=" + orderProducts +*/ 
	}
	
	
	
	
	
}
