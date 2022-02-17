package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	private Long id;
	
	@Column(name = "nombre", length = 100)
	private String nombre;
	
	public Cliente(String nombre, Long id) {
		this.nombre = nombre;
		this.id = id;
	}
	
	public Cliente() {
		
	}
	
	public String getNombre() {
		return nombre;
	}	
	
	public Long getId() {
		return id;
	}
}
