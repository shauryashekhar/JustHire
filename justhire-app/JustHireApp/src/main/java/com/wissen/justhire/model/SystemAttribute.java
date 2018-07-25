package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the system_attributes database table.
 * 
 */
@Entity
@Table(name="system_attributes")
@NamedQuery(name="SystemAttribute.findAll", query="SELECT s FROM SystemAttribute s")
public class SystemAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="system_id")
	private int systemId;
	
	@Column(name="minimum_questions")
	private int minimumQuestions;

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	@Column(name="no_of_rounds")
	private int noOfRounds;

	private int threshold;

	public SystemAttribute() {
	}

	public int getMinimumQuestions() {
		return this.minimumQuestions;
	}

	public void setMinimumQuestions(int minimumQuestions) {
		this.minimumQuestions = minimumQuestions;
	}

	public int getNoOfRounds() {
		return this.noOfRounds;
	}

	public void setNoOfRounds(int noOfRounds) {
		this.noOfRounds = noOfRounds;
	}

	public int getThreshold() {
		return this.threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

}