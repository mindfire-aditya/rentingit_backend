package com.mindfire.rentingit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.request.ProductRegisterRequest;

import com.mindfire.rentingit.dto.response.MessageResponse;
import com.mindfire.rentingit.entity.Product;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.ProductRepository;

@Service
public class AddProducts {

	@Autowired
	ProductRepository productRepository;
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

	// method for deleting the registered products based upon id
	public ResponseEntity<Product> deleteProductsById(long productId) {
		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with this id :" + productId));
		this.productRepository.delete(existingProduct);
		return ResponseEntity.ok().build();
	}

}
