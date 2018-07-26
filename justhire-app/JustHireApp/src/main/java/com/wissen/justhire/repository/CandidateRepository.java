package com.wissen.justhire.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.StatusType;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
	List<Candidate> findByStatus(String status);

	@Modifying
	@Query("update Candidate c set c.status =:status, c.score=:score where c.candidateId =:id  ") // write query
	void updateStatusAndScore(@Param("status") StatusType status, @Param("score") float score, @Param("id")  int id);

	int countByStatus(StatusType statusType);

}
