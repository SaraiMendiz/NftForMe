package com.nftforme.urjc.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@RestController
public class EnvioSender {

	@Autowired
	SenderInterno senderInterno;
	
	@Autowired
	private RepositorioProducto repoProd;
	
	@GetMapping
    public String send(){
		List <Producto> todos = repoProd.findAll();
		for(Producto temp : todos) {
			senderInterno.senderToInterno(temp);
		}
        return "mispedidos";
    }    
}
