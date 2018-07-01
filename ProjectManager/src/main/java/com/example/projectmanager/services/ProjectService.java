package com.example.projectmanager.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.User;
import com.example.projectmanager.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	private final ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public void saveProject(Project project) {	
		//project.setOwner(owner);
		projectRepository.save(project);
	}
	
	public Project[] findAllByUser(User user) {
		return projectRepository.findAllByUser(user);
	}
	
	public List<Project>showMyProjects(User user){
		List<Project> projects = new ArrayList<Project>();
		//List[] projectsArray = projects.toArray();
		for(Project project:projectRepository.findAllByUser(user)) {
			projects.add(project);
		}
		return projects;
	}
	public void deleteMyProject(int id) {
		projectRepository.deleteById(id);
	}
	public Project EditProject(int id) {
		return projectRepository.findById(id).orElse(null);
	}

}
