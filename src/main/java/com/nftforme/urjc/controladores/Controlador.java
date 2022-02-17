package com.nftforme.urjc.controladores;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.Cliente;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioCarroCompra;
import com.nftforme.urjc.repositorios.RepositorioCliente;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class Controlador {
	
	private boolean login;
	private HashSet<String> listaProd;
	private HashSet<String> listaCli;
	
	@Autowired
	private RepositorioProducto repoProd;
	
	@Autowired
	private RepositorioCliente clienteRepo;
	
	@Autowired
	private RepositorioCarroCompra carrito;
	
	public Controlador() {
		login=false;
	}
	
	@GetMapping("/login")
	public String login() {
		this.login=true;
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		this.login=false;
		return "redirect:/";
	}
	
	@GetMapping("/mispedidos")
	public String mispedidos(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "mispedidos";
	}
	
	@GetMapping("/carrito")
	public String carrito(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "carrito";
	}
	
	@GetMapping("/")
	public String mainPage(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "MainPage";
	}
	
	@GetMapping("/micuenta")
	public String micuenta(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "micuenta";
	}
	
	@GetMapping("/ayuda")
	public String ayuda(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "ayuda";
	}
	
	@GetMapping("/quienessomos")
	public String quienessomos(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "quienessomos";
	}
	
	//ControladorProductos
	
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
	public String productos(Model model) {
		List <Producto> todos = repoProd.findAll();
		model.addAttribute("producto", todos);
		model.addAttribute("filtro", listaProd);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "productos";
	}
	
	@GetMapping("/productos/{categoria}")
	public String categoria(Model model,@PathVariable String categoria) {
		List <Producto> categoriaAnimals = repoProd.findByCategoria(categoria);
		model.addAttribute("categoriaP", categoriaAnimals);
		model.addAttribute("filtro", listaProd);
		if(login) {
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
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "VerProducto";
	}
	
	//ControladorNuevosProductos
	
	@GetMapping("/nuevoproducto")
	public String nuevoproducto(Model model) {
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "nuevoproducto";
	}
	
	@GetMapping("/resultadonuevo")
	public String resultadonuevo(Model model,@RequestParam String nombre, @RequestParam String autor, @RequestParam float precio,@RequestParam String categoria,@RequestParam String imagen) {
		try {
			repoProd.save(new Producto(nombre,precio,autor,categoria,imagen));
			model.addAttribute("resultado","ok");
			model.addAttribute("comprobar",true);
		}catch(Exception ex){
			model.addAttribute("resultado","error");
		}
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return("resultadonuevo");
	}
	
	//ControladorProductosCliente
	
	@GetMapping("/cliente/{idCliente}/productos")
	public String main(Model model) {
		List <Producto> todos = repoProd.findAll();
		model.addAttribute("producto", todos);
		model.addAttribute("filtro", listaCli);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "productos";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/{categoria}")
	public String categoria(Model model,@PathVariable int id,@PathVariable String categoria) {
		
		List <Producto> Productos = repoProd.findByCategoria(categoria);
		model.addAttribute("categoriaP", Productos);
		model.addAttribute("filtro", listaCli);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "ProductosCategoria";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/ver/{nombre}")
	public String productoVer(Model model,@PathVariable int id,@PathVariable String nombre) {
		
		List <Producto> nombreAnimals = repoProd.findByNombre(nombre);
		model.addAttribute("nombreP", nombreAnimals);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "VerProducto";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/comprar/{nombre}")
	public String comprarProducto(Model model,@PathVariable int id,@PathVariable String nombre) {
		
		List <Producto> nombreAnimals = repoProd.findByNombre(nombre);
		List<Cliente> clienteanonimo = clienteRepo.findById(id);
		String nomString = "";
		for(Cliente a : clienteanonimo) {
			//carrito.save(new carritoClientes(a.getnombre(), a.getnombre(),a.getPrecio()));
		}
	
		List<CarritoCompra> todoCarrito = carrito.findBynombreArticulo(nomString);
		model.addAttribute("listaCarrito",todoCarrito);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "comprar";
	}
}
