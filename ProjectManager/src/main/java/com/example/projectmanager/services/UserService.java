package com.example.projectmanager.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.projectmanager.model.User;
import com.example.projectmanager.repository.UserRepository;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void saveMyUser(User user) {	
		userRepository.save(user);
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	public User FindById(int id) {
		return userRepository.findById(id).orElse(null);
	}
}
