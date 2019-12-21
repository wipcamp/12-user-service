package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

	User findByLineId(Long lineId);
}
