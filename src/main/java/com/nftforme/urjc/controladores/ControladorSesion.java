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
import org.springframework.web.bind.annotation.RequestParam;

import com.nftforme.urjc.objetos.Cliente;
import com.nftforme.urjc.repositorios.RepositorioCliente;

@Controller
public class ControladorSesion {
	
	private SampleLogController log;
	
	private boolean login;
	private Cliente clienteActual;
	
	@Autowired
	private RepositorioCliente clienteRepo;
	
	public ControladorSesion() {
		login=false;
	}
	
	/*@GetMapping("/login/{user}")
	public String login(@PathVariable String user) {
		this.clienteActual=clienteRepo.findByUser(user);
		this.login=true;
		return "redirect:/";
	}*/
	
	@GetMapping("/logout")
	public String logout() {
		this.login=false;
		return "redirect:/";
	}
	
	public boolean getLogin() {
		return(this.login);
	}
	
	public Cliente getClienteActual() {
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
	} //esta puesta como que tienes que iniciar iniciar
	
	@RequestMapping("/loginresult")
	public String loginresult(Model model,HttpServletRequest request) {
		System.out.println(log.page(model));
		if(request.getParameter("username").equals("user@gmail.com") && request.getParameter("password").equals("pass")) {
			return "redirect:/";
		}else {
			return "redirect:/ayuda";
		}
		
		/*if(user.equals("user@gmail.com") && pass.equals("pass")) {
			return "redirect:/";
		}else {
			return "redirect:/login";
		}	*/
		
	}
}
