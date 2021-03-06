package com.mindfire.rentingit.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.entity.Order;

/**
 * @author ujjwalk
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByProductId(int productId);
	
	List<Order> findByOwnerId(int ownerId);

	List<Order> findByCustomerId(int customerId);
	
}
