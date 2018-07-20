package com.wissen.justhire.service;

import java.util.List;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;

public interface AdminService {

	void addUser(User user);

	List<User> viewAllUsers();

	void approveQuestion(int id);

	void createCandidate(Candidate candidate);

	List<Candidate> viewCandidate();

	List<Integer> getStats();

	List<Round> getRounds();

}