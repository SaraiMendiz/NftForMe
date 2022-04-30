package com.nftforme.urjc.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class ControladorCompra {
		
	@Autowired
	private RepositorioProducto repoProd;
		
	@Autowired
	private RepositorioCarroCompra carrito;
	
	@Autowired
	private RepositorioPedidosCliente repoPedidos;
	
	@Autowired
	private ControladorSesion infoControl;
	
	@Autowired
	private SenderInterno senderInterno;
	
	

	@GetMapping("/mispedidos")
	public String mispedidos(Model model) {
		List<PedidosCliente> todos = repoPedidos.findAllByCliente(infoControl.getClienteActual());
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(PedidosCliente temp: todos) {
			productos.add(temp.getProducto());
		}
		model.addAttribute("producto", productos);
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "mispedidos";
	}
	
	@GetMapping("/carrito")
	public String carrito(Model model) {
		List<CarritoCompra> todos = carrito.findAllByCliente(infoControl.getClienteActual());
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(CarritoCompra temp: todos) {
			productos.add(temp.getProducto());
		}
		model.addAttribute("producto", productos);
		
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "carrito";
	}
	
	@GetMapping("/buy/{id}")
	public String addCarrito(Model model,@PathVariable Long id) {
		Optional<Producto> temp = repoProd.findById(id);
		
		if(infoControl.getLogin()) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		
		if(temp.get().getComprado()){
			model.addAttribute("resultado","Ya comprado");
		}else {
			carrito.save(new CarritoCompra(infoControl.getClienteActual(),temp.get()));
			temp.get().setComprado(true);
			repoProd.save(temp.get());
			model.addAttribute("resultado","Comprado correctamente");
			model.addAttribute("comprobar",true);
		}		
		
		if(infoControl.getLoginAdmin()) {
			model.addAttribute("admin",true);
		}else if(infoControl.getLogin()) {
			model.addAttribute("user",true);
		}else {
			model.addAttribute("normal",true);
		}
		return "resultadoCarrito";
	}
	
	@GetMapping("/moverapedido/{id}")
	public String moverAPedido(Model model,@PathVariable Long id) {
		repoPedidos.save(new PedidosCliente(infoControl.getClienteActual(),carrito.findByProducto(repoProd.findById(id)).get().getProducto()));
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Optional<CarritoCompra> producto;
		producto = carrito.findById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		Producto gProducto = producto.get().getProducto();
		senderInterno.senderToInterno(gProducto);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		carrito.deleteById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		return "redirect:/mispedidos";
	}
	
	@GetMapping("/deletebag/{id}")
	public String borrarCarrito(Model model,@PathVariable Long id) {
		carrito.deleteById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		repoProd.findById(id).get().setComprado(false);
		repoProd.save(repoProd.findById(id).get());
		return "redirect:/carrito";
	}

}
