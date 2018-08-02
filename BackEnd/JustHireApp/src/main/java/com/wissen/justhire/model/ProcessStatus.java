package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the process_status database table.
 * 
 */
@Entity
@Table(name="process_status")
@NamedQuery(name="ProcessStatus.findAll", query="SELECT p FROM ProcessStatus p")
public class ProcessStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="candidate_id")
	private int candidateId;

	@Column(name="round_id")
	private int roundId;

	@Enumerated(EnumType.STRING)
	private StatusType status;

	//bi-directional one-to-one association to Candidate
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	public ProcessStatus() {
	}

	public int getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public int getRoundId() {
		return this.roundId;
	}

	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	public StatusType getStatus() {
		return this.status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}