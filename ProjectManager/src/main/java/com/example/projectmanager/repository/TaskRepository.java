package com.example.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.Task;


public interface TaskRepository extends CrudRepository<Task, Integer> {

	Task[] findAllByProject(Project project);
	void deleteAllByProject(Project project);
}
