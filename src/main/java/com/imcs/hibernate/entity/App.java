package com.imcs.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imcs.hibernate.utils.HibernateUtil;

public class App {
	public static void main(String[] args) {
		
		Session session = HibernateUtil.buildSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Orders order1 = new Orders();
		order1.setCustomMessages("order 1");
		Customers customer1 = new Customers();
		customer1.setFirstName("Deepankar");
		customer1.setMiddleName("kumar");
		customer1.setLastName("Dey");
		customer1.setEmail("Dey@gmail.com");
		Address address = new Address();
		address.setBillingCity("TX");
		address.setCustomer(customer1);
		customer1.setAddress(address);
		order1.setCustomer(customer1);
		//session.save(customer1);
		//transaction.commit();
		
		Orders order2 = new Orders();
		order2.setCustomMessages("order 2");
		Customers customer2 = new Customers();
		customer2.setFirstName("Nikhil");
		customer2.setMiddleName("kumar");
		customer2.setLastName("Nikhil");
		customer2.setEmail("Nikhil@gmail.com");
		Address address1 = new Address();
		address1.setBillingCity("NJ");
		address1.setCustomer(customer2);
		order2.setCustomer(customer2);
		customer2.setAddress(address1);
		
		Products pen = new Products();
		pen.setName("pen");
		pen.setDescription("pen");
		pen.setPrice(10);
		
		Products pencil = new Products();
		pencil.setName("pencil");
		pencil.setDescription("pencil");
		pencil.setPrice(5);
		
		Products rubber = new Products();
		rubber.setName("rubber");
		rubber.setDescription("rubber");
		rubber.setPrice(5);
		
/*		session.save(order1);
		session.save(order2);*/
		session.save(pen);
		session.save(pencil);
		session.save(rubber);
		
		OrderProduct orderProduct1 = new OrderProduct();
		orderProduct1.setOrders(order1);
		orderProduct1.setProducts(pen);
		orderProduct1.setQuantity(2);
		session.save(orderProduct1);
		
		OrderProduct orderProduct2 = new OrderProduct();
		orderProduct2.setOrders(order1);
		orderProduct2.setProducts(pencil);
		orderProduct2.setQuantity(2);
		 
		session.save(orderProduct2);
		
		
		OrderProduct orderProduct3 = new OrderProduct();
		orderProduct3.setOrders(order1);
		orderProduct3.setProducts(rubber);
		orderProduct3.setQuantity(1);
		 
		session.save(orderProduct3);
		
		transaction.commit();
		
		/*// this user is obtained from the database with ID 34
		User user1 = (User) session.get(User.class, new Long(34));
		 
		// this group is obtained from the database with ID 17
		Group group1 = (Group) session.get(Group.class, new Long(17));*/
		
		/*User user1 = new User("dee", "dee", "dee@gmail.com"); 
		UserGroup userGroup1 = new UserGroup();
		userGroup1.setGroup(group);
		userGroup1.setUser(user1);
		userGroup1.setActivated(true);
		userGroup1.setRegisteredDate(new Date());
		 
		session.save(userGroup1);
		
		transaction.commit();
		UserGroup userGroup2 = new UserGroup();*/
		/*orderProduct.setId(3);*/
		/*Transaction transaction1 = session.beginTransaction();
		session.delete(customer2);
		transaction1.commit();*/
	}
}
