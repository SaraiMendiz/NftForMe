package com.nftforme.urjc.controladores;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class ControladorProductos {
	
	HashSet<String> listaProd;
	
	@Autowired
	private RepositorioProducto repoProd;
	
	@PostConstruct
	public void init() {
		listaProd = new HashSet<>();
		List <Producto> todos = repoProd.findAll();
		for(Producto temp : todos) {
			String eString = temp.getCategoria();
			listaProd.add(eString);
		}
	}
	
	@GetMapping("/productos")
	public String main(Model model) {
		List <Producto> todos = repoProd.findAll();
		model.addAttribute("producto", todos);
		model.addAttribute("filtro", listaProd);
		return "productos";
	}
	
	@GetMapping("/productos/{categoria}")
	public String categoria(Model model,@PathVariable String categoria) {
		List <Producto> categoriaAnimals = repoProd.findByCategoria(categoria);
		model.addAttribute("categoriaP", categoriaAnimals);
		model.addAttribute("filtro", listaProd);
		return "ProductosCategoria";
	}
	
	@GetMapping("/productos/ver/{nombre}")
	public String productoVer(Model model,@PathVariable String nombre) {
		List <Producto> nombreAnimals = repoProd.findByNombre(nombre);
		model.addAttribute("nombreP", nombreAnimals);
		return "VerProducto";
	}
}
