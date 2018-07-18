package com.wissen.justhire.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.service.AdminService;
import com.wissen.justhire.service.QuestionService;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private QuestionService questionService;

	@PostMapping(value = "login/", consumes = { "application/json" }, produces = { "application/json" })
	public User addUser(@RequestBody UserForm form) {
		User user = new User();
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		Round round = new Round();
		round.setRoundNumber(form.getRound_number());
		user.setRound(round);
		user.setEmail(form.getEmail());
		user.setPhoneNumber(form.getPhoneNumber());
		adminService.addUser(user);
		return user;
	}

	@GetMapping(value = "user/", consumes = { "application/json" }, produces = { "application/json" })
	public List<User> getUser() {
		return adminService.viewAllUsers();
	}

	@PutMapping(consumes = { "application/json" }, produces = { "application/json" }, params = { "questionid" })
	public String approveQuestion(@RequestParam() int questionId) {

		questionService.approveQuestion(questionId);
		return "Approved";
	}

	@PostMapping(value = "candidate/", consumes = { "application/json" }, produces = { "application/json" })
	public Candidate addCandidate(@RequestBody CandidateForm form) {

		Candidate candidate = new Candidate();
		candidate.setFirstName(form.getFirstName());
		candidate.setLastName(form.getLastName());
		candidate.setEmail(form.getEmail());
		candidate.setExperience(form.getExperience());
		candidate.setResume(form.getResume());
		adminService.createCandidate(candidate);
		return candidate;
	}

	@GetMapping(value = "report/", consumes = { "application/json" }, produces = { "application/json" })
	public List<Candidate> getCandidate() {
		return adminService.viewCandidate();
	}

	@ExceptionHandler()
	public ResponseEntity<String> exceptionHandler(Throwable t) {
		return new ResponseEntity<String>(t.getMessage(), null, HttpStatus.NOT_FOUND);
	}
}
