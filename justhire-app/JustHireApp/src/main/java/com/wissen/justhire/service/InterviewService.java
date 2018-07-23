package com.wissen.justhire.service;

import java.util.List;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.web.AnswerForm;
import com.wissen.justhire.web.ResponseMsg;

public interface InterviewService {
	int getMinimumQuestionThreshold();
	
	List<Candidate> viewPendingCandidates(int round);
	
	void startInterview(int candidateId);
	
	
	void submitAnswer(AnswerForm answerForm);
	Question firstQuestion(Candidate candidate, Round round);
	Question nextQuestion(int candidate, int round);
	ResponseMsg stopInterview(int candidate,int currentRound);

	List<QuestionsAsked> getReportQuestions(int candidateId);
	
	
}
