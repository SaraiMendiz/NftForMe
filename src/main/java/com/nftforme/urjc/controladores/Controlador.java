package com.nftforme.urjc.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.Cliente;
import com.nftforme.urjc.objetos.PedidosCliente;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioCarroCompra;
import com.nftforme.urjc.repositorios.RepositorioCliente;
import com.nftforme.urjc.repositorios.RepositorioPedidosCliente;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@Controller
public class Controlador {
	
	private boolean login;
	private HashSet<String> listaProd;
	private Cliente clienteActual;
	
	@Autowired
	private RepositorioProducto repoProd;
	
	@Autowired
	private RepositorioCliente clienteRepo;
	
	@Autowired
	private RepositorioCarroCompra carrito;
	
	@Autowired
	private RepositorioPedidosCliente repoPedidos;
	
	public Controlador() {
		login=false;
	}
	
	@PostConstruct
	private void init() {
		repoProd.save(new Producto("Opel",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Peugeot",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Citroen",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		clienteRepo.save(new Cliente("user1"));
		//carrito.save(new CarritoCompra(clienteRepo.findByUser("user1"),repoProd.findByNombre("Opel").get(0)));
		//repoPedidos.save(new PedidosCliente(clienteRepo.findByUser("user1"),repoProd.findByNombre("Opel").get(0)));
	}
	
	@GetMapping("/deletebag/{id}")
	public String borrarCarrito(Model model,@PathVariable Long id) {
		carrito.deleteById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		repoProd.findById(id).get().setComprado(false);
		repoProd.save(repoProd.findById(id).get());
		return "redirect:/carrito";
	}
	
	@GetMapping("/login/{user}")
	public String login(@PathVariable String user) {
		this.clienteActual=clienteRepo.findByUser(user);
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
		List<PedidosCliente> todos = repoPedidos.findAll();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(PedidosCliente temp: todos) {
			productos.add(temp.getProducto());
		}
		model.addAttribute("producto", productos);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "mispedidos";
	}
	
	@GetMapping("/carrito")
	public String carrito(Model model) {
		List<CarritoCompra> todos = carrito.findAll();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(CarritoCompra temp: todos) {
			productos.add(temp.getProducto());
		}
		model.addAttribute("producto", productos);
		
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "carrito";
	}
	
	@GetMapping("/buy/{id}")
	public String addCarrito(Model model,@PathVariable Long id) {
		Optional<Producto> temp = repoProd.findById(id);
		
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		
		if(temp.get().getComprado()){
			model.addAttribute("resultado","Ya comprado");
		}else {
			carrito.save(new CarritoCompra(this.clienteActual,temp.get()));
			temp.get().setComprado(true);
			repoProd.save(temp.get());
			model.addAttribute("resultado","Comprado correctamente");
			model.addAttribute("comprobar",true);
		}		
		return "resultadoCarrito";
	}
	
	@GetMapping("/moverapedido/{id}")
	public String moverAPedido(Model model,@PathVariable Long id) {
		repoPedidos.save(new PedidosCliente(this.clienteActual,carrito.findByProducto(repoProd.findById(id)).get().getProducto()));
		carrito.deleteById(carrito.findByProducto(repoProd.findById(id)).get().getId());
		return "redirect:/mispedidos";
	}
	
	@GetMapping("/hash/{id}")
	public String hash(Model model,@PathVariable Long id) {
		model.addAttribute("hash", repoProd.findById(id).get().getHash());
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "verhash";
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
		ArrayList<Producto> prodNoComprados = new ArrayList<Producto>();
		for(Producto temp:categoriaAnimals) {
			if(!temp.getComprado()) {
				prodNoComprados.add(temp);
			}
		}
		model.addAttribute("categoriaP", prodNoComprados);
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
	
	/*@GetMapping("/cliente/{idCliente}/productos")
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
	
		//List<CarritoCompra> todoCarrito = carrito.findByNombre(nomString);
		model.addAttribute("listaCarrito",todoCarrito);
		if(login) {
			model.addAttribute("login",true);
		}else {
			model.addAttribute("login",false);
		}
		return "comprar";
	}*/
}
