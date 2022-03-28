package com.nftforme.urjc.controladores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioProducto;
import com.nftforme.urjc.repositorios.RepositorioWebUser;

@Component
public class DatabaseLoader {

	@Autowired
	private RepositorioProducto repoProd;
	
	@Autowired
	private RepositorioWebUser clienteRepo;
	
	@PostConstruct
	private void init() {
		/*repoProd.save(new Producto("Opel",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Peugeot",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Citroen",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		

		clienteRepo.save(new WebUser("user","pass","USER"));
		clienteRepo.save(new WebUser("admin","adminpass","USER","ADMIN"));
		repoProd.save(new Producto("Renauld",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Toyota",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Ferrari",900F,"Alvaro","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));*/
	}
}