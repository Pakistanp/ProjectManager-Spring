package com.example.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.User;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

	public Project[] findAllByUser(User user);

}
