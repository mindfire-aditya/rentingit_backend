package com.mindfire.rentingit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.model.Category;
import com.mindfire.rentingit.model.Product;
import com.mindfire.rentingit.repository.CategoryRepository;
import com.mindfire.rentingit.repository.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// get all Category
	@GetMapping("category/all")
	public List<Category> getAllCategory() {
		return this.categoryRepository.findAll();
	}
	
	// get all Products
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}
	
    // get product by id
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable (value = "id") long productId) {
		return this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + productId));
	}
	
   // get product by pincode
	@GetMapping("/pincode/{pincode}")
	public Optional<Product> getProductByPincode(@PathVariable (value = "pincode") int pincode) {
		
			return productRepository.findByPinCode(pincode);

	}
	
	// get product by category id
	@GetMapping("/category/{id}")
	public Category getProductByCategoryId(@PathVariable (value = "id") long productId) {
		return this.categoryRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id :" + productId));
	}
	
}
