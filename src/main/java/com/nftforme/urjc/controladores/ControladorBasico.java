package com.nftforme.urjc.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class ControladorBasico {
	
	@Autowired
	private ControladorSesion infoControl;

	@GetMapping("/")
	public String mainPage(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "MainPage";
	}
	
	@GetMapping("/micuenta")
	public String micuenta(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "micuenta";
	}
	
	@GetMapping("/ayuda")
	public String ayuda(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "ayuda";
	}
	
	@GetMapping("/quienessomos")
	public String quienessomos(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "quienessomos";
	}
}
