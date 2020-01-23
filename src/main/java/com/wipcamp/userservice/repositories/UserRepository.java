package com.wipcamp.userservice.repositories;

import com.wipcamp.userservice.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	public Optional<User> findByLineId(long lineId);

	@Query(value = "SELECT * FROM users WHERE DATE(created_at) = :date", nativeQuery=true)
	public List<Integer> findDailyUser(@Param("date") String date);

	@Query(value = "SELECT * FROM users WHERE DATE(created_at) = :date AND HOUR(created_at) = :hour", nativeQuery=true)
	List<User> findUserPerHour(@Param("date") String date , @Param("hour") String hour);
}
