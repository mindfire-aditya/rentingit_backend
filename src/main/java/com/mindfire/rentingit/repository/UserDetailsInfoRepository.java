package com.mindfire.rentingit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mindfire.rentingit.entity.UserDetailsInfo;

public interface UserDetailsInfoRepository extends JpaRepository<UserDetailsInfo, Long> {
	
	Optional<UserDetailsInfo> findById(long id);

}
