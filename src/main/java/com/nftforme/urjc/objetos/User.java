package com.nftforme.urjc.objetos;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String passwordHash;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	// Constructor, getters and setters
	
	public User(String user, String pass, String rol1, String rol2) {
		// TODO Auto-generated constructor stub
		this.name=user;
		this.passwordHash=pass;
		roles.add(rol1);
		roles.add(rol2);
		
	}
	
	public User(String user, String pass, String rol1) {
		// TODO Auto-generated constructor stub
		this.name=user;
		this.passwordHash=pass;
		roles.add(rol1);
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
