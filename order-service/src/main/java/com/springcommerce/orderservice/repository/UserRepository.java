package com.springcommerce.orderservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcommerce.orderservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAllByUuidIn(List<UUID> uuids);
	@Modifying
	@Query(value = "update Users set deleted = true wheres uuid = :uuid", nativeQuery = true)
	void deleteByUuid(@Param("uuid") UUID uuid);
	Optional<User> findByUuid(UUID uuid);
}
