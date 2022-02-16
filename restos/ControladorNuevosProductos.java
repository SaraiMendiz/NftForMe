package com.nftforme.urjc.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class ControladorNuevosProductos {

	@Autowired
	private RepositorioProducto repoProd;
	
	@GetMapping("/nuevoproducto")
	public String nuevo(Model model) {
		return "nuevoproducto";
	}
	
	@GetMapping("/resultadonuevo")
	public String resultadonuevo(Model model,@RequestParam String nombre, @RequestParam String autor, @RequestParam float precio,@RequestParam String categoria) {
		try {
			repoProd.save(new Producto(nombre,precio,autor,categoria));
			model.addAttribute("resultado","ok");
		}catch(Exception ex){
			model.addAttribute("resultado","error");
		}
		return("resultadonuevo");
	}
}
