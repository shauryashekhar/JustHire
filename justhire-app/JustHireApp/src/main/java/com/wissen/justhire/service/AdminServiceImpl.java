package com.wissen.justhire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.ProcessStatus;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.QuestionRepository;
import com.wissen.justhire.repository.UserRepository;

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

	/* (non-Javadoc)
	 * @see com.wissen.justhire.service.AdminService#addUser(com.wissen.justhire.model.User)
	 */
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	/* (non-Javadoc)
	 * @see com.wissen.justhire.service.AdminService#viewAllUsers()
	 */
	@Override
	public List<User> viewAllUsers(){
		return userRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.wissen.justhire.service.AdminService#approveQuestion(int)
	 */
	@Override
	public void approveQuestion(int id) {
		questionRepository.approveQuestion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.wissen.justhire.service.AdminService#createCandidate(com.wissen.justhire.model.Candidate)
	 */
	@Override
	public void createCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
		int candidateId=candidate.getCandidateId();
		int roundId=1;
		String status="PENDING";
		ProcessStatus processStatus = new ProcessStatus();
		processStatus.setCandidateId(candidateId);
		processStatus.setRoundId(roundId);
		processStatus.setStatus(status);
		processStatusRepository.save(processStatus);
		
	}
}
