package com.wissen.justhire.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.service.AdminService;
import com.wissen.justhire.service.QuestionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AdminService adminService;

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public void addNewQuestion(@RequestBody QuestionForm form) {

		Question question = new Question();
		question.setQuestion(form.getQuestion());
		question.setDifficulty(form.getDifficulty());
		question.setExperience(form.getExperience());
		question.setComment(form.getComment());
		question.setRound(form.getRound());
		questionService.addSingleQuestion(question);
	}

	// Working
	@GetMapping(produces = { "application/json" })
	public List<Question> getAllQuestions() {
		List<Question> questions = questionService.getAllQuestions();
		return questions;
	}

	@GetMapping(value = "{userId}", produces = { "application/json" })
	public List<Question> getQuestionsForUser(@PathVariable int userId) {
		User user = new User();
		user.setUserId(userId);
		List<Question> questions = questionService.getQuestions(user);
		return questions;
	}

	// Working
	@PutMapping(value = "{questionId}", produces = { "application/json" })
	public void approveQuestion(@PathVariable int questionId) {
		adminService.approveQuestion(questionId);
	}

	@PostMapping(value = "{questionId}", produces = { "application/json" })
	public void editQuestion(@PathVariable int questionId, @RequestBody QuestionForm form) {
		Question question = new Question();
		question.setQuestionId(questionId);
		question.setQuestion(form.getQuestion());
		question.setDifficulty(form.getDifficulty());
		question.setExperience(form.getExperience());
		question.setComment(form.getComment());
		question.setRound(form.getRound());
		questionService.editQuestion(question);
	}

	@SuppressWarnings("resource")
	@PostMapping(headers = ("content-type=multipart/*"), consumes = {
			"application/x-www-form-urlencoded" }, produces = { "application/json" })
	public void addQuestionBank(@RequestParam("file") MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		file.transferTo(convFile);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		br = new BufferedReader(new FileReader(convFile));
		List<Question> questions = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			String[] values = line.split(cvsSplitBy);
			Question question = new Question();
			Round round = new Round();
			question.setQuestion(values[0]);
			question.setDifficulty(values[1]);
			question.setExperience(values[2]);
			question.setComment(values[3]);
			round.setRoundNumber(Integer.parseInt(values[4]));
			question.setRound(round);
			questions.add(question);
		}
		questionService.addQuestionBank(questions);

	}

}
