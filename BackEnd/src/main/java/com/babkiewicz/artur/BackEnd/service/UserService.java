package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.babkiewicz.artur.BackEnd.model.User;

public interface UserService {

	User save(User user);
	User update(User user);
	List<User> findAll();
	User getUserByEmail(String email);

}
