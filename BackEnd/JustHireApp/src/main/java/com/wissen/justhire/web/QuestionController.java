package com.wissen.justhire.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wissen.justhire.model.DifficultyType;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.QuestionRepository;
import com.wissen.justhire.repository.UserRepository;
import com.wissen.justhire.service.AdminService;
import com.wissen.justhire.service.QuestionService;
import com.wissen.justhire.service.StorageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StorageService storageService;

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public void addNewQuestion(@RequestBody QuestionForm form) {

		Question question = new Question();
		question.setQuestion(form.getQuestion());
		if (form.getDifficulty().equals("EASY"))
			question.setDifficulty(DifficultyType.EASY);
		else if (form.getDifficulty().equals("HARD"))
			question.setDifficulty(DifficultyType.HARD);
		else if (form.getDifficulty().equals("MEDIUM"))
			question.setDifficulty(DifficultyType.MEDIUM);

		question.setExperience(form.getExperience());
		question.setComment(form.getComment());
		question.setRound(form.getRound());
		questionService.addSingleQuestion(question);
	}

	@GetMapping(value = "edit/{questionId}")
	public Question getQuestionById(@PathVariable int questionId) {

		return questionService.getQuestionById(questionId);

	}

	// Working
	@GetMapping(produces = { "application/json" })
	public List<Question> getAllQuestions() {
		List<Question> questions = questionService.getAllQuestions();
		return questions;
	}

	@GetMapping(value = "{userId}", produces = { "application/json" })
	public List<Question> getQuestionsForUser(@PathVariable int userId) {
		User user = userRepository.findByuserId(userId);
		List<Question> questions = questionService.getQuestions(user);
		return questions;
	}

	// Working
	@PutMapping(value = "{questionId}", produces = { "application/json" })
	public void approveQuestion(@PathVariable int questionId) {
		adminService.approveQuestion(questionId);
	}

	@DeleteMapping(value = "{questionId}", produces = { "application/json" })
	public void deleteQuestion(@PathVariable int questionId) {
		Optional<Question> question = questionRepository.findById(questionId);
		questionRepository.delete(question.get());

	}

	@PutMapping(value = "edit/{questionId}", produces = { "application/json" })
	public void editQuestion(@PathVariable int questionId, @RequestBody QuestionForm form) {

		Question question = new Question();
		question.setQuestionId(questionId);
		question.setQuestion(form.getQuestion());
		if (form.getDifficulty().equals("EASY"))
			question.setDifficulty(DifficultyType.EASY);
		else if (form.getDifficulty().equals("HARD"))
			question.setDifficulty(DifficultyType.HARD);
		else if (form.getDifficulty().equals("MEDIUM"))
			question.setDifficulty(DifficultyType.MEDIUM);

		question.setExperience(form.getExperience());
		question.setComment(form.getComment());
		question.setRound(form.getRound());
		System.out.println(question);
		questionService.editQuestion(question);

	}

	@SuppressWarnings("resource")
	@PostMapping(value = "bank")
	public void addQuestionBank(@RequestParam("file") MultipartFile file) throws IOException {
		String name = storageService.store(file);
		File convFile = new File(storageService.getRootLocation() + "\\" + name);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		br = new BufferedReader(new FileReader(convFile));
		List<Question> questions = new ArrayList<>();
		int i = 0;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(cvsSplitBy);
			Question question = new Question();
			Round round = new Round();
			question.setQuestion(values[0]);
			if (values[1].equals("EASY"))
				question.setDifficulty(DifficultyType.EASY);
			else if (values[1].equals("HARD"))
				question.setDifficulty(DifficultyType.HARD);
			else
				question.setDifficulty(DifficultyType.MEDIUM);
			question.setExperience(values[2]);

			question.setIsApproved(1);
			question.setComment(values[3]);
			question.setRound(Integer.parseInt(values[4]));
			questions.add(question);
			i++;
			System.out.println(i);
		}
		System.out.println("Questions not added");
		questionService.addQuestionBank(questions);
		System.out.println("Questions added");
	}

	@ExceptionHandler()
	public ResponseEntity<String> exceptionHandler(Throwable t) {
		return new ResponseEntity<String>("Exception occured", null, HttpStatus.NOT_FOUND);
	}

}
