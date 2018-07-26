package com.wissen.justhire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Login;
import com.wissen.justhire.model.ProcessStatus;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.StatusType;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.InterviewRepository;
import com.wissen.justhire.repository.LoginRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.QuestionAskedRepository;
import com.wissen.justhire.repository.QuestionRepository;
import com.wissen.justhire.repository.RoundRepository;
import com.wissen.justhire.repository.SystemAttributeRepository;
import com.wissen.justhire.repository.UserRepository;
import com.wissen.justhire.web.ResponseMsg;
import com.wissen.justhire.model.SystemAttribute;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private ProcessStatusRepository processStatusRepository;

	@Autowired
	private RoundRepository roundRepository;

	@Autowired
	private QuestionAskedRepository questionAskedRepository;

	@Autowired
	private SystemAttributeRepository systemAttributeRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private InterviewRepository interviewRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wissen.justhire.service.AdminService#addUser(com.wissen.justhire.model.
	 * User)
	 */
	
	
	
	
	@Override
	public void addUser(User user) {
		userRepository.save(user);
		Login login = new Login();
		login.setUserId(userRepository.findByEmail(user.getEmail()).getUserId());
		login.setUser(user);
		login.setEmail(user.getEmail());
		login.setPassword("40c55b7a094ab345e76b8d1e0179e832");
		loginRepository.save(login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.justhire.service.AdminService#viewAllUsers()
	 */

	@Override
	public List<User> viewAllUsers() {
		return userRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.justhire.service.AdminService#approveQuestion(int)
	 */
	@Override
	public void approveQuestion(int id) {
		questionRepository.approveQuestion(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wissen.justhire.service.AdminService#createCandidate(com.wissen.justhire.
	 * model.Candidate)
	 */
	@Override
	public void createCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
		int candidateId = candidate.getCandidateId();
		int roundId = 1;

		ProcessStatus processStatus = new ProcessStatus();
		processStatus.setCandidateId(candidateId);
		processStatus.setRoundId(roundId);
		processStatus.setStatus(StatusType.PENDING);

		processStatusRepository.save(processStatus);

	}

	public List<Candidate> viewCandidate() {
		return candidateRepository.findAll();
	}

	public List<Integer> getStats() {
		List<Integer> list = new ArrayList<>();
		list.add((int) candidateRepository.countByStatus(StatusType.PENDING));
		list.add((int) userRepository.count());
		list.add((int) questionRepository.count());
		list.add(questionRepository.NumberOfPendingQuestion());
		return list;
	}

	public List<Round> getRounds() {

		List<Round> list = roundRepository.findAll();
		return list;

	}

	@Override
	public void setAttributes(SystemAttribute systemAttribute) {
		int rounds = systemAttributeRepository.findById(1).get().getNoOfRounds();
		systemAttributeRepository.deleteAll();
		roundRepository.deleteAll();
		systemAttributeRepository.save(systemAttribute);
		for (int i = 1; i <= systemAttribute.getNoOfRounds(); i++) {
			Round round2 = new Round();
			round2.setRoundNumber(i);
			roundRepository.save(round2);
		}
		if (rounds > systemAttributeRepository.findById(1).get().getNoOfRounds()) {
			System.out.println("Happening");
			questionRepository.setRoundNull(systemAttributeRepository.findById(1).get().getNoOfRounds());
			userRepository.setRoundNull(systemAttributeRepository.findById(1).get().getNoOfRounds());

		}

	}

	@Override
	public void resetInterview() {

		userRepository.deleteAll();
		loginRepository.deleteAll();
		interviewRepository.deleteAll();

		processStatusRepository.deleteAll();
		questionAskedRepository.deleteAll();
		questionRepository.deleteAll();
		roundRepository.deleteAll();
		systemAttributeRepository.deleteAll();

	}

}
