package com.imcs.hibernate.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.entity.OrderProduct;
import com.imcs.hibernate.entity.Orders;
import com.imcs.hibernate.interfaces.OrderDaoInterface;
import com.imcs.hibernate.utils.HibernateUtil;

import trng.imcs.hib.excp.CustomException;

public class OrderDao implements OrderDaoInterface {
	
	

	private Session getSession() {
		return HibernateUtil.buildSessionFactory().openSession();
	}

	@Override
	public boolean addOrder(OrderProduct orderProduct) throws CustomException {
		boolean added = false;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(orderProduct);
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

	@Override
	public Orders loadOrder(int id) throws CustomException {
		Orders order = null;
		Session session = null;
		try {
			session = getSession();
			order = (Orders)session.get(Orders.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return order;
	}

	@Override
	public boolean updateOrder(Orders order) throws CustomException {
		boolean update = false;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(order);
			transaction.commit();
			update = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return update;
	}

	@Override
	public boolean deleteOrder(int id) throws CustomException {
		boolean delete = false;
		Session session = null;
		Transaction transaction = null;
		Orders order = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			order = (Orders)session.get(Orders.class, id);
			session.delete(order);
			transaction.commit();
			delete = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return delete;
	}

	@Override
	public List<Orders> loadAllOrders() throws CustomException {
		Session session = null;
		Query query = null;
		try {
			session = getSession();
			query = session.createQuery("From Orders");
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
	
	@Override
	public List<Customers> loadOrdersByCustomerId(Customers customer) {
		Session session = getSession();
		/*//Query query = session.createQuery("select orders.orderID,orders.invoiceCreationDate,orders.deliveryDueDate "//,sum(op.quantity*pro.price), pro
				Query query = session.createQuery("FROM Orders as o "//select new com.imcs.hibernate.pojo.OrderDetails(op,sum(op.quantity*op.products.price)) 
				+ "inner join o.orderProducts as op "
				+ "inner join op.products as pro"
				+ "where o.customer = :customer");
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		query.setParameter("customer", customer);
		List<Orders> list= query.list();
		return list;*/
		Criteria customerCriteria = session.createCriteria(Customers.class,"customer");
		Criteria orderCriteria = customerCriteria.createCriteria("orders", "order");
		Criteria orderProductCriteria = orderCriteria.createAlias("orderProducts", "op");
		orderProductCriteria.add(Restrictions.eq("customer", customer));
		/*try {
			Date d1 = new SimpleDateFormat("mm-dd-yyyy").parse("02-21-2018");
			Date d2 = new SimpleDateFormat("mm-dd-yyyy").parse("02-21-2018");
			orderCriteria.add(Restrictions.between("invoiceCreationDate",d1 ,d2));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		customerCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		/*ProjectionList orderProperties = Projections.projectionList();
		orderProperties.add(Projections.property("orderID"));
		orderProperties.add(Projections.property("invoiceCreationDate"));
		orderProperties.add(Projections.property("deliveryDueDate"));
		//orderProperties.add(Projections.property("orderProducts"));
		
		//ProjectionList opProperties = Projections.projectionList();
		//opProperties.add(Projections.property("products"));
		
		orderCriteria.setProjection(orderProperties);
		//orderProductCriteria.setProjection(opProperties);
*/		
		return customerCriteria.list();
				
		
	}
}
