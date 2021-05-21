package com.mindfire.rentingit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.rentingit.entity.Erole;
import com.mindfire.rentingit.entity.Role;

/**
 * @author ujjwalk
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(Erole name);
}
