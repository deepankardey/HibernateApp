package com.imcs.hibernate.interfaces;

import java.util.List;

import com.imcs.hibernate.entity.Customers;
import com.imcs.hibernate.entity.OrderProduct;
import com.imcs.hibernate.entity.Orders;
import com.imcs.hibernate.pojo.OrderDetails;

import trng.imcs.hib.excp.CustomException;

public interface OrderDaoInterface {
	public boolean addOrder(OrderProduct orderProduct) throws CustomException;
	public Orders loadOrder(int id) throws CustomException;
	public boolean updateOrder(Orders order) throws CustomException;
	public boolean deleteOrder(int id) throws CustomException;
	public List<Orders> loadAllOrders()throws CustomException;
	public List<Customers> loadOrdersByCustomerId(Customers customer);
}
