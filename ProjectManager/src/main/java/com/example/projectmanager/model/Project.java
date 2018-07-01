package com.example.projectmanager.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	private int id;
	private String name;
	private String description;
	private Date deadline;
	@ManyToOne
	@JoinColumn(name = "owner")
	private User user;
	
	public Project() {
		
	}

	public Project(String name, String description, Date deadline, User user) {
		super();
		this.name = name;
		this.description = description;
		this.deadline = deadline;
		this.user = user;
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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", deadline=" + deadline
				+ ", user=" + user + "]";
	}

	
	
}
