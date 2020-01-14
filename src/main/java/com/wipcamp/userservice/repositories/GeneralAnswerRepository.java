package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.GeneralAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralAnswerRepository extends JpaRepository<GeneralAnswer,Integer> {
}
