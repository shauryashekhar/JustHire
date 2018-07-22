package com.wissen.justhire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.model.Login;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.LoginRepository;
import com.wissen.justhire.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean authenticateUser(String email, String password) {

		Login login = loginRepository.findByEmail(email);

		System.out.println(login.getEmail() + " " + login.getPassword());
		String actualPassword = login.getPassword();
		boolean isLogin = false;
		if (actualPassword.equals(password)) {
			isLogin = true;
		}
		System.out.println(isLogin);
		return isLogin;
	}

	@Override
	public String generateToken(String userName) {

		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		return userName + ":" + expires+ ":JustHire";
	}

	@Override
	public User getUser(String email) {

		User user = userRepository.findByEmail(email);
		return user;
	}

}
