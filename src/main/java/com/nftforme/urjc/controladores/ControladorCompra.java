package com.nftforme.urjc.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.PedidosCliente;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioCarroCompra;
import com.nftforme.urjc.repositorios.RepositorioPedidosCliente;
import com.nftforme.urjc.repositorios.RepositorioProducto;
import com.nftforme.urjc.security.UserComponent;

@Controller
public class ControladorCompra {
		
	@Autowired
	private RepositorioProducto repoProd;
		
	@Autowired
	private RepositorioCarroCompra carrito;
	
	@Autowired
	private RepositorioPedidosCliente repoPedidos;
	
	@Autowired
	private UserComponent infoControl;
	
	@Autowired
	private SenderInterno senderInterno;
	
	
	@Cacheable("emp")
	@GetMapping("/mispedidos")
	public String mispedidos(Model model) {
		List<PedidosCliente> todos = repoPedidos.findAllByCliente(infoControl.getLoggedUser());
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(PedidosCliente temp: todos) {
			productos.add(temp.getProducto());
		}
		model.addAttribute("producto", productos);
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "mispedidos";
	}
	
	@Cacheable("car")
	@GetMapping("/carrito")
	public String carrito(Model model) {
		List<CarritoCompra> todos = carrito.findAllByCliente(infoControl.getLoggedUser());
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(CarritoCompra temp: todos) {
			productos.add(temp.getProducto());
		}
		model.addAttribute("producto", productos);
		
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "carrito";
	}
	
	@GetMapping("/buy/{id}")
	public String addCarrito(Model model,@PathVariable Long id) {
		Optional<Producto> temp = repoProd.findById(id);
		
		if(temp.get().getComprado()){
			model.addAttribute("resultado","Ya comprado");
		}else {
			carrito.save(new CarritoCompra(infoControl.getLoggedUser(),temp.get()));
			temp.get().setComprado(true);
			repoProd.save(temp.get());
			model.addAttribute("resultado","Comprado correctamente");
			model.addAttribute("comprobar",true);
		}		
		
		if(infoControl.isAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLoggedUser()!=null) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "resultadoCarrito";
	}
	
	@CacheEvict(value = "emp", allEntries=true)
	@GetMapping("/moverapedido/{id}")
	public String moverAPedido(Model model,@PathVariable Long id) {
		repoPedidos.save(new PedidosCliente(infoControl.getLoggedUser(),carrito.findByProducto(repoProd.findById(id)).get().getProducto()));
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Optional<CarritoCompra> producto;
		producto = carrito.findById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		Producto gProducto = producto.get().getProducto();
		senderInterno.senderToInterno(gProducto);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		carrito.deleteById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		return "redirect:/mispedidos";
	}
	
	@CacheEvict(value = "car", allEntries=true)
	@GetMapping("/deletebag/{id}")
	public String borrarCarrito(Model model,@PathVariable Long id) {
		carrito.deleteById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		repoProd.findById(id).get().setComprado(false);
		repoProd.save(repoProd.findById(id).get());
		return "redirect:/carrito";
	}

}
