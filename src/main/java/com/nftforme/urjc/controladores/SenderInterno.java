package com.nftforme.urjc.controladores;

import java.util.HashMap;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nftforme.urjc.objetos.Producto;

@Component
public class SenderInterno{

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	public void senderToInterno(Producto temp) {
		
		HashMap<String,String> MESSAGE = new HashMap<>();
		MESSAGE.put("Usuario", temp.getNombre());
		MESSAGE.put("Precio", Float.toString(temp.getPrecio()));
		MESSAGE.put("Autor", temp.getAutor());
		MESSAGE.put("Usuario", temp.getNombre());
		MESSAGE.put("Hash", temp.getHash());
		
		rabbitTemplate.convertAndSend("exchange_name", "routing_key", MESSAGE);
	}
}