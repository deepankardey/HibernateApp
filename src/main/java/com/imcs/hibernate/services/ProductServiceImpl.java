package com.imcs.hibernate.services;

import java.util.List;

import com.imcs.hibernate.dao.ProductDao;
import com.imcs.hibernate.entity.Products;
import com.imcs.hibernate.interfaces.ProductDaoInterface;
import com.imcs.hibernate.interfaces.ProductServiceInterface;

import trng.imcs.hib.excp.CustomException;

public class ProductServiceImpl implements ProductServiceInterface {
	
	ProductDaoInterface productDao = new ProductDao();

	@Override
	public List<Products> loadAllProducts() throws CustomException {
		return productDao.loadAllProducts();
	}

}
