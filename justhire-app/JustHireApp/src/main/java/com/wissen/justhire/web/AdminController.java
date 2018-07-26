package com.wissen.justhire.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.StatusType;
import com.wissen.justhire.model.SystemAttribute;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.RoundRepository;
import com.wissen.justhire.repository.UserRepository;
import com.wissen.justhire.service.AdminService;
import com.wissen.justhire.service.QuestionService;
import com.wissen.justhire.service.StorageService;

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

	@Autowired
	private RoundRepository roundRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StorageService storageService;
	

	@GetMapping(value = "stats")
	public List<Integer> getStats() {
		List<Integer> list = adminService.getStats();
//		System.out.println(list);
		return list;

	}

	@PostMapping(value = "user", consumes = { "application/json" }, produces = { "application/json" })
	public User addUser(@RequestBody UserForm form) {
		User user = new User();
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());

//		round.setRoundNumber(form.getRoundNumber());
		user.setRound(form.getRoundNumber());
		user.setEmail(form.getEmail());
		user.setPhoneNumber(form.getPhoneNumber());
		adminService.addUser(user);
		return user;
	}

	@GetMapping(value = "editUser/{userId}")
	public User getUserById(@PathVariable int userId) {
		return userRepository.findById(userId).get();
	}

	@PutMapping(value = "editUser/{userId}", consumes = { "application/json" }, produces = { "application/json" })
	public User editUser(@RequestBody UserForm form, @PathVariable int userId) {
		User user = new User();
		user.setUserId(userId);
		user.setEmail(form.getEmail());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setPhoneNumber(form.getPhoneNumber());
		user.setRound(form.getRoundNumber());
		userRepository.save(user);
		return user;
	}

	@GetMapping(value = "user")
	public List<User> getUser() {
//		List<User> check = adminService.viewAllUsers();
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
		candidate.setStatus(StatusType.PENDING);
		adminService.createCandidate(candidate);
		return candidate;
	}

	@PutMapping(value = "candidate/{candidateId}", consumes = { "multipart/form-data", "application/json" })
	public void addCandidateResume(@PathVariable int candidateId, @RequestParam("file") MultipartFile resume)
			throws IOException {
		storageService.storeResume(resume, candidateId);
		Optional<Candidate> candidate = candidateRepository.findById(candidateId);
		candidate.get().setResume(storageService.getResumePath() + "\\" + candidateId + ".pdf");
		candidateRepository.save(candidate.get());
	}
	
	@GetMapping(value = "candidate")
	public List<Candidate> getCandidate() {
		return adminService.viewCandidate();
	}

	@GetMapping(value = "rounds")
	public List<Round> getRounds() {
		return adminService.getRounds();
	}

	@PostMapping(value = "attribute")
	public ResponseMsg setAttribute(@RequestBody SystemAttributeForm attributeForm) {

		SystemAttribute attribute = new SystemAttribute();
		attribute.setSystemId(1);
		attribute.setNoOfRounds(attributeForm.getNoOfRounds());
		attribute.setMinimumQuestions(attributeForm.getMinimumQuestions());
		attribute.setThreshold(attributeForm.getThreshold());
		adminService.setAttributes(attribute);
		ResponseMsg msg = new ResponseMsg();
		msg.setMessage("Attributes set");

		return msg;

	}

	@GetMapping(value = "reset")
	public ResponseMsg resetInterview() {
		ResponseMsg msg = new ResponseMsg();
		adminService.resetInterview();
		msg.setMessage("Inteview Configuration Reset");
		return msg;
	}

	@ExceptionHandler()
	public ResponseEntity<String> exceptionHandler(Throwable t) {
		return new ResponseEntity<String>("KUCH WORK NHI KAR RAHA BC", null, HttpStatus.NOT_FOUND);
	}
}
