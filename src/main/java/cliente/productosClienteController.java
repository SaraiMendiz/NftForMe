package cliente;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nftforme.urjc.Producto;
import com.nftforme.urjc.productoRepository;

@Controller
public class productosClienteController {
	
	HashSet<String> listadoHashSet;
	
	@Autowired
	private productoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private carritoRepository carrito;
	
	@PostConstruct
	public void init() {
		listadoHashSet = new HashSet<>();
		List <Producto> todosCat = repository.findAll();
		for( Producto animal : todosCat) {
			String eString = animal.getCategoria();
			listadoHashSet.add(eString);
		}
	}
	
	@GetMapping("/cliente/{idCliente}/productos")
	public String main(Model model) {
		List <Producto> todos = repository.findAll();
		model.addAttribute("producto", todos);
		model.addAttribute("filtro", listadoHashSet);
		return "productos";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/{categoria}")
	public String categoria(Model model,@PathVariable int id,@PathVariable String categoria) {
		
		List <Producto> Productos = repository.findByCategoria(categoria);
		model.addAttribute("categoriaP", Productos);
		model.addAttribute("filtro", listadoHashSet);
		return "ProductosCategoria";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/ver/{nombre}")
	public String productoVer(Model model,@PathVariable int id,@PathVariable String nombre) {
		
		List <Producto> nombreAnimals = repository.findByNombre(nombre);
		model.addAttribute("nombreP", nombreAnimals);
		return "verProducto";
	}
	
	@GetMapping("/cliente/{idCliente}/productos/comprar/{nombre}")
	public String comprarProducto(Model model,@PathVariable int id,@PathVariable String nombre) {
		
		List <Producto> nombreAnimals = repository.findByNombre(nombre);
		Page<Cliente> clienteanonimo = clienteRepo.findById(id);
		String nomString = "";
		for(Cliente a : clienteanonimo) {
			nomString = a.getnombre();
			carrito.save(new carritoClientes(nomString, nombre));
		}
	
		List<carritoClientes> todoCarrito = carrito.findBynombre(nomString);
		model.addAttribute("listaCarrito",todoCarrito);
		return "comprar";
	}
	
	
	
	
}
