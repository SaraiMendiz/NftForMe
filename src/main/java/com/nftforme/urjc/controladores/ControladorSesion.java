package com.nftforme.urjc.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nftforme.urjc.objetos.Cliente;
import com.nftforme.urjc.repositorios.RepositorioCliente;

@Controller
public class ControladorSesion {
	
	private boolean login;
	private Cliente clienteActual;
	
	@Autowired
	private RepositorioCliente clienteRepo;
	
	public ControladorSesion() {
		login=false;
	}
	
	/*@GetMapping("/login/{user}")
	public String login(@PathVariable String user) {
		this.clienteActual=clienteRepo.findByUser(user);
		this.login=true;
		return "redirect:/";
	}*/
	
	@GetMapping("/logout")
	public String logout() {
		this.login=false;
		return "redirect:/";
	}
	
	public boolean getLogin() {
		return(this.login);
	}
	
	public Cliente getClienteActual() {
		return(this.clienteActual);
	}
	
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {

	 CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
	 model.addAttribute("token", token.getToken());
	 return "login";
	}
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		 model.addAttribute("token", token.getToken());
		 return "register";
	} //esta puesta como que tienes que iniciar iniciar
	
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
