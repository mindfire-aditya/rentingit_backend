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

import com.mindfire.rentingit.dto.request.OrderRequest;
import com.mindfire.rentingit.entity.Order;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.OrderRepository;
import com.mindfire.rentingit.services.AddOrder;

/**
 * @author ujjwalk
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	AddOrder addOrder;

	/**
	 * get all orders
	 * 
	 * @return list of orders
	 */
	@GetMapping("/all")
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	/**
	 * @param cId
	 * @return get the orders by customer id
	 */
	@GetMapping("/all/{id}")
	public List<Order> getAllCustomOders(@PathVariable(value = "id") int cId) {
		return this.orderRepository.findByCustomerId(cId);
	}

	/**
	 * get order by id
	 * 
	 * @param orderId
	 * @return order
	 */
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable(value = "id") long orderId) {
		return this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
	}

	/**
	 * update order details
	 * 
	 * @param order
	 * @param orderId
	 * @return updated order
	 */
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

	/**
	 * delete order by id
	 * 
	 * @param orderId
	 * @return success msg on deletion
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteOder(@PathVariable("id") long orderId) {
		Order existingOrder = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
		this.orderRepository.delete(existingOrder);
		return ResponseEntity.ok().build();
	}

	/**
	 * @param addNewOrder
	 * @return success msg on addition of orders
	 */
	@PostMapping(value = "/new-order")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest addNewOrder) {
		return addOrder.addNewOrder(addNewOrder);
	}

}
