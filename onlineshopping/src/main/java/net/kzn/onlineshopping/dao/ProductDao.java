package net.kzn.onlineshopping.dao;

import java.util.List;

import net.kzn.onlineshopping.entity.Product;

public interface ProductDao {

	Product get(int productId);
	List<Product> list();	
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);

	// business methods
	List<Product> listActiveProducts();	
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	List<Product> getProductsByParam(String string, int i);
}
