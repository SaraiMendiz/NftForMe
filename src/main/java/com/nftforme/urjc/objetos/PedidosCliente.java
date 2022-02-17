package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PedidosCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100)
	private Long id;
	
	@Column(name = "cliente", length = 100)
	@ManyToOne //un pedido solo pertenece a un cliente, un cliente puede tener varios pedidos
	private Cliente cliente;
	
	@Column(name = "producto", length = 100, unique = true)
	@OneToMany //A un pedido pueden asociarse varios productos, un producto solo puede asociarse a un pedido.
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
