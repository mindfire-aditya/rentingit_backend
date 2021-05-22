package com.mindfire.rentingit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.rentingit.dto.request.ProductRegisterRequest;
import com.mindfire.rentingit.entity.Category;
import com.mindfire.rentingit.entity.Product;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.CategoryRepository;
import com.mindfire.rentingit.repository.ProductRepository;
import com.mindfire.rentingit.services.AddProducts;

/**
 * @author ujjwalk
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	AddProducts addProducts;

	/**
	 * method for registering user products for rent
	 * 
	 * @param productRegisterRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/register-products-for-rent")
	public ResponseEntity<?> registerProducts(@RequestBody ProductRegisterRequest productRegisterRequest)
			throws Exception {
		return addProducts.addingProductDetails(productRegisterRequest);
	}

	/**
	 * method for updating the registered product for rent
	 * 
	 * @param productRegisterRequest
	 * @param productId
	 * @return added products
	 * @throws Exception
	 */
	@PutMapping(value = "/update-registered-products/{id}")
	public Product updateRegisteredProductDetails(@RequestBody ProductRegisterRequest productRegisterRequest,
			@PathVariable("id") long productId) throws Exception {
		return addProducts.updatingProductDetails(productRegisterRequest, productId);

	}

	/**
	 * method for deleting the registered products based upon owner id and product
	 * id
	 * 
	 * @param productId
	 * @return delete msg
	 */
	@DeleteMapping("/delete-owner-registered-product-by-id/{productId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> deleteProducts(@PathVariable("productId") long productId) {

		return addProducts.deleteProductsById(productId);

	}

	/**
	 * method for deleting the registered products based upon owner id and product
	 * id
	 * 
	 * @param productId
	 * @return
	 */
	@DeleteMapping("/delete-registered-product-by-id/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProductsByID(@PathVariable("productId") long productId) {

		return addProducts.deleteProducts(productId);

	}

	/**
	 * get all Category
	 * 
	 * @return all category list
	 */
	@GetMapping("category/all")
	public List<Category> getAllCategory() {
		return this.categoryRepository.findAll();
	}

	/**
	 * get all Products
	 * 
	 * @return list of all products
	 */
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	/**
	 * get product by id
	 * 
	 * @param productId
	 * @return product
	 */
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable(value = "id") long productId) {
		return this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + productId));
	}

	/**
	 * get product by pincode
	 * 
	 * @param pincode
	 * @return product
	 */
	@GetMapping("/pincode/{pincode}")
	public Optional<Product> getProductByPincode(@PathVariable(value = "pincode") int pincode) {

		return productRepository.findByPinCode(pincode);

	}

	/**
	 * get product by category id
	 * 
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/category/id/{id}")
	public List<Product> getProductByCategoryId(@PathVariable(value = "id") int categoryId) {
		return this.productRepository.findByCategoryId(categoryId);
	}

	/**
	 * get product by parent category id
	 * 
	 * @param parentCategoryId
	 * @return list of products
	 */
	@GetMapping("/category/parentId/{id}")
	public List<Product> getProductByParentCategoryId(@PathVariable(value = "id") int parentCategoryId) {
		return this.productRepository.findByParentCategoryId(parentCategoryId);
	}

	/**
	 * get product owned by user
	 * 
	 * @return list of products owned by user
	 */
	@GetMapping("/currently-loggedin")
	public List<Product> getProductsOwnedByCurrentUser() {
		return addProducts.findProductsOfUser();

	}

	/**
	 * get the product based upon Parent category name
	 * 
	 * @param parentCategoryName
	 * @return list of child category
	 */
	@GetMapping("/category/parentcategory/{parentCategoryName}")
	public List<Category> getProductByParentCategoryName(
			@PathVariable(value = "parentCategoryName") String parentCategoryName) {
		return (List<Category>) this.categoryRepository.findByParentCategory(parentCategoryName);
		// .orElseThrow(() -> new ResourceNotFoundException("Parent category not found
		// with name as ::"+ parentCategoryName));

	}

	/**
	 * get the category name based upon child category name
	 * 
	 * @param childCategoryName
	 * @return products
	 */
	@GetMapping("/category/childcategory/{childCategoryName}")
	public Category getProductByChildCategoryName(@PathVariable(value = "childCategoryName") String childCategoryName) {
		return this.categoryRepository.findByChildCategory(childCategoryName).orElseThrow(
				() -> new ResourceNotFoundException("Child category not found with name as ::" + childCategoryName));

	}

	/**
	 * get the product based upon the name
	 * 
	 * @param productName
	 * @return products
	 */
	@GetMapping("/search/{productName}")
	public List<Product> getProductsByProductName(@PathVariable(value = "productName") String productName) {
		return productRepository.findByProductName(productName);
	}

}
