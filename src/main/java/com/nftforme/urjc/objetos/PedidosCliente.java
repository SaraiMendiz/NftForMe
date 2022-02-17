package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PedidosCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToOne
	private Producto producto;
	
	public PedidosCliente(Cliente cliente, Producto producto) {
		cliente=cliente;
		producto=producto;
	}
	
	public PedidosCliente() {
		
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Producto getProducto() {
		return producto;
	}	
}
