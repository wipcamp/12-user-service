package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

}
