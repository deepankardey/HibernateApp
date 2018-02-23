package com.imcs.hibernate.services;

import java.util.List;

import com.imcs.hibernate.dao.OrderDao;
import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.entity.OrderProduct;
import com.imcs.hibernate.entity.Orders;
import com.imcs.hibernate.interfaces.OrderDaoInterface;
import com.imcs.hibernate.interfaces.OrderServiceInterface;

import trng.imcs.hib.excp.CustomException;

public class OrderServiceImpl implements OrderServiceInterface {
	
	OrderDaoInterface orderDao = new OrderDao();

	@Override
	public boolean addOrder(OrderProduct orderProduct) throws CustomException {
		return orderDao.addOrder(orderProduct);
	}

	@Override
	public Orders loadOrder(int id) throws CustomException {
		return orderDao.loadOrder(id);
	}

	@Override
	public boolean updateOrder(Orders order) throws CustomException {
		return orderDao.updateOrder(order);
	}

	@Override
	public boolean deleteOrder(int id) throws CustomException {
		return orderDao.deleteOrder(id);
	}

	@Override
	public List<Orders> loadAllOrders() throws CustomException {
		return orderDao.loadAllOrders();
	}

	@Override
	public List<Customers> loadOrdersByCustomerId(Customers customer) throws CustomException {
		return orderDao.loadOrdersByCustomerId(customer);
	}
	

}
