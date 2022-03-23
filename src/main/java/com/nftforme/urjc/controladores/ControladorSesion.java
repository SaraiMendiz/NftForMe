package com.nftforme.urjc.controladores;

import javax.servlet.http.HttpServletRequest;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;

@Controller
public class ControladorSesion {
	
	private static final Log log = LogFactory.getLog(ControladorSesion.class);
		
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
		clienteRepo.save(new WebUser(user,new BCryptPasswordEncoder().encode(pass),"USER"));
		return "redirect:/";
	}
	
	public void setCurrentUser(UsernamePasswordAuthenticationToken user) {
		/*log.info(user.getPrincipal());
		//System.out.println();
		this.clienteActual=clienteRepo.findByName((String)user.getPrincipal());
		//this.clienteActual=(WebUser)user.getPrincipal();*/
		this.login=true;
	}
	
	@RequestMapping(value = "/closelog", method = RequestMethod.GET)
	public String closelog(Model model) {
		this.login=false;
		this.clienteActual=null;
		return("redirect:/");
	}
}
