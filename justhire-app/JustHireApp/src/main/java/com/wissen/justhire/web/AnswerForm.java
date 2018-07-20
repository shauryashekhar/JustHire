package com.wissen.justhire.web;

public class AnswerForm {

	private int candidateId;
	private int questionId;
	
	private int roundId;
	
	private String comment;
	private float score;
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getRoundId() {
		return roundId;
	}
	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	
	
	
}
