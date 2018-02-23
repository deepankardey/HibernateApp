package com.imcs.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.imcs.hibernate.entity.Products;
import com.imcs.hibernate.interfaces.ProductDaoInterface;
import com.imcs.hibernate.utils.HibernateUtil;

import trng.imcs.hib.excp.CustomException;

public class ProductDao implements ProductDaoInterface {
	
	private Session getSession() {
		return HibernateUtil.buildSessionFactory().openSession();
	}

	@Override
	public List<Products> loadAllProducts() throws CustomException {
		Session session = null;
		Query query = null;
		try {
			session = getSession();
			query = session.createQuery("From Products");
			query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return query.list();
	}

	
}
