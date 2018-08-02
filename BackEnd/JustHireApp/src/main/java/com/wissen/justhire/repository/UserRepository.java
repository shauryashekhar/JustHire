package com.wissen.justhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByuserId(int userId);

	List<User> findAll();

	User findByEmail(String email);

	List<User> findByRound(Round round);

	List<User> findByFirstName(String firstName);

	@Query("select U.round from User U where U.userId=:id") // write query
	Round findUserRoundbyId(@Param("id") int id);
	
	
	@Modifying
	@Query("update User u set u.round=-1 where u.round>:round")
	void setRoundNull(@Param("round") int round);
	
	@Query("select count(u.round) from User u where u.round=-1")
	List<QuestionsAsked> ifNullPresent();
	
	@Query("select round from User u where round in (select distinct u.round from User u) and round>0")
	List<Integer> getDistinctRoundfromUser();

}
