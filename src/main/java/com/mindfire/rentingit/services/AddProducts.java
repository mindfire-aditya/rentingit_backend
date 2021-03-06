package com.mindfire.rentingit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.request.ProductRegisterRequest;

import com.mindfire.rentingit.dto.response.MessageResponse;
import com.mindfire.rentingit.entity.Product;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.ProductRepository;
import com.mindfire.rentingit.repository.UserRepository;

/**
 * @author ujjwalk
 *
 */
@Service
public class AddProducts {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomUserDetailService customUserDetails;
	@Autowired
	Message msg;

	// function for adding the product details in DB

	/**
	 * @param productRegisterRequest
	 * @return response entity
	 */
	public ResponseEntity<?> addingProductDetails(ProductRegisterRequest productRegisterRequest) {

		// Create new product info with required details
		Product newProducts = new Product(productRegisterRequest.getProductName(),
				productRegisterRequest.getActualName(), productRegisterRequest.getMaintainanceTime(),
				productRegisterRequest.getAssetStatus(), productRegisterRequest.getAssetDescription(),
				productRegisterRequest.getImageUrl(), productRegisterRequest.getUnits(),
				productRegisterRequest.getPricePerHour(), productRegisterRequest.getPricePerDay(),
				productRegisterRequest.getPricePerWeek(), productRegisterRequest.getPricePerMonth(),
				productRegisterRequest.getPinCode(), productRegisterRequest.getCategoryId(),
				productRegisterRequest.getParentCategoryId(), productRegisterRequest.getOwnerId());

		productRepository.save(newProducts);

		return ResponseEntity.ok(new MessageResponse(msg.PRODUCTS_ADDED));
	}

	// function for updating the products details

	/**
	 * @param productRegisterRequest
	 * @param productId
	 * @return updated product
	 */
	public Product updatingProductDetails(ProductRegisterRequest productRegisterRequest, long productId) {

		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + productId));

		// updating the values
		existingProduct.setProductName(productRegisterRequest.getProductName());
		existingProduct.setActualName(productRegisterRequest.getActualName());
		existingProduct.setMaintainanceTime(productRegisterRequest.getMaintainanceTime());
		existingProduct.setAssetStatus(productRegisterRequest.getAssetStatus());
		existingProduct.setAssetDescription(productRegisterRequest.getAssetDescription());
		existingProduct.setImageUrl(productRegisterRequest.getImageUrl());
		existingProduct.setUnits(productRegisterRequest.getUnits());
		existingProduct.setPricePerHour(productRegisterRequest.getPricePerHour());
		existingProduct.setPricePerDay(productRegisterRequest.getPricePerDay());
		existingProduct.setPricePerWeek(productRegisterRequest.getPricePerWeek());
		existingProduct.setPricePerMonth(productRegisterRequest.getPricePerMonth());
		existingProduct.setPinCode(productRegisterRequest.getPinCode());
		existingProduct.setCategoryId(productRegisterRequest.getCategoryId());
		existingProduct.setOwnerId(productRegisterRequest.getOwnerId());

		return this.productRepository.save(existingProduct);

	}

	/**
	 * method for deleting the registered products based upon id by user
	 * 
	 * @param productId
	 * @return
	 */
	public ResponseEntity<?> deleteProductsById(long productId) {
		// checking for the request if it is made by the current loggedIn user or not

		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with this id :" + productId));

		// getting the ownerID of product
		long currentOwnerId = existingProduct.getOwnerId();

		User currentUser = customUserDetails.getCurrentUser();
		long currentUserId = currentUser.getId();

		boolean flag;
		if (currentUserId == currentOwnerId) {
			flag = true;

		} else {
			flag = false;
		}

		if (flag == true) {
			this.productRepository.delete(existingProduct);
			return ResponseEntity.ok(new MessageResponse(msg.PRODUCTS_DELETED));
		} else {
			return ResponseEntity.ok(new MessageResponse(msg.CANT_DELETE_PROD));
		}
	}

	/**
	 * deleting the products by id for admin
	 * 
	 * @param productId
	 * @return
	 */
	public ResponseEntity<?> deleteProducts(long productId) {
		// checking for the request if it is made by the current loggedIn user or not

		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with this id :" + productId));

		this.productRepository.delete(existingProduct);
		return ResponseEntity.ok(new MessageResponse(msg.PRODUCTS_DELETED));

	}

	//

	/**
	 * getting all products of currently loggedin user
	 * 
	 * @return list of products
	 */
	public List<Product> findProductsOfUser() {

		User currentUser = customUserDetails.getCurrentUser();
		long userId = currentUser.getId();

		int ownerID = (int) userId;

		List<Product> p = this.productRepository.findByOwnerId(ownerID);

		return p;
	}
}
