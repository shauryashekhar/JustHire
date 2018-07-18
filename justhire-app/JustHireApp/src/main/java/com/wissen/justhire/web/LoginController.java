package com.wissen.justhire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.justhire.service.LoginService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public LoginResponse post(@RequestBody LoginForm form) {

		// validate

		LoginResponse loginResponse = new LoginResponse();
		if (loginService.authenticateUser(form.getEmail(), form.getPassword())) {
			loginResponse.setMessage("Login Successful");
		} else {
			loginResponse.setMessage("Invalid Username or password.");
		}
		return loginResponse;

	}
}
