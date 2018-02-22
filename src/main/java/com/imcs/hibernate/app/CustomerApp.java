package com.imcs.hibernate.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.imcs.hibernate.entity.Address;
import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.entity.OrderProduct;
import com.imcs.hibernate.entity.Orders;
import com.imcs.hibernate.entity.Products;
import com.imcs.hibernate.interfaces.CustomerServiceInterface;
import com.imcs.hibernate.interfaces.OrderServiceInterface;
import com.imcs.hibernate.pojo.OrderDetails;
import com.imcs.hibernate.services.CustomerServiceImpl;
import com.imcs.hibernate.services.OrderServiceImpl;
import com.imcs.hibernate.services.ProductServiceImpl;

import trng.imcs.hib.excp.CustomException;

public class CustomerApp {
	
	static CustomerServiceInterface customerServiceImpl = null; 
	static OrderServiceInterface orderServiceImpl = null; 
	List<Products> productList = null;
	
	public static void main(String[] args) throws CustomException {
		
		new CustomerApp().execute();
	}
	
	public void execute() {
		try(Scanner sc = new Scanner(System.in);) {
			customerServiceImpl = new CustomerServiceImpl();
			orderServiceImpl = new OrderServiceImpl();
			while (true) {
				System.out.println("=========================================================================");
				System.out.println("Select one option from the menu");
				System.out.println("1. Add a Customer");
				System.out.println("2. Delete a Customer");
				System.out.println("3. Update a Customer.");
				System.out.println("4. Display Customer information .");
				System.out.println("5. Display All Customers .");
				System.out.println("6. Place an Order");
				System.out.println("7. Update an Order.");
				System.out.println("8. Display Order information.");
				System.out.println("9. Display All Orders.");
				System.out.println("10. Load Orders By Customer ID");
				System.out.println("11. Exit");
				System.out.println("=========================================================================");
				int choice;
				try {
					choice = sc.nextInt();
				} catch (Exception e) {
					choice = sc.nextInt();
				}

				switch (choice) {
				case 1:
					addCustomer(sc);
					break;
				case 2:
					deleteCustomer(sc);
					break;
				case 3:
					updateCustomer(sc);
					break;
				case 4:
					Customers customer = displayCustomer(sc);
					if(customer==null)
						System.out.println("Provided Customer Id not valid");
					else
						System.out.println(customer);
					break;
				case 5:
					List<Customers> list = displayAllCustomers();
					for(Customers cust : list) {
						if(cust != null)
							System.out.println(cust);
					}
					break;
				case 6:
					addOrder(sc);
					break;
				case 7:
					updateOrder(sc);
					break;
				case 8:
					System.out.println(displayOrder(sc));
					break;
				case 9:
					displayAllOrders();
					break;
				case 10:
					loadOrdersByCustomerId(sc);
					break;
				case 11:
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
	
	private void addOrder(Scanner sc) {
		boolean added = false;
		SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
		Date invoiceDate = null;
		Date deliveryDate = null;
		Date paymentDueDate = null;

		Customers customer = displayCustomer(sc);
		List<Products> productList = selectProduct(sc);
		Orders order = new Orders();
		
		try {
			invoiceDate = new Date();
			invoiceDate = sdf.parse(sdf.format(invoiceDate));  
			
			deliveryDate = new Date();
			deliveryDate = new Date(deliveryDate.getTime() + (1000 * 60 * 60 * 24));
			deliveryDate = sdf.parse(sdf.format(deliveryDate));
			
			paymentDueDate = new Date();
			paymentDueDate = new Date(paymentDueDate.getTime() + (1000 * 60 * 60 * 48));
			paymentDueDate = sdf.parse(sdf.format(paymentDueDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		order.setInvoiceCreationDate(invoiceDate);
		order.setDeliveryDueDate(deliveryDate);
		order.setPaymentDueDate(paymentDueDate);
		System.out.println("Enter any custome message : ");
		String message = sc.next();
		order.setCustomMessages(message);
		order.setCustomer(customer);
		for(Products product : productList) {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrders(order);
			orderProduct.setProducts(product);
			System.out.println("Enter quantity : ");
			int quantity = sc.nextInt();
			orderProduct.setQuantity(quantity);
			added = orderServiceImpl.addOrder(orderProduct);
		}
		
		if(added)
			System.out.println("+++++++++++++++Order Created+++++++++++++++");
		else
			System.out.println("+++++++++++++++Order Creation Failded+++++++++++++++");

		
	}
	
	private void deleteOrder(Scanner sc) {
		System.out.println("Enter Order ID : ");
		int id = sc.nextInt();
		try {
			if(orderServiceImpl.deleteOrder(id))
				System.out.println("Order with id "+id+" got deleted");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
		
	}
	
	private void updateOrder(Scanner sc) {
		Orders order = displayOrder(sc);
		System.out.println("Enter custome message : ");
		String message = sc.next();
		order.setCustomMessages(message);
		orderServiceImpl.updateOrder(order);
		
		
	}
	
	private Orders displayOrder(Scanner sc) {
		System.out.println("Enter Order ID : ");
		int id = sc.nextInt();
		return orderServiceImpl.loadOrder(id);
	}
	
	private void displayAllOrders() {
		List<Orders> list = orderServiceImpl.loadAllOrders();
		for(Orders order : list) {
			if(order != null)
				System.out.println(order);
		}
		
	}

	private void addCustomer(Scanner sc)throws Exception {
		Customers customer = enterCustomerDetails(sc);
		Address address = enterAddressDetails(sc);
		address.setCustomer(customer);
		customer.setAddress(address);
		try {
			if(customerServiceImpl.addCustomer(customer));
				System.out.println("+++++++++++++++Customer with Name : "+customer.getFirstName()+" added to the DB+++++++++++++++");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void deleteCustomer(Scanner sc)throws Exception {
		System.out.println("Enter Customer ID : ");
		int id = sc.nextInt();
		try {
			if(customerServiceImpl.deleteCustomer(id))
				System.out.println("+++++++++++++++Customer with id "+id+" got deleted+++++++++++++++");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void updateCustomer(Scanner sc)throws Exception {
		System.out.println("Enter Customer ID : ");
		int id = sc.nextInt();
		Customers customer = enterCustomerDetails(sc);
		customer.setId(id);
		try {
			if(customerServiceImpl.updateCustomer(customer))
				System.out.println("+++++++++++++++Customer with id "+id+" got updated+++++++++++++++");
		} catch (CustomException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private Customers displayCustomer(Scanner sc) {
		System.out.println("Enter Customer ID : ");
		int id = sc.nextInt();
		return customerServiceImpl.loadCustomer(id);
	}
	
	private List<Customers> displayAllCustomers() {
		return customerServiceImpl.loadAllCustomers();
	}
	
	private Customers enterCustomerDetails(Scanner sc) {
		
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
		System.out.println("Enter Other Details : ");
		String otherDetails=sc.next(); 
		
		Customers customer = new Customers(title,firstName,middleName,lastName,suffix,email,
				company,displayName,printOnCheckAs,otherDetails);
		
		return customer;
	}
	
	private Address enterAddressDetails(Scanner sc) {
		String shippingStreet = null,shippingCity = null,shippingState = null,shippingCountry = null;
		int shippingZip = 0;
		System.out.println("Enter Customer Billing street : ");
		String billingStreet = sc.next();
		System.out.println("Enter Customer Billing  city : ");
		String billingCity = sc.next();
		System.out.println("Enter Customer Billing  zip : ");
		int billingZip;
		try {
			billingZip = sc.nextInt();
		} catch (Exception e) {
			billingZip = 0;
		}
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
					try {
						billingZip=sc.nextInt();
					} catch (Exception e) {
						billingZip = 0;
					} 
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
		
		Address address = new Address();
		address.setBillingStreet(billingStreet);
		address.setBillingCity(billingCity);
		address.setBillingState(billingState);
		address.setBillingCountry(billingCountry);
		address.setBillingZip(billingZip);
		address.setShippingStreet(shippingStreet);
		address.setShippingCity(shippingCity);
		address.setShippingState(shippingState);
		address.setShippingCountry(shippingCountry);
		address.setShippingZip(shippingZip);
		
		return address;
		
	}
	
	private List<Products> selectProduct(Scanner sc) {
		List<Products> selectedProducts = new ArrayList<Products>();
		int addMore = 0;
		if(productList==null)
			productList = new ProductServiceImpl().loadAllProducts();
		do {
			System.out.println("Select Product : ");
			for(int i=0;i<productList.size();i++) {
				System.out.println(i+". "+productList.get(i));
			}
			int selectedProduct = sc.nextInt();
			selectedProducts.add(productList.get(selectedProduct));
			System.out.println("Do you want to add more products ?");
			System.out.println("1. YES");
			System.out.println("2. No");
			addMore = sc.nextInt();
		}while(addMore==1);
		
		return selectedProducts;
	}
	
	private void loadOrdersByCustomerId(Scanner sc) {
		int total = 0;
		Customers customer = displayCustomer(sc);
		List<Customers> list = orderServiceImpl.loadOrdersByCustomerId(customer);
		for(Customers cust : list) {
			System.out.println(cust);
			if(cust!=null) {
				Set<Orders> orderSet = cust.getOrders();
				for(Orders order : orderSet) {
					total = 0;
					System.out.println("====================Order ID : "+order.getOrderID()+" \"====================");
					Set<OrderProduct> orderProductSet = order.getOrderProducts();
					for(OrderProduct op : orderProductSet) {
						Products product = op.getProducts();
						System.out.println(product);
						total = total+product.getPrice();
					}
					System.out.println("+++++++Total Order Price: "+total+" ++++++++++++");
				}
			}
		}
	}
}
