package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.KnowWhence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface knowWhenceRepository extends JpaRepository <KnowWhence,Integer> {
}
