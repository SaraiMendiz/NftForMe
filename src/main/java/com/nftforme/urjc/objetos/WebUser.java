package com.nftforme.urjc.objetos;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String passwordHash;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	// Constructor, getters and setters
	
	public WebUser(String user, String pass, String rol1, String rol2) {
		this.name=user;
		this.passwordHash=pass;
		roles.add(rol1);
		roles.add(rol2);
	}
	
	public WebUser(String user, String pass, String rol1) {
		this.name=user;
		this.passwordHash=pass;
		roles.add(rol1);
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

}
