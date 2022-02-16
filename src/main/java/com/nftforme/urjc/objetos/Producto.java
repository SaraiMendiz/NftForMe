package com.nftforme.urjc.objetos;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 100)
	private Long id;
	
	@Column(name = "nombre", length = 100, unique = true)
	private String nombre;
	
	@Column(name = "precio", length = 100)
	private float precio;
	
	@Column(name = "autor", length = 100)
	private String autor;
	
	@Column(name = "comprado", length = 100)
	private boolean comprado;
	
	@Column(name = "categoria", length = 100)
	private String categoria;
	
	@Column(name = "codigoHash", length = 100)
	private String codigoHash;
	
	static int generador_id = 0;
	
	public Producto(String nombre, float precio, String autor, String categoria) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.comprado = false;
		this.autor = autor;
		this.categoria=categoria;
		
		try {
        	MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
			digest.update(nombre.getBytes("utf8"));
			String sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
			this.codigoHash=sha1;
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public Producto() {
		//Constructor vacío
	}
	
	public String getNombre(){
		return nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public Long getID() {
		return id;
	}

	public String getAutor(){
		return autor;
	}

	public Boolean getComprado() {
		return comprado;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public String getHash() {
		return codigoHash;
	}
}
