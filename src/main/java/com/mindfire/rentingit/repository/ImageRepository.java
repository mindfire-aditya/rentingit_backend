package com.mindfire.rentingit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
