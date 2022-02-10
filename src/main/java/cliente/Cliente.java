package cliente;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 String nombre;
	 int ID;
	 float saldo;
	 Pedido pedido;
	
	public Cliente(String nombre, int ID) {
		this.nombre = nombre;
		this.ID = ID;
		saldo = 0;
		pedido = new Pedido();
	}
	//si el cliente quiere añadir saldo a su cuenta
	public void anadirSaldo(float cantidad) {
		saldo += cantidad;
	}
	
	public String getnombre() {
		
		return nombre;
	}
	//a la hora de realizar una compra se restara el saldo y se comprobara si tiene el suficiente para poder realizarla
	public boolean restarSaldo(float cantidad) {
		if(cantidad > saldo) {
			return false;
		}
		else {
			saldo -= cantidad;
			return true;
		}
	}
	public boolean pagar() {
		float total = pedido.calcularTotal();
		if(this.restarSaldo(total) == true) {
			return true;
		}
		else return false;
	}
}
