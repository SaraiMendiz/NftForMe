package com.nftforme.urjc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@EnableJpaRepositories ("com.nftforme.urjc.productoRepository")
@Controller
public class crearProductos {

	
	@Autowired
	private productoRepository productoRepositorys;
	
	@PostConstruct
	public void init() {
		categoriaAnimal cultivo = new categoriaAnimal("Manzana",12 ,"Golden", "Madrid");
		categoriaAnimal cultivo1 = new categoriaAnimal("Lechuga", 43,"Iceberg", "Barcelona");
		categoriaAnimal cultivo2 = new categoriaAnimal("Tomate",6543,"Raf", "Asturias");
		categoriaAnimal cultivo3 = new categoriaAnimal("Patata",543,"Kennebec",  "Madrid");
		categoriaAnimal cultivo5 = new categoriaAnimal("Caqui", 543,"Permisson", "Sevilla");
	
		
		productoRepositorys.save(cultivo);
		productoRepositorys.save(cultivo1);
		productoRepositorys.save(cultivo2);
		productoRepositorys.save(cultivo3);
		productoRepositorys.save(cultivo5);
	}
	
	
}
