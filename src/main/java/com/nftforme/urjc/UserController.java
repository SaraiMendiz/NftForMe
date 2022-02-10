package com.nftforme.urjc;
import cliente.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/posts") 
public class UserController {

	 
		 @Autowired
		 private ClienteRepository posts;
		 
		 @PostConstruct
		 public void init() {
			 posts.save(new Cliente("Sergio",1));
		 }
		 
		 
		 @GetMapping("/")
		 public Page<Cliente> getPosts(
		 @RequestParam(required = false) String user, Pageable page) {
			 
		 
			 return posts.findBynombre(user);
		 
		 
	}
		 
		 
}

