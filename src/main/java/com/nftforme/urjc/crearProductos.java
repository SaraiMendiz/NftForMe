package com.nftforme.urjc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class crearProductos {

	
	@Autowired
	private productoRepository productoRepositorys;
	
	/*@PostConstruct
	public void init() {
		Producto cultivo = new Producto("Manzana",12 ,"Golden", "Madrid");
		Producto cultivo1 = new Producto("Lechuga", 43,"Iceberg", "Barcelona");
		Producto cultivo2 = new Producto("Tomate",6543,"Raf", "Asturias");
		Producto cultivo3 = new Producto("Patata",543,"Kennebec",  "Madrid");
		Producto cultivo5 = new Producto("Caqui", 543,"Permisson", "Sevilla");
	
		
		productoRepositorys.save(cultivo);
		productoRepositorys.save(cultivo1);
		productoRepositorys.save(cultivo2);
		productoRepositorys.save(cultivo3);
		productoRepositorys.save(cultivo5);
	}*/
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		return "crearProducto";
	}
	
	@Mapping("/nuevook",method="POST")
	public String nuevook(Model model,@RequestParam String nombre, @RequestParam String autor, @RequestParam float precio,@RequestParam String categoria) {
		try {
			productoRepositorys.save(new Producto(nombre,precio,autor,categoria));
			model.addAttribute("resultado","ok");
		}catch(Exception ex){
			model.addAttribute("resultado","error");
		}
		return("nuevook");
	}
	
	
}
