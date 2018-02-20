package com.imcs.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="customers")
@Access(AccessType.FIELD)
@Data
@AllArgsConstructor
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private Integer id;
	
	private String title;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	private String suffix;
	
	@Column(unique = true)
	private String email;
	
	private String company;
	
	@Column(name="display_name")
	private String displayName;
	
	@Column(name="print_on_check")
	private String printOnCheck;
	
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
	
	@Column(name="other_details")
	private String otherDetails;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Set<Orders> orders = new HashSet<Orders>();
	
	public Customers(String title, String firstName, String middleName, String lastName, String suffix, String email,
			String company, String displayName, String printOnCheck, String billingStreet, String billingCity,
			String billingState, Integer billingZip, String billingCountry, String shippingStreet, String shippingCity,
			String shippingState, Integer shippingZip, String shippingCountry, String otherDetails) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.email = email;
		this.company = company;
		this.displayName = displayName;
		this.printOnCheck = printOnCheck;
		this.billingStreet = billingStreet;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingZip = billingZip;
		this.billingCountry = billingCountry;
		this.shippingStreet = shippingStreet;
		this.shippingCity = shippingCity;
		this.shippingState = shippingState;
		this.shippingZip = shippingZip;
		this.shippingCountry = shippingCountry;
		this.otherDetails = otherDetails;
	}

	public Customers(String firstName, String lastName, String email, String company) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
	}

	public Customers(Integer id, String title, String firstName, String middleName, String lastName, String suffix,
			String email, String company, String displayName, String printOnCheck, String billingStreet,
			String billingCity, String billingState, Integer billingZip, String billingCountry, String shippingStreet,
			String shippingCity, String shippingState, Integer shippingZip, String shippingCountry,
			String otherDetails) {
		super();
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.email = email;
		this.company = company;
		this.displayName = displayName;
		this.printOnCheck = printOnCheck;
		this.billingStreet = billingStreet;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingZip = billingZip;
		this.billingCountry = billingCountry;
		this.shippingStreet = shippingStreet;
		this.shippingCity = shippingCity;
		this.shippingState = shippingState;
		this.shippingZip = shippingZip;
		this.shippingCountry = shippingCountry;
		this.otherDetails = otherDetails;
	}

	public Customers() {
		super();
	}

	@Override
	public String toString() {
		return "Customers ["
				+ "\n\t id=" + id + ", "
				+ "\n\t title=" + title + ", "
				+ "\n\t firstName=" + firstName + ","
				+ "\n\t middleName=" + middleName+ ","
				+ "\n\t lastName=" + lastName + ","
				+ "\n\t suffix=" + suffix + ","
				+ "\n\t email=" + email + ","
				+ "\n\t company=" + company+ ","
				+ "\n\t displayName=" + displayName + ","
				+ "\n\t printOnCheck=" + printOnCheck + ","
				+ "\n\t billingStreet=" + billingStreet+ ","
				+ "\n\t billingCity=" + billingCity + ","
				+ "\n\t billingState=" + billingState + ","
				+ "\n\t billingZip=" + billingZip+ ","
				+ "\n\t billingCountry=" + billingCountry + ","
				+ "\n\t shippingStreet=" + shippingStreet + ","
				+ "\n\t shippingCity=" + shippingCity + ","
				+ "\n\t shippingState=" + shippingState + ","
				+ "\n\t shippingZip=" + shippingZip+","
				+ "\n\t shippingCountry=" + shippingCountry + ","
				+ "\n\t otherDetails=" + otherDetails + ","
				+ "\n\t orders=" + orders
				+ "\n]";
	}
	
	
	
}
