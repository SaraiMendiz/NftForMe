package com.nftforme.urjc.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBasico {
	
	@Autowired
	private ControladorSesion infoControl;
	
	@GetMapping("/error")
	public String error(Model model) {
		return "error";
	}

	@GetMapping("/")
	public String mainPage(Model model) {
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		
		return "MainPage";
	}
	
	@GetMapping("/micuenta")
	public String micuenta(Model model) {
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "micuenta";
	}
	
	@GetMapping("/ayuda")
	public String ayuda(Model model) {
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "ayuda";
	}
	
	@GetMapping("/quienessomos")
	public String quienessomos(Model model) {
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "quienessomos";
	}
}
