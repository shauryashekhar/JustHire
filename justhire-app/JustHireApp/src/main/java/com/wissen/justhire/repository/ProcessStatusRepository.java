package com.wissen.justhire.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.ProcessStatus;
import com.wissen.justhire.model.Round;

@Transactional
public interface ProcessStatusRepository extends JpaRepository<ProcessStatus, Integer> {

	List<ProcessStatus> findByroundId(int id);

	List<ProcessStatus> findByStatus(String status);

	@Modifying
	@Query("update ProcessStatus  p set p.status=:status, p.roundId=:round where candidateId=:candidate") // write query
	void updateRoundAndStatus(@Param("status") String status, @Param("round") int round,
			@Param("candidate") int candidate);

	@Modifying
	@Query("update ProcessStatus  p set p.status=:status where candidateId=:candidate") // write query
	void updateStatus(@Param("status") String status, @Param("candidate") int candidate);

	void deleteByCandidateId(int candidateId);
	
	@Query("select p.candidate from ProcessStatus p where p.roundId=:round And p.status='PENDING'")
	List<Candidate> viewCandidate(@Param("round") int round);
	
}
