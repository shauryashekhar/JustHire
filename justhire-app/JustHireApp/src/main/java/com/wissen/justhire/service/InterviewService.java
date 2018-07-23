package com.wissen.justhire.service;

import java.util.List;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.web.AnswerForm;

public interface InterviewService {
	int getMinimumQuestionThreshold();
	
	List<Candidate> viewPendingCandidates(int round);
	
	void startInterview(int candidateId);
	
	
	void submitAnswer(AnswerForm answerForm);
	Question firstQuestion(Candidate candidate, Round round);
	Question nextQuestion(int candidate, int round);
	void stopInterview(int candidate,int currentRound);
	
	
}
