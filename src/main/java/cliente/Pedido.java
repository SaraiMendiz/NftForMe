package cliente;

import java.util.ArrayList;
import producto.Producto;

public class Pedido {
	private ArrayList<Producto> productos;
	
	public Pedido() {
		productos = new ArrayList<>();
	}
	
	public void anadirProducto(Producto p) {
		productos.add(p);
	}
	public void quitarProducto(Producto p) {
		productos.remove(p);
	}
	public float calcularTotal() {
		float total = 0;
		for(int i = 0; i < productos.size(); i++) {
			total += productos.get(i).getPrecio();
		}
		return total;
	}
	public String mostrarPedido(int posicion) { //hacer un bucle que llame a la funcion recursivamente para que devuelva el nombre junto al precio
		return (productos.get(posicion).getNombre()+"    Precio: "+productos.get(posicion).getPrecio()+"€");
	}
}