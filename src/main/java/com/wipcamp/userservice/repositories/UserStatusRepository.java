package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.UserStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus,Integer> {
	Integer countByIsAccepted(boolean accepted);
	Integer countByIsRegistered(boolean registered);
	Integer countByIsGeneralAnswered(boolean generalAnswered);
	Integer countByIsMajorAnswered(boolean majorAnswered);
	Integer countByIsSubmitted(boolean submitted);
}
