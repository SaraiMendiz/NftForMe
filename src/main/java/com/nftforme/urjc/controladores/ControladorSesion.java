package com.nftforme.urjc.controladores;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nftforme.urjc.objetos.ActualUser;
import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioActualUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;
import com.nftforme.urjc.security.UserComponent;

@Controller
public class ControladorSesion {
	
	//private static final Log log = LogFactory.getLog(ControladorSesion.class);
	
	@Autowired
	private RepositorioWebUser clienteRepo;
	
	@Autowired
	private RepositorioActualUser userLog;
	
	@Autowired
	private UserComponent infoControl;
	
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		return "register";
	}
	
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		return "login";
	}
	
	@PostMapping("/nuevouser")
	public String nuevouser(Model model,WebUser temp) {
		temp.setRolUser();
		clienteRepo.save(temp);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String closelog(Model model) {
		infoControl.logout();
		return("redirect:/");
	}
	
	@GetMapping("/nuevologin")
	public String nuevologin(Model model,@RequestParam String name,@RequestParam String pass) {
		String web = "redirect:/";
		Optional<WebUser> user = clienteRepo.findByName(name);
		if (!user.isPresent()) {
			web="errorLoginName";
		}else {
			if (!new BCryptPasswordEncoder().matches(pass, user.get().getPasswordHash())) {
				web="errorLoginPass";
			}else {
				userLog.save(new ActualUser(user.get().getName()));
			}
		}
		return web;
	}
}
