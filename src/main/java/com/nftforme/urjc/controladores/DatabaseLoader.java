package com.nftforme.urjc.controladores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
		/*repoProd.save(new Producto("Monkey 1",900F,"NFTForMe","Monkey","https://lh3.googleusercontent.com/UJWngvyb8dHsTBO8VKD_JE5J27V0ySZnMmLfTe9KjNYJf6iW0vDlU2xUoycSveoQ5g8rO3F66P_ZICebToz46s7wfNxN0EFS8UuPwQ=w600"));
		repoProd.save(new Producto("Peugeot",97382F,"NFTForMe","Coches","https://img.remediosdigitales.com/81cf58/opel-astra-2021-03/840_560.jpeg"));
		repoProd.save(new Producto("Citroen",23400F,"NFTForMe","Coches","https://images1.autocasion.com/unsafe/1200x800/actualidad/wp-content/uploads/2020/07/Seat-Leon-2020-1600-02.jpg"));
		repoProd.save(new Producto("Monkey 2",23400F,"NFTForMe","Monkey","https://lh3.googleusercontent.com/4UrdP9Js_x-cwdTYKUshwGufwmbeuwrz_iSUPlWmb1jzK1hO45gNELfmtD9McErS9AK8WQK3KG9d6b-6muL2zEyT3LcQPQuWbcXl=w600"));
		repoProd.save(new Producto("Monkey 3",23400F,"NFTForMe","Monkey","https://lh3.googleusercontent.com/-zMz3pwzb7eYJjObX07yCLdzMf5Za-vfRsGt4nMsmJQXN0t2LYzLHsuKU4Kbc00NthDJoIqWo3SrmkC_-bmJ8k31rjLETFb9bdqyEUI=w600"));
		repoProd.save(new Producto("Monkey 4",97382F,"NFTForMe","Monkey","https://lh3.googleusercontent.com/XTsalDd9S4fQJDPmDFHhQeIiUQe2WDfgHwPuRTSuqkFVki7le_ObTffSK0A23ti1FqduWAr-6V3i3yIX5vDOjWUBqLTDAyVvTnn-=w600"));

		clienteRepo.save(new WebUser("user","pass","USER"));
		clienteRepo.save(new WebUser("admin","adminpass","USER","ADMIN"));*/
	}
}