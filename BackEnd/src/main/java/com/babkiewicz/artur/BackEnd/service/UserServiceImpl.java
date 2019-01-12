package com.babkiewicz.artur.BackEnd.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.babkiewicz.artur.BackEnd.model.User;
import com.babkiewicz.artur.BackEnd.repository.UserRepository;
import com.babkiewicz.artur.BackEnd.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user) {
		String password = PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		return userRepository.save(user);
	}
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}
	@Override
	public User update(User user) {
		return userRepository.save(user);
	}
}
