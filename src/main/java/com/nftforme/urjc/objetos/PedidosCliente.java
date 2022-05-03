package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PedidosCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100)
	private Long id;
	
	@ManyToOne
	private WebUser cliente;
	
	@OneToOne
	private Producto producto;
	
	public PedidosCliente(WebUser cliente, Producto producto) {
		this.cliente=cliente;
		this.producto=producto;
	}
	
	public PedidosCliente() {
		
	}

	public Long getId() {
		return id;
	}

	public WebUser getCliente() {
		return cliente;
	}

	public Producto getProducto() {
		return producto;
	}	
}
