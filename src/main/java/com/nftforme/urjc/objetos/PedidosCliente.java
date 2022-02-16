package com.nftforme.urjc.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PedidosCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100)
	private Long id;
	
	@Column(name = "idCliente", length = 100)
	private Long idCliente;
	
	@Column(name = "idProducto", length = 100, unique = true)
	private Long idProducto;
	
	public PedidosCliente(Long idC, Long idP) {
		idCliente=idC;
		idProducto=idP;
	}
	
	public PedidosCliente() {
		
	}

	public Long getId() {
		return id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public Long getIdProducto() {
		return idProducto;
	}	
}
