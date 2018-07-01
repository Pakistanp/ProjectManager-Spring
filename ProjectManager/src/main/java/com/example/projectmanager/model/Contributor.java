package com.example.projectmanager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contributors")
public class Contributor {

	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	@ManyToOne
	@JoinColumn(name = "id_proj")
	private Project project;
	
	public Contributor() {
		
	}

	public Contributor(User user, Project project) {
		super();
		this.user = user;
		this.project = project;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Contributor [id=" + id + ", user=" + user + ", project=" + project + "]";
	}

	
}
