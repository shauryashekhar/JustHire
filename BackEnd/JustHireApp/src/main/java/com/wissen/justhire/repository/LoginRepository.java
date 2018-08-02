package com.wissen.justhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wissen.justhire.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	Login findByEmail(String email);
	
//	@Query("delete from login")
//	void delet();
}
