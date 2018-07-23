package com.wissen.justhire.model;

import java.io.Serializable;
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
	@ManyToOne
	@JoinColumn(name = "round_id")
	private Round round;

	// bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	// bi-directional many-to-one association to Candidate
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

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

	public Round getRound() {
		return this.round;
	}

	public void setRound(Round round) {
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