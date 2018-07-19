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

	@Query("select count(*) from QuestionsAsked A where A.candidate=:candidate And A.question=:question ")
	int questionExists(@Param("candidate") Candidate candidate, @Param("question") Question question);
	
	@Query("from QuestionsAsked a where a.candidate=:candidate order by askedId Desc")
	List<QuestionsAsked> getPreviousQuestion(@Param("candidate") Candidate candidate);
	
	
}
