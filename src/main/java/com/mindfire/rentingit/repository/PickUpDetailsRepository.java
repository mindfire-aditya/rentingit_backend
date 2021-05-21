package com.mindfire.rentingit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.entity.PickUpDetails;

/**
 * @author ujjwalk
 *
 */
@Repository
public interface PickUpDetailsRepository extends JpaRepository<PickUpDetails, Long> {

	Optional<PickUpDetails> findByAssetId(int assetId);
}
