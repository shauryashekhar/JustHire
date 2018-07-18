package com.wissen.justhire.service;

import java.util.List;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;

public interface InterviewService {
	int getMinimumQuestionThreshold();
	List<Candidate> viewPendingCandidates(int round);
	void startInterview(Candidate candidate);
	Question nextQuestion(Candidate candidate, Round round, String currentDifficulty, float Score, String comment);
	void stopInterview(Candidate candidate,Round currentRound, int easyCount, int mediumCount, int hardCount, int easyScore, int mediumScore, int hardScore);
	
	
}
