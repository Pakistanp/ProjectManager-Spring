package com.example.projectmanager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	
	@Id
	private int id;
	private String username;
	private String firstname;
	private String secondname;
	private String password;

	public User() {
		
	}
	
	public User(String username, String firstname, String secondname, String password) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.secondname = secondname;
		this.password = password;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", secondname=" + secondname
				+ ", password=" + password + "]";
	}
	
}
