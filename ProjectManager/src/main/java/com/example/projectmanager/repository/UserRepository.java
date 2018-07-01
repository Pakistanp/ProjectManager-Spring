package com.example.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projectmanager.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
}
