package com.nftforme.urjc.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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
		this.passwordHash=new BCryptPasswordEncoder().encode(pass);
		this.roles.add(rol1);
		this.roles.add(rol2);
	}
	
	public WebUser(String user, String pass, String rol1) {
		this.name=user;
		this.passwordHash=new BCryptPasswordEncoder().encode(pass);
		this.roles.add(rol1);
	}
	
	public WebUser() {
		
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = new BCryptPasswordEncoder().encode(passwordHash);
	}

	public void setRolUser() {
		this.roles.add("USER");
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

	public void setName(String name) {
		this.name = name;
	}
}
