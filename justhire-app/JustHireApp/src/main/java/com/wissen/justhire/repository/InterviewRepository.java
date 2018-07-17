package com.wissen.justhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.justhire.model.Interview;

public interface InterviewRepository extends  JpaRepository<Interview, Integer> {
	

}
