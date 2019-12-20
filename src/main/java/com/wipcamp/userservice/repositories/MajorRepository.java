package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.Major;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long > {
}
