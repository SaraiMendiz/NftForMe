package com.nftforme.urjc.controladores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;

@Component
public class controladorGuardarUsuarios {
	 
	  @Autowired
	 private RepositorioWebUser userRepository; 
	  
	  //@PostConstruct
/*	 private void initDatabase() { 
		  userRepository.save(new User("user","pass","ROLE_USER"));
		  userRepository.save(new User("admin","adminpass","ROLE_USER","ROLE_ADMIN"));
	 } */
	  
}

