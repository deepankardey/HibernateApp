package com.imcs.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
		Session session = null;
		Customers customer = null;
		try {
			session = getSession();
			customer = (Customers)session.get(Customers.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
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
		}finally {
			if(session!=null)
				session.close();
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
		}finally {
			if(session!=null)
				session.close();
		}
		return deleted;
	}
	

	@Override
	public List<Customers> loadAllCustomers() throws CustomException {
		Session session = null;
		Query query = null;
		try {
			session = getSession();
			query = session.createQuery("FROM Customers");
			query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return query.list();
	}

	private Session getSession() {
		return HibernateUtil.buildSessionFactory().openSession();
	}
}
