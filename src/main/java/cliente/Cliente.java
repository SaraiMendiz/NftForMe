package cliente;

import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.domain.Page;

import com.nftforme.urjc.categoriaAnimal;

@Entity
public class Cliente {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100, unique = true)
	int id;
	@Column(name = "nombre", length = 100)
	String nombre;
	
	public Cliente(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
		
	}
	//si el cliente quiere añadir saldo a su cuenta
	
	public String getnombre() {
		
		return nombre;
	}
	

	//a la hora de realizar una compra se restara el saldo y se comprobara si tiene el suficiente para poder realizarla
	
}
