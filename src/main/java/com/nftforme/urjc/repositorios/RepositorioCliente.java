package com.nftforme.urjc.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nftforme.urjc.objetos.Cliente;

@Service
public interface RepositorioCliente extends JpaRepository<Cliente, Long> {

	List<Cliente> findBynombre(String user);
	List<Cliente> findById(int id) ;	
}
