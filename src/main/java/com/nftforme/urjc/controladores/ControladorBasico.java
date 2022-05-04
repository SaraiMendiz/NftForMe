package com.nftforme.urjc.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.nftforme.urjc.security.UserComponent;

@Controller
public class ControladorBasico {
	
	@Autowired
	private UserComponent infoControl;
	
	@GetMapping("/error")
	public String error(Model model) {
		return "error";
	}

	@GetMapping("/")
	public String mainPage(Model model) {
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		
		return "MainPage";
	}
	
	@GetMapping("/micuenta")
	public String micuenta(Model model) {
		String web = "micuenta";
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			web = "error";
		}
		return web;
	}
	
	@GetMapping("/ayuda")
	public String ayuda(Model model) {
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "ayuda";
	}
	
	@GetMapping("/quienessomos")
	public String quienessomos(Model model) {
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "quienessomos";
	}
}
