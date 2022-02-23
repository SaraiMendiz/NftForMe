package com.nftforme.urjc.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nftforme.urjc.repositorios.RepositorioCliente;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class ControladorBasico {
	
	/*@Autowired
	private RepositorioProducto repoProd;
	
	@Autowired
	private RepositorioCliente clienteRepo;*/
	
	@Autowired
	private ControladorSesion infoControl;
		
	/*@PostConstruct
	private void init() {
		repoProd.save(new Producto("Opel",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Peugeot",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Citroen",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		clienteRepo.save(new Cliente("user1"));
		//carrito.save(new CarritoCompra(clienteRepo.findByUser("user1"),repoProd.findByNombre("Opel").get(0)));
		//repoPedidos.save(new PedidosCliente(clienteRepo.findByUser("user1"),repoProd.findByNombre("Opel").get(0)));
	}*/

	@GetMapping("/")
	public String mainPage(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "MainPage";
	}
	
	@GetMapping("/micuenta")
	public String micuenta(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "micuenta";
	}
	
	@GetMapping("/ayuda")
	public String ayuda(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "ayuda";
	}
	
	@GetMapping("/quienessomos")
	public String quienessomos(Model model) {
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "quienessomos";
	}
}
