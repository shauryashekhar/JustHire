package com.wissen.justhire.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionAskedRepository extends JpaRepository<QuestionsAsked, Integer> {

	void deleteBycandidate(Candidate candidate);

	List<QuestionsAsked> findByCandidate(Candidate candidate);

	List<QuestionsAsked> findByRound(Round round);

	@Query("from QuestionsAsked A where A.candidate=:candidate And A.question=:question ")
	QuestionsAsked questionExists(@Param("candidate") Candidate optional, @Param("question") Question optional2);
}
