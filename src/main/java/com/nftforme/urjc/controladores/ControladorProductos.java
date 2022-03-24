package com.nftforme.urjc.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.datatype.jdk8.OptionalIntDeserializer;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class ControladorProductos {
	
	private HashSet<String> listaProd;
	
	@Autowired
	private ControladorSesion infoControl;
	
	@Autowired
	private RepositorioProducto repoProd;

	public void buscarCategorias() {
		listaProd = new HashSet<>();
		List <Producto> todos = repoProd.findAll();
		for(Producto temp : todos) {
			String eString = temp.getCategoria();
			if(!temp.getComprado()) {
				listaProd.add(eString);
			}	
		}
	}
	
	@GetMapping("/productos")
	public String productos(Model model) {
		this.buscarCategorias();
		List <Producto> todos = repoProd.findAll();
		ArrayList<Producto> prodNoComprados = new ArrayList<Producto>();
		for(Producto temp:todos) {
			if(!temp.getComprado()) {
				prodNoComprados.add(temp);
			}
		}
		model.addAttribute("producto", prodNoComprados);
		model.addAttribute("filtro", listaProd);
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "productos";
	}
	
	@GetMapping("/productos/{categoria}")
	public String categoria(Model model,@PathVariable String categoria) {
		List <Producto> categoriaAnimals = repoProd.findByCategoria(categoria);
		ArrayList<Producto> prodNoComprados = new ArrayList<Producto>();
		for(Producto temp:categoriaAnimals) {
			if(!temp.getComprado()) {
				prodNoComprados.add(temp);
			}
		}
		model.addAttribute("categoriaP", prodNoComprados);
		model.addAttribute("filtro", listaProd);
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "ProductosCategoria";
	}
	
	@GetMapping("/productos/ver/{nombre}")
	public String verProducto(Model model,@PathVariable String nombre) {
		List <Producto> nombreAnimals = repoProd.findByNombre(nombre);
		model.addAttribute("nombreP", nombreAnimals);
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "VerProducto";
	}
	
	@GetMapping("/hash/{id}")
	public String hash(Model model,@PathVariable Long id) {
		model.addAttribute("hash", repoProd.findById(id).get().getHash());
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "verhash";
	}
	
	@GetMapping("/deleteproduct/{id}")
	public String borrarProducto(@PathVariable Long id) {
		repoProd.deleteById(id);
		return("redirect:/productos");
	}
}
