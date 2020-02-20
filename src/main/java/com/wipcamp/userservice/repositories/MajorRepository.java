package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.Major;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long > {
	Optional<Major> findByName(@NotNull String name);
}
