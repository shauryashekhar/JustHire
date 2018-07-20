package com.wissen.justhire.service;

import java.util.List;

import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.User;

public interface QuestionService {

	void addSingleQuestion(Question question);
	void addQuestionBank(List<Question> questionList);
	List<Question> getAllQuestions();
	List<Question> getQuestions(User user);
	void editQuestion(Question question);
	void approveQuestion(int id);
	Question getQuestionById(int questionId);
}
