package com.nftforme.urjc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

	@GetMapping("/greeting")
	public String greeting(Model model) {
		model.addAttribute("name", "World");
		return "holamundo";
	}
}
