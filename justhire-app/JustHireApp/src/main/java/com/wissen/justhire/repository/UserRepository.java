package com.wissen.justhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByuserId(int userId);

	List<User> findAll();

	List<User> findByRound(Round round);

	List<User> findByFirstName(String firstName);

	User save(User user);
}
