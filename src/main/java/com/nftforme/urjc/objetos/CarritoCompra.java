package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarritoCompra {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	Long id;
	
	@Column(name = "idCliente", length = 100)
	Long idCliente;
	
	@Column(name = "nombreArticulo", length = 100)
	String nombreArticulo;
	
	@Column(name = "precioArticulo", length = 100)
	float precioArticulo;
	
	static Long generador_id = 0L;
	
	public CarritoCompra(Long id, String articulo, float precio) {
		this.id = generador_id++;
		this.idCliente=id;
		this.nombreArticulo=articulo;
		this.precioArticulo=precio;
	}
	
	public CarritoCompra() {
		
	}

	public Long getIdCliente() {
		return idCliente;
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
