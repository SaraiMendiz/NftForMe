package com.nftforme.urjc.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioProducto;
import com.nftforme.urjc.security.UserComponent;

@Controller
public class ControladorNuevosProductos {
	
	@Autowired
	private RepositorioProducto repoProd;
		
	@Autowired
	private UserComponent infoControl;
	
	@GetMapping("/nuevoproducto")
	public String nuevoproducto(Model model, HttpServletRequest request) {
		String web = "nuevoproducto";
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			web = "error";
		}
		return web;
	}
	@CacheEvict(value = "prod", allEntries=true)
	@GetMapping("/resultadonuevo")
	public String resultadonuevo(Model model,@RequestParam String nombre, @RequestParam String autor, @RequestParam float precio,@RequestParam String categoria,@RequestParam String imagen) {
		try {
			repoProd.save(new Producto(nombre,precio,autor,categoria,imagen));
			model.addAttribute("resultado","ok");
			model.addAttribute("comprobar",true);
		}catch(Exception ex){
			model.addAttribute("resultado","error");
		}
		String web = "resultadonuevo";
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			web = "error";
		}
		return web;
	}
}
