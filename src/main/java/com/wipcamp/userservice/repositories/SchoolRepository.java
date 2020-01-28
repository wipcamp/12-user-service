package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.School;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {
}
