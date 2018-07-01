package com.example.projectmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.User;
import com.example.projectmanager.services.ProjectService;
import com.example.projectmanager.services.UserService;

@Controller
@SessionAttributes("user")
public class ProjectManagerController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	
	
	@RequestMapping("/welcome")
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
	
	@RequestMapping("/register")
	public String Registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
	}
	
	@PostMapping("/save-user")
	public String RegisterUser(@ModelAttribute User user,BindingResult bindingResult,HttpServletRequest request) {
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
	}
	
	@RequestMapping("/login")
	public String Login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
	}
	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user ,HttpServletRequest request) {
		if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())!=null) {
			user.setId(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword()).getId());
			request.setAttribute("projects", projectService.showMyProjects(user));
			request.setAttribute("mode", "MODE_MY_PROJECTS");
			return "homepage";
		}
		else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "welcomepage";
		}
	}
	@ModelAttribute("user")
	   public User setUpUserForm() {
	      return new User();
	}
}
