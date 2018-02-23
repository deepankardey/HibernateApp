package com.imcs.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="address")
public class Address {
	
	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "customers"))
	private Long id;

	@Column(name="billing_street")
	private String billingStreet;
	
	@Column(name="billing_city")
	private String billingCity;
	
	@Column(name="billing_state")
	private String billingState;
	
	@Column(name="billing_zip")
	private Integer billingZip;
	
	@Column(name="billing_country")
	private String billingCountry; 
	
	@Column(name="shipping_street")
	private String shippingStreet; 
	
	@Column(name="shipping_city")
	private String shippingCity;
	
	@Column(name="shipping_state")
	private String shippingState;
	
	@Column(name="shipping_zip")
	private Integer shippingZip;
	
	@Column(name="shipping_country")
	private String shippingCountry;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customers customer;
	
	public Address() {
		super();
	}

	@Override
	public String toString() {
		return "Address ["
				+ "\n\t\t  billingStreet=" + billingStreet + ","
				+ "\n\t\t  billingCity=" + billingCity + ","
				+ "\n\t\t  billingState="+ billingState + ","
				+ "\n\t\t  billingZip=" + billingZip + ","
				+ "\n\t\t  billingCountry=" + billingCountry+ ","
				+ "\n\t\t  shippingStreet=" + shippingStreet + ","
				+ "\n\t\t  shippingCity=" + shippingCity + ","
				+ "\n\t\t  shippingState="+ shippingState + ","
				+ "\n\t\t  shippingZip=" + shippingZip + ","
				+ "\n\t\t  shippingCountry=" + shippingCountry + 
				"\n]";
	}
}
