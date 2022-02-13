package com.nftforme.urjc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface productoRepository extends JpaRepository<categoriaAnimal,Long>{

	List<categoriaAnimal> findByCategoria(String categoria);
	List<categoriaAnimal> findByNombre(String nombre);
	
}
