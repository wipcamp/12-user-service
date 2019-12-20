package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

	@Query("FROM Question q where q.major.id = :majorId")
	List<Question> findAllQuestionByMajorId(@Param("majorId") Long majorId);

}
