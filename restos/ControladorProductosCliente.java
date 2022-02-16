package com.nftforme.urjc.controladores;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.Cliente;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioCarroCompra;
import com.nftforme.urjc.repositorios.RepositorioCliente;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class ControladorProductosCliente {
	
	@Autowired
	private RepositorioProducto repository;
	
	HashSet<String> listaCli;
	
	@Autowired
	private RepositorioCliente clienteRepo;
	
	@Autowired
	private RepositorioCarroCompra carrito;
	
	@PostConstruct
	public void init() {
		listaCli = new HashSet<>();
		List <Producto> todosCat = repository.findAll();
		for( Producto animal : todosCat) {
			String eString = animal.getCategoria();
			listaCli.add(eString);
		}
	}
	
	@GetMapping("/cliente/{idCliente}/productos")
	public String main(Model model) {
		List <Producto> todos = repository.findAll();
		model.addAttribute("producto", todos);
		model.addAttribute("filtro", listaCli);
		return "productos";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/{categoria}")
	public String categoria(Model model,@PathVariable int id,@PathVariable String categoria) {
		
		List <Producto> Productos = repository.findByCategoria(categoria);
		model.addAttribute("categoriaP", Productos);
		model.addAttribute("filtro", listaCli);
		return "ProductosCategoria";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/ver/{nombre}")
	public String productoVer(Model model,@PathVariable int id,@PathVariable String nombre) {
		
		List <Producto> nombreAnimals = repository.findByNombre(nombre);
		model.addAttribute("nombreP", nombreAnimals);
		return "VerProducto";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/comprar/{nombre}")
	public String comprarProducto(Model model,@PathVariable int id,@PathVariable String nombre) {
		
		List <Producto> nombreAnimals = repository.findByNombre(nombre);
		List<Cliente> clienteanonimo = clienteRepo.findById(id);
		String nomString = "";
		for(Cliente a : clienteanonimo) {
			//carrito.save(new carritoClientes(a.getnombre(), a.getnombre(),a.getPrecio()));
		}
	
		List<CarritoCompra> todoCarrito = carrito.findBynombreArticulo(nomString);
		model.addAttribute("listaCarrito",todoCarrito);
		return "comprar";
	}	
}