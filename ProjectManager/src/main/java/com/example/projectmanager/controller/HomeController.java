package com.example.projectmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.projectmanager.model.Contributor;
import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.Task;
import com.example.projectmanager.model.User;
import com.example.projectmanager.services.ContributorService;
import com.example.projectmanager.services.ProjectService;
import com.example.projectmanager.services.TaskService;
import com.example.projectmanager.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private ContributorService contributorService;
	@Autowired
	private TaskService taskService;
	
	
	@RequestMapping("/new-project")
	public String NewProject(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_NEW_PROJECT");
		return "homepage";
	}
	@PostMapping("/save-project")
	public String CreateProject(@ModelAttribute Project project,BindingResult bindingResult,HttpServletRequest request,@SessionAttribute("user") User user) {
		project.setUser(user);
		projectService.saveProject(project);
		request.setAttribute("projects", projectService.showMyProjects(user));
		request.setAttribute("mode", "MODE_MY_PROJECTS");
		return "homepage";
	}
	
	@GetMapping("/show-my-projects")
	public String showMyProjects(HttpServletRequest request,@SessionAttribute("user") User user) {
		request.setAttribute("projects", projectService.showMyProjects(user));
		request.setAttribute("mode", "MODE_MY_PROJECTS");
		return "homepage";
	}
	
	@RequestMapping("/delete-project")
	public String DeleteProject(@RequestParam int id,HttpServletRequest request,@SessionAttribute("user") User user) {
		taskService.deleteAllTasks(projectService.EditProject(id));
		contributorService.deleteAllContributors(projectService.EditProject(id));
		projectService.deleteMyProject(id);
		request.setAttribute("projects", projectService.showMyProjects(user));
		request.setAttribute("mode", "MODE_MY_PROJECTS");
		return "homepage";
	}
	
	@RequestMapping("/edit-project")
	public String editProject(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("project", projectService.EditProject(id));
		request.setAttribute("mode", "MODE_EDIT_PROJECT");
		return "homepage";
	}

	@RequestMapping("/contributors")
	public String Contributors(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("project", projectService.EditProject(id));
		Project project = new Project();
		project.setId(id);
		request.setAttribute("users", contributorService.showMyUser(project));
		request.setAttribute("mode", "MODE_CONTRIBUTORS");
		return "homepage";
	}
	@PostMapping("/add-contributor")
	public String AddContributor(@ModelAttribute User user, BindingResult bindingResult,HttpServletRequest request,@SessionAttribute("user") User owner) {
		String test = request.getParameter("id");
		Project project = new Project();
		project.setId(Integer.parseInt(test));
		if(userService.findByUsername(user.getUsername())!=null && userService.findByUsername(user.getUsername())!=userService.FindById(owner.getId())){
			Contributor contributor = new Contributor();
			contributor.setProject(project);
			contributor.setUser(userService.findByUsername(user.getUsername()));
			contributorService.AddContributor(contributor);
			request.setAttribute("project", projectService.EditProject(Integer.parseInt(test)));
			request.setAttribute("users", contributorService.showMyUser(project));
			request.setAttribute("mode", "MODE_CONTRIBUTORS");
			return "homepage";
		}
		else {
			request.setAttribute("project", projectService.EditProject(Integer.parseInt(test)));
			request.setAttribute("users", contributorService.showMyUser(project));
			request.setAttribute("error", "Invalid Username or You try add yourself");
			request.setAttribute("mode", "MODE_CONTRIBUTORS");
			return "homepage";
		}
		//project.setOwner(user.getId());
		//projectService.saveProject(project);
		//request.setAttribute("projects", projectService.showMyProjects(user));
	}
	@RequestMapping("/delete-contributor")
	public String DeleteContributor(@RequestParam int id,@RequestParam int id_proj,HttpServletRequest request,@SessionAttribute("user") User user) {
		contributorService.deleteContributor(id);
		Project project = new Project();
		project.setId(id_proj);
		request.setAttribute("project", projectService.EditProject(id_proj));
		request.setAttribute("users", contributorService.showMyUser(project));
		request.setAttribute("mode", "MODE_CONTRIBUTORS");
		return "homepage";
	}
	
	@GetMapping("/show-guest-projects")
	public String showGuestProjects(HttpServletRequest request,@SessionAttribute("user") User user) {
		request.setAttribute("projects", contributorService.showGuestProjects(user));
		request.setAttribute("mode", "MODE_GUEST_PROJECTS");
		return "homepage";
	}
	@RequestMapping("/show-tasks")
	public String ShowTasks(@RequestParam int id,HttpServletRequest request) {
		Project project = new Project();
		project = projectService.EditProject(id);
		request.setAttribute("project", projectService.EditProject(id));
		List<Task> tt = taskService.showMyTasks(project);
		request.setAttribute("tasks", taskService.showMyTasks(project));
		request.setAttribute("mode", "MODE_SHOW_MY_TASKS");
		return "homepage";
	}
	@RequestMapping("/new-task")
	public String NewTask(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("project", projectService.EditProject(id));
		request.setAttribute("mode", "MODE_NEW_TASK");
		return "homepage";
	}
	@PostMapping("/save-task")
	public String CreateTask(@ModelAttribute Task task,BindingResult bindingResult,HttpServletRequest request,@SessionAttribute("user") User user) {
		String test = request.getParameter("idP");
		String status = request.getParameter("status");
		Project project = new Project();
		project.setId(Integer.parseInt(test));
		task.setProject(project);
		if(status != null)
			task.setStatus(true);
		else
			task.setStatus(false);
		taskService.saveTask(task);
		project = projectService.EditProject(project.getId());
		request.setAttribute("project", projectService.EditProject(project.getId()));
		request.setAttribute("tasks", taskService.showMyTasks(project));
		request.setAttribute("mode", "MODE_SHOW_MY_TASKS");
		return "homepage";
	}
	@RequestMapping("/delete-task")
	public String DeleteTask(@RequestParam int id,@RequestParam int id_proj,HttpServletRequest request,@SessionAttribute("user") User user) {
		taskService.deleteMyTask(id);
		Project project = new Project();
		project = projectService.EditProject(id_proj);
		request.setAttribute("project", projectService.EditProject(project.getId()));
		request.setAttribute("tasks", taskService.showMyTasks(project));
		request.setAttribute("mode", "MODE_SHOW_MY_TASKS");
		return "homepage";
	}
	@RequestMapping("/edit-task")
	public String EditTask(@RequestParam int id,@RequestParam int id_proj,HttpServletRequest request) {
		Project project = new Project();
		project = projectService.EditProject(id_proj);
		request.setAttribute("project", projectService.EditProject(project.getId()));
		request.setAttribute("task", taskService.EditTask(id));
		request.setAttribute("mode", "MODE_EDIT_TASK");
		return "homepage";
	}
	@GetMapping("/logout")
	public String Logout(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
}
