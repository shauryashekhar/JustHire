package com.wissen.justhire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.model.DifficultyType;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public void addSingleQuestion(Question question) {
		questionRepository.save(question);
	}

	public void addQuestionBank(List<Question> questionList) {
		questionRepository.saveAll(questionList);
	}

	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	public Question getQuestionById(int id) {
		Optional<Question> question = questionRepository.findById(id);
		return question.get();

	}

	public List<Question> getQuestions(User user) {
		return questionRepository.getRoundQuestions(user.getRound());
	}

	public void editQuestion(Question question) {
		int questionId = question.getQuestionId();
		String comment = question.getComment();
		DifficultyType difficulty = question.getDifficulty();
		String experience = question.getExperience();
		String questionText = question.getQuestion();
		int round = question.getRound();
		questionRepository.editQuestion(comment, difficulty.toString(), experience, round, questionText, questionId);
	}

	@Override
	public void approveQuestion(int id) {
		questionRepository.approveQuestion(id);

	}

}
