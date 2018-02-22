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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name="other_details")
	private String otherDetails;
	
	@OneToMany(mappedBy="customer" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<Orders> orders = new HashSet<Orders>();
	
	@OneToOne(mappedBy="customer" , fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;
	
	public Customers() {
		super();
	}
	
	
	public Customers(String title, String firstName, String middleName, String lastName, String suffix, String email,
			String company, String displayName, String printOnCheck, String otherDetails) {
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
		this.otherDetails = otherDetails;
	}
	
	public Customers(Integer id, String title, String firstName, String middleName, String lastName, String suffix,
			String email, String company, String displayName, String printOnCheck, String otherDetails) {
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
		this.otherDetails = otherDetails;
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
				+ "\n\t otherDetails=" + otherDetails + ","
				+ "\n\t Addres=" + address
				/*+ "\n\t Orders=" + orders*/
				+ "\n]";
	}
	
}
