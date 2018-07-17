package com.wissen.justhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;

public interface QuestionAskedRepository extends JpaRepository<QuestionsAsked, Integer> {

	void deleteBycandidate(Candidate candidate);
	List<QuestionsAsked> findByCandidate(Candidate candidate);
	List<QuestionsAsked> findByRound(Round round);
	
	
}
