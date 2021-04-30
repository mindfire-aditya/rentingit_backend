package com.mindfire.rentingit.services;

import org.springframework.stereotype.Service;

import com.mindfire.rentingit.dto.request.OrderRequest;
import com.mindfire.rentingit.repository.OrderRepository;
import com.mindfire.rentingit.repository.ProductRepository;
import com.mindfire.rentingit.repository.UserRepository;

@Service
public class AddOrder {

	OrderRepository orderRepository;
	
	UserRepository userRepository;
	
	ProductRepository productRepository;
	
	public String addNewOrder(OrderRequest order) {
		
		return "Order Placed Successfully";
	}
}
