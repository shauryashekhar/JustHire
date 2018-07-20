package com.wissen.justhire.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.service.AdminService;
import com.wissen.justhire.service.QuestionService;

@RestController
@RequestMapping(value = "/api/admin/")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ProcessStatusRepository processStatusRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@GetMapping(value = "stats")
	public List<Integer> getStats() {
		List<Integer> list = adminService.getStats();
		System.out.println(list);
		return list;

	}

	@PostMapping(value = "user", consumes = { "application/json" }, produces = { "application/json" })
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

	@GetMapping(value = "user")
	public List<User> getUser() {
		List<User> check = adminService.viewAllUsers();
		return adminService.viewAllUsers();
	}

	@PostMapping(value = "candidate", consumes = { "application/json" }, produces = { "application/json" })
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

	@GetMapping(value = "candidate")
	public List<Candidate> getCandidate() {
		return adminService.viewCandidate();
	}

	@GetMapping(value = "candidate/pending/{roundId}")
	public List<Candidate> getPendingCandidate(@PathVariable int roundId) {

		List<Candidate> pendingCandidate = processStatusRepository.viewPendingCandidate(roundId);
		List<Integer> candidateList = new ArrayList<>();
		List<Candidate> pendingCandidateList = candidateRepository.findAllById(candidateList);
		return pendingCandidateList;
	}

	@GetMapping(value = "rounds")
	public List<Round> getRounds() {
		return adminService.getRounds();
	}
	
	
	@ExceptionHandler()
	public ResponseEntity<String> exceptionHandler(Throwable t) {
		return new ResponseEntity<String>("KUCH WORK NHI KAR RAHA BC", null, HttpStatus.NOT_FOUND);
	}
}
