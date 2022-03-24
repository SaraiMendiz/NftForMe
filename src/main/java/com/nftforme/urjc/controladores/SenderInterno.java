package com.nftforme.urjc.controladores;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nftforme.urjc.objetos.Producto;




@Component
public class SenderInterno{

	
	
	@Autowired
    RabbitTemplate rabbitTemplate;
	
   
    
	public void senderToInterno(Optional<Producto> temp) {
		
		HashMap<String,String> MESSAGE = new HashMap<>();
		MESSAGE.put("Usuario", temp.get().getNombre());
		MESSAGE.put("Precio", Float.toString(temp.get().getPrecio()));
		MESSAGE.put("Autor", temp.get().getAutor());
		MESSAGE.put("Usuario", temp.get().getNombre());
		MESSAGE.put("Hash", temp.get().getHash());
		
		rabbitTemplate.convertAndSend("exchange_name", "routing_key", MESSAGE);
		
	}
	
	
}
