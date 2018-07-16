package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rounds database table.
 * 
 */
@Entity
@Table(name="rounds")
@NamedQuery(name="Round.findAll", query="SELECT r FROM Round r")
public class Round implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="round_number")
	private int roundNumber;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="round")
	private List<Question> questions;

	//bi-directional many-to-one association to QuestionsAsked
	@OneToMany(mappedBy="round")
	private List<QuestionsAsked> questionsAskeds;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="round")
	private List<User> users;

	public Round() {
	}

	public int getRoundNumber() {
		return this.roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setRound(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setRound(null);

		return question;
	}

	public List<QuestionsAsked> getQuestionsAskeds() {
		return this.questionsAskeds;
	}

	public void setQuestionsAskeds(List<QuestionsAsked> questionsAskeds) {
		this.questionsAskeds = questionsAskeds;
	}

	public QuestionsAsked addQuestionsAsked(QuestionsAsked questionsAsked) {
		getQuestionsAskeds().add(questionsAsked);
		questionsAsked.setRound(this);

		return questionsAsked;
	}

	public QuestionsAsked removeQuestionsAsked(QuestionsAsked questionsAsked) {
		getQuestionsAskeds().remove(questionsAsked);
		questionsAsked.setRound(null);

		return questionsAsked;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRound(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRound(null);

		return user;
	}

}