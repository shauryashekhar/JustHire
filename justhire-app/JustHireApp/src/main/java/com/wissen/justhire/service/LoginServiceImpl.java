package com.wissen.justhire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.model.Login;
import com.wissen.justhire.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

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

}
