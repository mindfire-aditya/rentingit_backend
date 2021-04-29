/*
 * @author Ujjwal Kumar
 */
package com.mindfire.rentingit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.rentingit.entity.Order;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.OrderRepository;
import com.mindfire.rentingit.services.AddOrder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	AddOrder addOrder;

	// get all orders
	@GetMapping("/all")
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	// get order by id
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable(value = "id") long orderId) {
		return this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
	}

	// update order details
	@PutMapping("/{id}")
	public Order updateOrder(@RequestBody Order order, @PathVariable("id") long orderId) {
		Order existingOrder = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
		existingOrder.setRentStartDate(existingOrder.getRentStartDate());
		existingOrder.setRentEndDate(existingOrder.getRentEndDate());
		existingOrder.setRentMode(existingOrder.getRentMode());
		existingOrder.setTotalAmount(existingOrder.getTotalAmount());

		return this.orderRepository.save(existingOrder);
	}

	// delete order by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteOder(@PathVariable("id") long orderId) {
		Order existingOrder = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
		this.orderRepository.delete(existingOrder);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/new-order")
	public String placeOrder(@RequestBody Order newOrder) {
		return  addOrder.addNewOrder();
	}

}
