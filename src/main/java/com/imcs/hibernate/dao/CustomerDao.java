package com.imcs.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.interfaces.CustomerDaoInterface;
import com.imcs.hibernate.utils.HibernateUtil;

import trng.imcs.hib.excp.CustomException;

public class CustomerDao implements CustomerDaoInterface {
	
	public boolean addCustomer(Customers customer) throws CustomException {
		boolean added = false;
		Session session  = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(customer);
			transaction.commit();
			added = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return added;
	}

	public Customers loadCustomer(int id) throws CustomException {
		Session session = getSession();
		Customers customer = (Customers)session.get(Customers.class, id);
		session.close();
		return customer;
	}

	public boolean updateCustomer(Customers customer) throws CustomException {
		boolean updated = false;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(customer);
			transaction.commit();
			updated = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return updated;
	}

	public boolean deleteCustomer(int id) throws CustomException {
		boolean deleted = false;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Customers customer = (Customers)session.get(Customers.class, id);
			session.delete(customer);
			transaction.commit();
			deleted = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return deleted;
	}
	
	private Session getSession() {
		return HibernateUtil.buildSessionFactory().openSession();
	}

}
