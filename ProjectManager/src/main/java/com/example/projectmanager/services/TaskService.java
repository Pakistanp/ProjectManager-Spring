package com.example.projectmanager.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.Task;
import com.example.projectmanager.model.User;
import com.example.projectmanager.repository.TaskRepository;


@Service
@Transactional
public class TaskService {

private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public void saveTask(Task task) {
		taskRepository.save(task);
	}
	public Task[] findAllByProject(Project project) {
		return taskRepository.findAllByProject(project);
	}
	
	public List<Task>showMyTasks(Project project){
		List<Task> tasks = new ArrayList<Task>();
		//List[] projectsArray = projects.toArray();
		for(Task task:taskRepository.findAllByProject(project)) {
			tasks.add(task);
		}
		return tasks;
	}
	public void deleteMyTask(int id) {
		taskRepository.deleteById(id);
	}
	public void deleteAllTasks(Project project) {
		taskRepository.deleteAllByProject(project);
	}
	public Task EditTask(int id) {
		return taskRepository.findById(id).orElse(null);
	}
}
