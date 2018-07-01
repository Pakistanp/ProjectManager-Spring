package com.example.projectmanager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {
	@Id
	private int id;
	private String name;
	private String description;
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "id_proj")
	private Project project;
	
	public Task() {
		
	}

	public Task( String name, String description, boolean status, Project project) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.project = project;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", project=" + project + "]";
	}
	
}
