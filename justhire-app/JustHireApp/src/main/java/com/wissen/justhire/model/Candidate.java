package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the candidate database table.
 * 
 */
@Entity
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="candidate_id")
	private int candidateId;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String resume;

	private float score;

	
	@Enumerated(EnumType.STRING)
	private StatusType status;
	
	private String experience;

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	//bi-directional many-to-one association to Interview
	@JsonIgnore
	@OneToMany(mappedBy="candidate",fetch=FetchType.LAZY)
	private List<Interview> interviews;

	//bi-directional one-to-one association to ProcessStatus
	@JsonIgnore
	@OneToOne(mappedBy="candidate",fetch=FetchType.LAZY)
	private ProcessStatus processStatus;

	//bi-directional many-to-one association to QuestionsAsked
	@JsonIgnore
	@OneToMany(mappedBy="candidate",fetch=FetchType.LAZY)
	private List<QuestionsAsked> questionsAskeds;

	public Candidate() {
	}

	public int getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public StatusType getStatus() {
		return this.status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public List<Interview> getInterviews() {
		return this.interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public Interview addInterview(Interview interview) {
		getInterviews().add(interview);
		interview.setCandidate(this);

		return interview;
	}

	public Interview removeInterview(Interview interview) {
		getInterviews().remove(interview);
		interview.setCandidate(null);

		return interview;
	}

	public ProcessStatus getProcessStatus() {
		return this.processStatus;
	}

	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public List<QuestionsAsked> getQuestionsAskeds() {
		return this.questionsAskeds;
	}

	public void setQuestionsAskeds(List<QuestionsAsked> questionsAskeds) {
		this.questionsAskeds = questionsAskeds;
	}

	public QuestionsAsked addQuestionsAsked(QuestionsAsked questionsAsked) {
		getQuestionsAskeds().add(questionsAsked);
		questionsAsked.setCandidate(this);

		return questionsAsked;
	}

	public QuestionsAsked removeQuestionsAsked(QuestionsAsked questionsAsked) {
		getQuestionsAskeds().remove(questionsAsked);
		questionsAsked.setCandidate(null);

		return questionsAsked;
	}

}