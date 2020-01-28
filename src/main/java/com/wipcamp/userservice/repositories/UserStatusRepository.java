package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.UserStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus,Integer> {
}
