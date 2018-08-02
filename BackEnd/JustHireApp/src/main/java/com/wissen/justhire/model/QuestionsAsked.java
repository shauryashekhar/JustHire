package com.wissen.justhire.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the questions_asked database table.
 * 
 */
@Entity
@Table(name = "questions_asked")
@NamedQuery(name = "QuestionsAsked.findAll", query = "SELECT q FROM QuestionsAsked q")
public class QuestionsAsked implements Serializable {
	private static final long serialVersionUID = 1L;

	private String comment;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asked_id")
	private int askedId;

	private float score;

	// bi-directional many-to-one association to Round
	@Column(name = "round_id")
	private int round;

	// bi-directional many-to-one association to Question
	@ManyToOne()
	@JoinColumn(name = "question_id")
	private Question question;

	public Date getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(Date dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	// bi-directional many-to-one association to Candidate
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dateofinterview")
	private Date dateOfInterview;
	
	public QuestionsAsked() {
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}