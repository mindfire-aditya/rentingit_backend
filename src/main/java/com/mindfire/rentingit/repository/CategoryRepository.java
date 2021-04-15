package com.mindfire.rentingit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
}
