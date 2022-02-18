package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CarritoCompra {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	private Long id;
	
	@OneToOne
	private Cliente cliente;
	
	@ManyToOne
	private Producto producto;
		
	public CarritoCompra(Cliente cliente, Producto producto) {
		this.cliente=cliente;
		this.producto=producto;
	}
	
	public CarritoCompra() {
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public Long getId() {
		return id;
	}
}
