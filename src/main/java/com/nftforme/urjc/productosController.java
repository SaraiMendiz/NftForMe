package com.nftforme.urjc;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class productosController {
	
	HashSet<String> listadoHashSet;
	
	@Autowired
	private productoRepository repository;
	
	@PostConstruct
	public void init() {
		listadoHashSet = new HashSet<>();
		List <categoriaAnimal> todosCat = repository.findAll();
		for( categoriaAnimal animal : todosCat) {
			String eString = animal.getCategoria();
			listadoHashSet.add(eString);
		}
	}
	
	@GetMapping("/productos")
	public String main(Model model) {
		List <categoriaAnimal> todos = repository.findAll();
		model.addAttribute("producto", todos);
		model.addAttribute("filtro", listadoHashSet);
		return "productos";
	}
	
	@GetMapping("/productos/{categoria}")
	public String categoria(Model model,@PathVariable String categoria) {
		
		List <categoriaAnimal> categoriaAnimals = repository.findByCategoria(categoria);
		model.addAttribute("categoriaP", categoriaAnimals);
		model.addAttribute("filtro", listadoHashSet);
		return "ProductosCategoria";
	}
	
	@GetMapping("/productos/ver/{nombre}")
	public String productoVer(Model model,@PathVariable String nombre) {
		
		List <categoriaAnimal> nombreAnimals = repository.findByNombre(nombre);
		model.addAttribute("nombreP", nombreAnimals);
		return "verProducto";
	}
}
