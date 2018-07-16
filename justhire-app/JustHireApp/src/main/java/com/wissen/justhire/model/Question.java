package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@Table(name="questions")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id")
	private int questionId;

	private String comment;

	private String difficulty;

	private String experience;

	@Column(name="is_approved")
	private int isApproved;

	private String question;

	//bi-directional many-to-one association to Round
	@ManyToOne
	@JoinColumn(name="round_number")
	private Round round;

	//bi-directional many-to-one association to QuestionsAsked
	@OneToMany(mappedBy="question")
	private List<QuestionsAsked> questionsAskeds;

	public Question() {
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public int getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Round getRound() {
		return this.round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public List<QuestionsAsked> getQuestionsAskeds() {
		return this.questionsAskeds;
	}

	public void setQuestionsAskeds(List<QuestionsAsked> questionsAskeds) {
		this.questionsAskeds = questionsAskeds;
	}

	public QuestionsAsked addQuestionsAsked(QuestionsAsked questionsAsked) {
		getQuestionsAskeds().add(questionsAsked);
		questionsAsked.setQuestion(this);

		return questionsAsked;
	}

	public QuestionsAsked removeQuestionsAsked(QuestionsAsked questionsAsked) {
		getQuestionsAskeds().remove(questionsAsked);
		questionsAsked.setQuestion(null);

		return questionsAsked;
	}

}