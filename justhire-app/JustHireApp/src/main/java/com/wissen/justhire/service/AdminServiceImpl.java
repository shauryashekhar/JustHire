package com.wissen.justhire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.ProcessStatus;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.QuestionRepository;
import com.wissen.justhire.repository.RoundRepository;
import com.wissen.justhire.repository.SystemAttributeRepository;
import com.wissen.justhire.repository.UserRepository;
import com.wissen.justhire.web.ResponseMsg;
import com.wissen.justhire.model.SystemAttribute;

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
	private SystemAttributeRepository systemAttributeRepository; 
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
		String status = "PENDING";
		ProcessStatus processStatus = new ProcessStatus();
		processStatus.setCandidateId(candidateId);
		processStatus.setRoundId(roundId);
		processStatus.setStatus(status);
		processStatusRepository.save(processStatus);

	}

	public List<Candidate> viewCandidate() {
		return candidateRepository.findAll();
	}

	public List<Integer> getStats() {
		List<Integer> list = new ArrayList<>();
		list.add((int) candidateRepository.count());
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
		systemAttributeRepository.save(systemAttribute);
	}

}
