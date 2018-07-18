package com.wissen.justhire.service;

import org.springframework.stereotype.Service;


public interface LoginService {
	boolean authenticateUser(String email, String Password);
}
