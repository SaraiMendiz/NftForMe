package com.nftforme.urjc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface productoRepository extends JpaRepository<Producto,Long>{

	List<Producto> findByNombre(String nombre);
	List<Producto> findByCategoria(String categoria);
	
}
