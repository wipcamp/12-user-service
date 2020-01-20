package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.User;

import com.wipcamp.userservice.reponses.GraphDailyResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	public Optional<User> findByLineId(long lineId);

	public List<User> findByCreatedAt(Date thisDate);
}
