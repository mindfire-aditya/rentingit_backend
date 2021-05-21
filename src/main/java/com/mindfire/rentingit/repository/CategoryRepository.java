package com.mindfire.rentingit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.entity.Category;

/**
 * @author ujjwalk
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByParentCategory(String parentCategoryName);

	Optional<Category> findByChildCategory(String childCategoryName);

}
