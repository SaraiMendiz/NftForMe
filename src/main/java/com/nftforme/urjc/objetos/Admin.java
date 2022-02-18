package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	private Long id;
	
	@Column(name = "user", length = 100, unique = true)
	private String user;
	
	@Column(name = "password", length = 100)
	private String password;
	
	public Admin(String password) {
		this.user="admin";
		this.password=password;
	}
	
	public Long getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
