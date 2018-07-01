package com.example.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projectmanager.model.Contributor;
import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.User;


public interface ContributorRepository extends CrudRepository<Contributor, Integer> {

	Contributor[] findAllByProject(Project project);
	Contributor[] findAllByUser(User user);
	void deleteAllByProject(Project project);
}
