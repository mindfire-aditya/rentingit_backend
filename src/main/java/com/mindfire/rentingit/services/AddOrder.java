package com.mindfire.rentingit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.request.OrderRequest;
import com.mindfire.rentingit.dto.response.MessageResponse;
import com.mindfire.rentingit.entity.Order;
import com.mindfire.rentingit.entity.Product;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.OrderRepository;
import com.mindfire.rentingit.repository.ProductRepository;
import com.mindfire.rentingit.repository.UserRepository;

@Service
public class AddOrder {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CustomUserDetailService customUserDetails;
	@Autowired
	Message msg;


	// function for adding the product details in DB
		public ResponseEntity<?> addNewOrder(OrderRequest order) {
			
			long productId = order.getProductId();
			
			Product existingProduct = this.productRepository.findById(productId)
					.orElseThrow(() -> new ResourceNotFoundException("Product not found with this id :" + productId));
			
			//getting the ownerID of product
			long currentOwnerId = existingProduct.getOwnerId();
			

			User currentUser = customUserDetails.getCurrentUser();
			long currentUserId = currentUser.getId();
			

			// Create new product info with required details
			Order newOrder = new Order((int)currentOwnerId,(int)currentUserId,(int)productId,order.getStart_datetime(),
										order.getEnd_datetime(),order.getRent_mode(),order.isAgreedToTermsAndConditions(),
										order.getTotal_amount(),order.getUnits());
					
			
			orderRepository.save(newOrder);
			existingProduct.setUnits((existingProduct.getUnits() - newOrder.getUnits()));
			productRepository.save(existingProduct);

			return ResponseEntity.ok(new MessageResponse(msg.ORDER_ADDED));
		}
}
