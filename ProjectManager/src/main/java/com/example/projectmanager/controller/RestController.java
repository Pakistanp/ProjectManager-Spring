package com.example.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.projectmanager.model.User;
import com.example.projectmanager.services.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private UserService userService;
	@GetMapping("/")
	public String hello() {
		return "This is home";
	}
	@GetMapping("/save-user")
	public String saveUser(@RequestParam String mail,@RequestParam String firstname,@RequestParam String secondname,@RequestParam String password) {
		User user = new User(mail,firstname,secondname,password);
		userService.saveMyUser(user);
		return "User Saved";
	}
}
