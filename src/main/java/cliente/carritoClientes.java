package cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class carritoClientes {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	int id;
	@Column(name = "nombreCliente", length = 100)
	String nombreCliente;
	@Column(name = "articuloCliente", length = 100)
	String articuloCliente;
	@Column(name = "precioArticulo", length = 100)
	int precioArticulo;
	
	static int generador_id = 0;
	
	public carritoClientes(String nombre, String articulo, int precio) {
		
		this.id = generador_id++;
		this.nombreCliente=nombre;
		this.articuloCliente=articulo;
		this.precioArticulo=precio;
		
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getArticuloCliente() {
		return articuloCliente;
	}

	public void setArticuloCliente(String articuloCliente) {
		this.articuloCliente = articuloCliente;
	}

	public int getPrecio() {
		return precioArticulo;
	}
	
	
}
