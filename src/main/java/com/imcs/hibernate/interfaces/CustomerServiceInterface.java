package com.imcs.hibernate.interfaces;

import java.util.List;

import com.imcs.hibernate.entity.Customers;

import trng.imcs.hib.excp.CustomException;

public interface CustomerServiceInterface {

	public boolean addCustomer(Customers customer) throws CustomException;
	public Customers loadCustomer(int id) throws CustomException;
	public boolean updateCustomer(Customers customer) throws CustomException;
	public boolean deleteCustomer(int id) throws CustomException;
	public List<Customers> loadAllCustomers()throws CustomException;
}
