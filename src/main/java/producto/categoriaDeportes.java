package producto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class categoriaDeportes{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String nombre;
	private float precio;
	private int ID;
	private String autor;
	private boolean comprado = false;
	static int generador_id = 0;
	
	public categoriaDeportes(String nombre, float precio, String autor) {
		this.nombre = nombre;
		this.precio = precio;
		this.ID = generador_id++;
		this.comprado = false;
		this.autor = autor;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setPrecio(float precio){
		this.precio = precio;
	}
	public float getPrecio() {
		return precio;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getAutor(){
		return autor;
	}
	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	public Boolean getComprado() {
		return comprado;
	}
}
