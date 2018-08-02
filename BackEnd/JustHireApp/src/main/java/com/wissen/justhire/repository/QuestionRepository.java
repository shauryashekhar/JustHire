package com.wissen.justhire.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.DifficultyType;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;

@Transactional
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> findByDifficulty(String difficulty);

	List<Question> findByisApproved(int approved);

	@Modifying
	@Query("update Question q set isApproved=1 where questionId=:questId") // write query
	void approveQuestion(@Param("questId") int id);

	
	@Query("from Question q where q.difficulty=:difficulty AND q.round=:round AND experience=:experience AND isApproved=1") // write
	List<Question> findNext(@Param("difficulty") DifficultyType difficulty, @Param("round") int round,
			@Param("experience") String experience);

	@Modifying
	@Query("update Question q set q.comment=:comment, q.difficulty=:difficulty, q.experience=:experience, q.isApproved=0, q.question=:question, q.round=:round where q.questionId=:questionId ") // write
	void editQuestion(@Param("comment") String comment, @Param("difficulty") DifficultyType difficulty,
			 @Param("experience") String experience, @Param("round") int round, @Param("question") String question,
			@Param("questionId") int questionId);

	@Query("from Question q where q.round<=:round")
	List<Question> getRoundQuestions(@Param("round") int round);
	
	@Query("select count(*) from Question q where q.isApproved=0 ")
	int NumberOfPendingQuestion();
	
	@Modifying
	@Query("update Question u set u.round=-1 where u.round>:round")
	void setRoundNull(@Param("round") int round);
	
}
