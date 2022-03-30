package com.nftforme.urjc.controladores;

import javax.servlet.http.HttpServletRequest;

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
		
	@Autowired
	private ControladorSesion infoControl;
	
	@GetMapping("/nuevoproducto")
	public String nuevoproducto(Model model, HttpServletRequest request) {
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		
		return "nuevoproducto";
	}
	
	@GetMapping("/resultadonuevo")
	public String resultadonuevo(Model model,@RequestParam String nombre, @RequestParam String autor, @RequestParam float precio,@RequestParam String categoria,@RequestParam String imagen) {
		try {
			repoProd.save(new Producto(nombre,precio,autor,categoria,imagen));
			model.addAttribute("resultado","ok");
			model.addAttribute("comprobar",true);
		}catch(Exception ex){
			model.addAttribute("resultado","error");
		}
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return("resultadonuevo");
	}
}
