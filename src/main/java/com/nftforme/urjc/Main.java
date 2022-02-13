package com.nftforme.urjc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

	@Autowired
	private productoRepository repository;
	
	@GetMapping("/")
	public String main(Model model) {
		List <categoriaAnimal> todos = repository.findAll();
		model.addAttribute("producto", todos);
		return "main";
	}
	
}
