package com.nftforme.urjc.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;

@Controller
public class ControladorSesion {
	
	private SampleLogController log;
	
	private boolean login;
	private WebUser clienteActual;
	
	@Autowired
	private RepositorioWebUser clienteRepo;
	
	public ControladorSesion() {
		login=false;
	}
	
	@GetMapping("/logout")
	public String logout() {
		this.login=false;
		return "redirect:/";
	}
	
	public boolean getLogin() {
		return(this.login);
	}
	
	public WebUser getClienteActual() {
		return(this.clienteActual);
	}
	
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {

	 CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
	 model.addAttribute("token", token.getToken());
	 return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		 model.addAttribute("token", token.getToken());
		 return "register";
	}
	
	@RequestMapping(value = "/nuevouser", method = RequestMethod.GET)
	public String nuevouser(Model model,@RequestParam String user,@RequestParam String pass) {
		clienteRepo.save(new WebUser(user,pass,"USER"));
		return "redirect:/";
	}
}
