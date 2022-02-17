package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CarritoCompra {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	Long id;
	
	@Column(name = "cliente", unique = true)
	@OneToOne
	Cliente cliente;
	
	@Column(name = "nombreArticulo", length = 100)
	String nombreArticulo;
	
	@Column(name = "precioArticulo", length = 100)
	float precioArticulo;
	
	static Long generador_id = 0L;
	
	public CarritoCompra(Cliente cliente, String articulo, float precio) {
		this.id = generador_id++;
		this.cliente=cliente;
		this.nombreArticulo=articulo;
		this.precioArticulo=precio;
	}
	
	public CarritoCompra() {
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public float getPrecio() {
		return precioArticulo;
	}	
	
	public Long getId() {
		return id;
	}
}
