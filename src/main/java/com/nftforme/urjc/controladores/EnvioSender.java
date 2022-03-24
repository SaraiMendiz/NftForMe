package com.nftforme.urjc.controladores;

import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.repositorios.RepositorioProducto;

@RestController
@RequestMapping("/sender")
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
        return "ok. done";
        
    }
	    
}
