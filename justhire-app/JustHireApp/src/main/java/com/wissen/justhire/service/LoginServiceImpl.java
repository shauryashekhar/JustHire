package com.wissen.justhire.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;

import com.wissen.justhire.model.Login;
import com.wissen.justhire.repository.LoginRepository;

public class LoginServiceImpl implements LoginService {

	@Override
	public boolean authenticateUser(String email, String Password) {
		// TODO Auto-generated method stub
		return false;
	}
//	@Autowired
//	private LoginRepository loginRepository;
//
//	@Override
//	public boolean authenticateUser(String email, String password) {
//		Login login = loginRepository.findByEmail(email);
//		String actualPassword = login.getPassword();
//		
//		MessageDigest md =null;
//		try {
//			md = MessageDigest.getInstance("MD5");
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//        md.update(password.getBytes());
//        byte[] digest = md.digest();
////        String enteredPassword = DatatypeConverter.printHexBinary(digest);
//        boolean isLogin=false;
////        if(actualPassword.equals(enteredPassword)) {
//        	isLogin = true;
//        }
//		return isLogin;
//	}

}
