package net.kzn.onlineshopping.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.onlineshopping.dao.ProductDao;
import net.kzn.onlineshopping.entity.Category;
import net.kzn.onlineshopping.entity.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

	SessionFactory sf = (SessionFactory) new Configuration().configure("Hibernate.cfg.xml")
			.addAnnotatedClass(Product.class)
			.buildSessionFactory();
	
	
	@Override
	public Product get(int productId) {
		Session session=sf.openSession();
		// TODO Auto-generated method stub
		Product product=session.get(Product.class, productId);
		
		return product;
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		Query<Product> query=session.createQuery("from Product where active=:active",Product.class);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public boolean add(Product product) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
	try {
		session.persist(product);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}

	@Override
	public boolean update(Product product) {
		Session session=sf.openSession();
	try {
		session.update(product);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}

	@Override
	public boolean delete(Product product) {
		Session session=sf.openSession();
	try {
		session.delete(product);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}

	@Override
	public List<Product> listActiveProducts() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Session session=sf.openSession();
				Query<Product> query=session.createQuery("from Product where active=:active",Product.class);
				query.setParameter("active", true);
				return query.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		Query<Product> query=session.createQuery("from Product where active=:active and categoryId=:categoryId",Product.class);
		query.setParameter("active", true);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
	
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		Query<Product> query=session.createQuery("from Product where active=:active order by id",Product.class);
		query.setParameter("active", true);
	   query.setFirstResult(0);
	   query.setMaxResults(count);
		return query.getResultList();
	}

	@Override
	public List<Product> getProductsByParam(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
