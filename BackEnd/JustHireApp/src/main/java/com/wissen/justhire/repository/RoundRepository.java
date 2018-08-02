package com.wissen.justhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.justhire.model.Round;

public interface RoundRepository extends JpaRepository<Round, Integer> {

	
	@Query("from Round where roundNumber=:roundId") // write query
	Round getRound(@Param("roundId") int id);
}
