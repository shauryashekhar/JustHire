package com.wissen.justhire.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "is_admin")
	private int isAdmin;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone_number")
	private String phoneNumber;

	// bi-directional many-to-one association to Interview
	@JsonIgnore
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Interview> interviews;

	// bi-directional one-to-one association to Login

	@JsonIgnore
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Login login;

	
	@Column(name = "round_number")
	private int round;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Interview> getInterviews() {
		return this.interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public Interview addInterview(Interview interview) {
		getInterviews().add(interview);
		interview.setUser(this);

		return interview;
	}

	public Interview removeInterview(Interview interview) {
		getInterviews().remove(interview);
		interview.setUser(null);

		return interview;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}