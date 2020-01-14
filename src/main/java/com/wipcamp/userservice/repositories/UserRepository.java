package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	public Optional<User> findByLineId(long lineId);
}
