package com.imcs.hibernate.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Orders {
	
	@Id
	@GeneratedValue
	@Column(name="order_id")
	private Integer orderID;
	
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
	
	@OneToMany(mappedBy = "orders" , fetch = FetchType.EAGER)
	private Set<OrderProduct> orderProducts = new HashSet<OrderProduct>();
	
	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id") 
	private Customers customer; 
	
	public Orders() {
		super();
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
	public Date getInvoiceCreationDate() {
		return invoiceCreationDate;
	}

	public void setInvoiceCreationDate(Date invoiceCreationDate) {
		this.invoiceCreationDate = invoiceCreationDate;
	}

	public Date getDeliveryDueDate() {
		return deliveryDueDate;
	}

	public void setDeliveryDueDate(Date deliveryDueDate) {
		this.deliveryDueDate = deliveryDueDate;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getCustomMessages() {
		return customMessages;
	}

	public void setCustomMessages(String customMessages) {
		this.customMessages = customMessages;
	}

	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	
	public void addOrderProducts(OrderProduct orderProducts) {
        this.orderProducts.add(orderProducts);
    } 
	
	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Orders ["
				+ "\n\t\t orderID=" + orderID + ","
				+ "\n\t\t invoiceCreationDate=" + invoiceCreationDate + ","
				+ "\n\t\t deliveryDueDate="+ deliveryDueDate + ","
				+ "\n\t\t paymentDueDate=" + paymentDueDate + ","
				+ "\n\t\t customMessages=" + customMessages+ ","
				+ "\n\t\t Customers=" + customer+ ","
				+ "\n]";
		/*+ "\n\t\t orderProduct=" + orderProducts +*/ 
	}
	
	
	
}

/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private Integer orderID;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customers customer;
	
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
	private Set<OrderProduct> orderProducts;*/