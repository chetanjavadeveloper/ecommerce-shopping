package net.kzn.onlineshopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import net.kzn.onlineshopping.dao.CategoryDao;
import net.kzn.onlineshopping.dao.ProductDao;
import net.kzn.onlineshopping.entity.Category;
import net.kzn.onlineshopping.entity.Product;
import net.kzn.onlineshopping.exception.ProductNotFoundException;






@Controller
public class PageController {
	private static final Logger logger=LoggerFactory.getLogger(PageController.class);
	
	
	@Autowired
	CategoryDao categoryDao;

	@Autowired
	private ProductDao productDAO;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","Home");
		mv.addObject("categories", categoryDao.list());
		mv.addObject("userClickHome",true);
		logger.info("comes to home page");
		return mv;				
	}


	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","All Products");
		
		//passing the list of categories
		mv.addObject("categories", categoryDao.list());
		
		mv.addObject("userClickAllProducts",true);
		return mv;				
	}	
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {		
		ModelAndView mv = new ModelAndView("page");
		
		// categoryDAO to fetch a single category
		Category category = null;
		
		category = categoryDao.get(id);
		
		mv.addObject("title",category.getName());
		
		//passing the list of categories
		mv.addObject("categories", categoryDao.list());
		
		// passing the single category object
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;				
	}	
	

	/*
	 * Viewing a single product
	 * */
	
	@RequestMapping(value = "/show/{id}/product") 
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		//---------------------------
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProduct", true);
		
		
		return mv;
		
	}
	
	
}
