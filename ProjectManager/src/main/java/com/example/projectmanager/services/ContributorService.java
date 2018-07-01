package com.example.projectmanager.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.projectmanager.model.Contributor;
import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.User;
import com.example.projectmanager.repository.ContributorRepository;

@Service
@Transactional
public class ContributorService {

	private final ContributorRepository contributorRepository;
	
	public ContributorService(ContributorRepository contributorRepository) {
		this.contributorRepository = contributorRepository;
	}
	
	public void AddContributor(Contributor contributor) {	
		contributorRepository.save(contributor);
	}
	public Contributor[] findAllByProject(Project project) {
		return contributorRepository.findAllByProject(project);
	}
	
	public List<Contributor>showMyUser(Project project){
		List<Contributor> users = new ArrayList<Contributor>();
		//List[] projectsArray = projects.toArray();
		for(Contributor user:contributorRepository.findAllByProject(project)) {
			users.add(user);
		}
		return users;
	}

	public void deleteContributor(int id) {
		contributorRepository.deleteById(id);
	}
	public void deleteAllContributors(Project project) {
		contributorRepository.deleteAllByProject(project);
	}
	
	public Contributor[] findAllByUser(User user) {
		return contributorRepository.findAllByUser(user);
	}
	public List<Contributor>showGuestProjects(User user){
		List<Contributor> projects = new ArrayList<Contributor>();
		//List[] projectsArray = projects.toArray();
		for(Contributor project:contributorRepository.findAllByUser(user)) {
			projects.add(project);
		}
		return projects;
	}
}
	
