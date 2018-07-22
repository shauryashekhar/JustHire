package com.wissen.justhire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
//		System.out.println("here ");

		LoginResponse loginResponse = new LoginResponse();
		if (loginService.authenticateUser(form.getEmail(), form.getPassword())) {
			loginResponse.setMessage("Login Successful");
			loginResponse.setToken(loginService.generateToken(form.getEmail()));
			loginResponse.setUser(loginService.getUser(form.getEmail()));
		} else {
			loginResponse.setMessage("Invalid Username or password.");
		}
		return loginResponse;

	}

	@ExceptionHandler(value = { NullPointerException.class })
	public ResponseEntity<String> exceptionHandler(Throwable t) {
		return new ResponseEntity<String>("Username or Password Wrong", null, HttpStatus.NOT_FOUND);
	}
}
