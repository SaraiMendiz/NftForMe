package com.nftforme.urjc.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;
import com.nftforme.urjc.security.UserComponent;

@Controller
public class ControladorSesion {
	
	//private static final Log log = LogFactory.getLog(ControladorSesion.class);
	
	@Autowired
	private RepositorioWebUser clienteRepo;
	
	@Autowired
	private UserComponent infoControl;
	
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		 model.addAttribute("token", token.getToken());
		 return "register";
	}
	
	@PostMapping("/nuevouser")
	public String nuevouser(Model model,WebUser temp) {
		temp.setRolUser();
		clienteRepo.save(temp);
		return "redirect:/";
	}
	
	@GetMapping("/closelog")
	public String closelog(Model model) {
		infoControl.logout();
		return("redirect:/");
	}
}
