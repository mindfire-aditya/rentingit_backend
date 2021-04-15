package com.mindfire.rentingit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByPinCode(int pincode);
	
}
