/*
 * @author Ujjwal Kumar
 */
package com.mindfire.rentingit.services;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<?> addingProductDetails(ProductRegisterRequest productRegisterRequest) {

		// Create new userDetails info with required details
		Product newProducts = new Product(productRegisterRequest.getProductName(),
				productRegisterRequest.getMaintainanceTime(), productRegisterRequest.getAssetStatus(),
				productRegisterRequest.getAssetDescription(), productRegisterRequest.getImageUrl(),
				productRegisterRequest.getUnits(), productRegisterRequest.getPricePerHour(),
				productRegisterRequest.getPricePerDay(), productRegisterRequest.getPricePerWeek(),
				productRegisterRequest.getPricePerMonth(), productRegisterRequest.getPinCode(),
				productRegisterRequest.getCategoryId(), productRegisterRequest.getOwnerId());

		productRepository.save(newProducts);

		return ResponseEntity.ok(new MessageResponse(msg.PRODUCTS_ADDED));
	}

	// function for updating the products details
	public Product updatingProductDetails(ProductRegisterRequest productRegisterRequest, long productId) {

		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + productId));

		// updating the values
		existingProduct.setProductName(productRegisterRequest.getProductName());
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

	// method for deleting the registered products based upon id by user
	public ResponseEntity<?> deleteProductsById(long productId) {
		// checking for the request if it is made by the current loggedIn user or not

		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with this id :" + productId));

		//getting the ownerID of product
		long currentOwnerId = existingProduct.getOwnerId();
		

		User currentUser = customUserDetails.getCurrentUser();
		long currentUserId = currentUser.getId();

		boolean flag;
		if (currentUserId == currentOwnerId ) {
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

	// deleting the products by id for admin
	public ResponseEntity<?> deleteProducts(long productId) {
		// checking for the request if it is made by the current loggedIn user or not

		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with this id :" + productId));

		this.productRepository.delete(existingProduct);
		return ResponseEntity.ok(new MessageResponse(msg.PRODUCTS_DELETED));

	}
	
	//getting all products of currently loggedin user
	public List<Product> findProductsOfUser(){
		
		User currentUser = customUserDetails.getCurrentUser();
		long userId = currentUser.getId();
		
		int ownerID = (int)userId;
		
		List<Product> p = this.productRepository.findByOwnerId(ownerID);
		
		return p;
	}
}
