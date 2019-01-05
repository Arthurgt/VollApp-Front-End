package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import com.babkiewicz.artur.BackEnd.model.User;

public interface UserService {

	User save(User user);
	List<User> findAll();
	User getUserByEmail(String email);

}
