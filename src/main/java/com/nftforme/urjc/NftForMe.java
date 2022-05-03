package com.nftforme.urjc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class NftForMe {
	
	public static final String EXCHANGE_NAME = "exchange_name";
    public static final String ROUTING_KEY = "routing_key";
    private static final String QUEUE_NAME = "queue_name";
    private static final boolean IS_DURABLE_QUEUE = false;
	
    public static void main(String[] args){
		SpringApplication.run(NftForMe.class, args);
	}
	 @Bean
	    Queue queue() {
	        return new Queue(QUEUE_NAME, IS_DURABLE_QUEUE);
	    }
	    
	    @Bean
	    TopicExchange exchange() {
	        return new TopicExchange(EXCHANGE_NAME);
	    }
	    
	    @Bean
	    Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	    }
	    
	

}