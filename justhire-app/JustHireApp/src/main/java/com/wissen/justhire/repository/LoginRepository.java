package com.wissen.justhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.justhire.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	Login findByEmail(String email);
	

}
