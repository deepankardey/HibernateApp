package com.imcs.hibernate.app;

import java.util.Scanner;

import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.interfaces.CustomerServiceInterface;
import com.imcs.hibernate.services.CustomerServiceImpl;

import trng.imcs.hib.excp.CustomException;

public class CustomerApp {
	
	CustomerServiceInterface impl = null; 
	
	public static void main(String[] args) throws CustomException {
		new CustomerApp().execute();
	}
	
	public void execute() {
		try(Scanner sc = new Scanner(System.in);) {
			impl = new CustomerServiceImpl();
			while (true) {
				System.out.println("=========================================================================");
				System.out.println("Select one option from the menu");
				System.out.println("1. Add an Customer");
				System.out.println("2. Delete an Customer");
				System.out.println("3. Update existing Customer data with the given new information.");
				System.out.println("4. Display Customer information for the given Customer id.");
				System.out.println("5. Exit");
				System.out.println("=========================================================================");
				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					addCustomerOptions(sc);
					break;
				case 2:
					deleteCustomerOption(sc);
					break;
				case 3:
					updateCustomerOptions(sc);
					break;
				case 4:
					displayCustomerOption(sc);
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Input. Please try again.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private void addCustomerOptions(Scanner sc)throws Exception {
		Customers customer = enterCustomerDetails(sc);
		try {
			if(impl.addCustomer(customer));
				System.out.println("Customer with Name : "+customer.getFirstName()+" added to the DB");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void deleteCustomerOption(Scanner sc)throws Exception {
		System.out.println("Enter Customer ID : ");
		int id = sc.nextInt();
		try {
			if(impl.deleteCustomer(id))
				System.out.println("Customer with id "+id+" got deleted");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void updateCustomerOptions(Scanner sc)throws Exception {
		System.out.println("Enter Customer ID : ");
		int id = sc.nextInt();
		Customers customer = enterCustomerDetails(sc);
		customer.setId(id);
		try {
			if(impl.updateCustomer(customer))
				System.out.println("Customer with id "+id+" got updated");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void displayCustomerOption(Scanner sc) {
		System.out.println("Enter Customer ID : ");
		int id = sc.nextInt();
		Customers emp = impl.loadCustomer(id);
		if(emp==null)
			System.out.println("Provided Customer Id not valid");
		else
			System.out.println(emp);
	}
	
	private Customers enterCustomerDetails(Scanner sc) {
		String shippingStreet = null,shippingCity = null,shippingState = null,shippingCountry = null;
		int shippingZip = 0;
		System.out.println("Enter Customer Title : ");
		String title = sc.next();
		System.out.println("Enter Customer First Name : ");
		String firstName = sc.next();
		System.out.println("Enter Customer Middle Name : ");
		String middleName = sc.next();
		System.out.println("Enter Customer Last Name : ");
		String lastName = sc.next();
		System.out.println("Enter Customer Suffix : ");
		String suffix = sc.next();
		System.out.println("Enter Customer Email : ");
		String email = sc.next();
		System.out.println("Enter Customer Company : ");
		String company = sc.next();
		System.out.println("Enter Display Name : ");
		String displayName = sc.next();
		System.out.println("Print On Check As : ");
		String printOnCheckAs = sc.next();
		System.out.println("Enter Customer Billing street : ");
		String billingStreet = sc.next();
		System.out.println("Enter Customer Billing  city : ");
		String billingCity = sc.next();
		System.out.println("Enter Customer Billing  zip : ");
		int billingZip = sc.nextInt();
		System.out.println("Enter Customer Billing state : ");
		String billingState = sc.next();
		System.out.println("Enter Customer Billing  country : ");
		String billingCountry = sc.next();
		System.out.println("Is Billing address is same as shipping address : ");
		System.out.println("1. YES");
		System.out.println("2. NO");
		System.out.println("Note : Other than No will be considered as Yes.");
		int selection = sc.nextInt();
		switch(selection) {
			case 1: shippingStreet = billingStreet;
					shippingCity = billingCity;
					shippingZip = billingZip;
					shippingState = billingState;
					shippingCountry = billingCountry;
					break;
			case 2: System.out.println("Enter Customer Billing street : ");
					billingStreet=sc.next();
					System.out.println("Enter Customer Billing  city : ");
					billingCity=sc.next(); 
					System.out.println("Enter Customer Billing  zip : ");
					billingZip=sc.nextInt(); 
					System.out.println("Enter Customer Billing state : ");
					billingState=sc.next(); 
					System.out.println("Enter Customer Billing  country : ");
					billingCountry=sc.next(); 
					break;
			default:shippingStreet = billingStreet;
					shippingCity = billingCity;
					shippingZip = billingZip;
					shippingState = billingState;
					shippingCountry = billingCountry;
					break;
		}
		
		System.out.println("Enter Other Details : ");
		String otherDetails=sc.next(); 
		return new Customers(title,firstName,middleName,lastName,suffix,email,
				company,displayName,printOnCheckAs,billingStreet,billingCity,billingState,
				billingZip,billingCountry,shippingStreet,shippingCity,shippingState,shippingZip,
				shippingCountry,otherDetails);
	}
}
