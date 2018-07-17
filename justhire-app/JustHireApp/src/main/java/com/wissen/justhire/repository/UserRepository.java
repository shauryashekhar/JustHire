package com.wissen.justhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByuserId(int userId);

	List<User> findAll();

	List<User> findByRound(Round round);

	List<User> findByFirstName(String firstName);
	
	@Query("select U.round from User U where U.userId=:id") // write query
	Round findUserRoundbyId(@Param("id") int id);

	
}