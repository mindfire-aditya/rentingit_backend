package com.mindfire.rentingit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.request.ProductRegisterRequest;

import com.mindfire.rentingit.dto.response.MessageResponse;
import com.mindfire.rentingit.entity.Product;
import com.mindfire.rentingit.repository.ProductRepository;

@Service
public class AddProducts {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	Message msg;
	
	// function for adding the user extra details in DB
		public ResponseEntity<?> addingProductDetails(ProductRegisterRequest productRegisterRequest) {

			// Create new userDetails info with required details
			Product newProducts = new Product(
											   productRegisterRequest.getProductName(),
											   productRegisterRequest.getMaintainanceTime(),
											   productRegisterRequest.getAssetStatus(),
											   productRegisterRequest.getAssetDescription(),
											   productRegisterRequest.getImageUrl(),
											   productRegisterRequest.getUnits(),
											   productRegisterRequest.getPricePerHour(),
											   productRegisterRequest.getPricePerDay(),
											   productRegisterRequest.getPricePerWeek(),
											   productRegisterRequest.getPricePerMonth(),
											   productRegisterRequest.getPinCode(),
											   productRegisterRequest.getCategoryId(),
											   productRegisterRequest.getOwnerId());

			productRepository.save(newProducts);

			return ResponseEntity.ok(new MessageResponse(msg.PRODUCTS_ADDED));
		}

}
