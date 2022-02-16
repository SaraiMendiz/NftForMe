package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	Long id;
	
	@Column(name = "nombre", length = 100)
	String nombre;
	
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