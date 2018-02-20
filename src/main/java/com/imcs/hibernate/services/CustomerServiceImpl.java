package com.imcs.hibernate.services;

import com.imcs.hibernate.dao.CustomerDao;
import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.interfaces.CustomerDaoInterface;
import com.imcs.hibernate.interfaces.CustomerServiceInterface;

import trng.imcs.hib.excp.CustomException;

public class CustomerServiceImpl implements CustomerServiceInterface {
	
	CustomerDaoInterface customerDao = new CustomerDao();
	
	public boolean addCustomer(Customers customer) throws CustomException {
		return customerDao.addCustomer(customer);
	}

	public Customers loadCustomer(int id) throws CustomException {
		return customerDao.loadCustomer(id);
	}

	public boolean updateCustomer(Customers customer) throws CustomException {
		return customerDao.updateCustomer(customer);
	}

	public boolean deleteCustomer(int id) throws CustomException {
		return customerDao.deleteCustomer(id);
	}

}
