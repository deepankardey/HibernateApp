package com.imcs.hibernate.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private Integer orderID;
	
	/*@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customers customer;*/
	
	@Temporal(TemporalType.DATE)
	@Column(name="invoice_creation_date")
	private Date invoiceCreationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="delivery_due_date")
	private Date deliveryDueDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="payment_due_date")
	private Date paymentDueDate;
	
	@Column(name="custom_Messages")
	private String customMessages;
	
	@OneToMany
	@JoinColumn(name = "order_id")
	private Set<OrderProduct> orderProduct;

	public Orders() {
		super();
	}
	
}
