package net.kzn.onlineshopping.daoImpl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.onlineshopping.dao.CategoryDao;
import net.kzn.onlineshopping.entity.Category;


@Repository("CategoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	
	SessionFactory sf = (SessionFactory) new Configuration().configure("Hibernate.cfg.xml")
			.addAnnotatedClass(Category.class)
			.buildSessionFactory();
	
	
	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
	Category category=session.get(Category.class, id);
	return category;
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		
		Query<Category> query=session.createQuery("from Category where active=:active",Category.class);
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	@Override
	public boolean add(Category category) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		
		try {
			session.persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
	try {
		session.update(category);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
	try {
		session.delete(category);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}

}
