package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the interview database table.
 * 
 */
@Entity
@NamedQuery(name="Interview.findAll", query="SELECT i FROM Interview i")
public class Interview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="interview_id")
	private int interviewId;

	//bi-directional many-to-one association to User
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Candidate
	@ManyToOne()
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	public Interview() {
	}

	public int getInterviewId() {
		return this.interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}