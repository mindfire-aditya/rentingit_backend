package com.mindfire.rentingit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.entity.UserDetailsInfo;

@Repository
public interface UserDetailsInfoRepository extends JpaRepository<UserDetailsInfo, Long> {

}
