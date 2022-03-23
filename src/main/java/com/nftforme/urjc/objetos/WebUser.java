package com.nftforme.urjc.objetos;

import java.util.ArrayList;
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
	private List<String> roles = new ArrayList<>();
	
	public WebUser(String user, String pass, String rol1, String rol2) {
		this.name=user;
		this.passwordHash=pass;
		this.roles.add(rol1);
		this.roles.add(rol2);
	}
	
	public WebUser(String user, String pass, String rol1) {
		this.name=user;
		this.passwordHash=pass;
		this.roles.add(rol1);
	}
	
	public WebUser() {
		
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
