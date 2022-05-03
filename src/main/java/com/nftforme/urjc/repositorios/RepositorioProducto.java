package com.nftforme.urjc.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.nftforme.urjc.objetos.Producto;

@Service
public interface RepositorioProducto extends JpaRepository<Producto,Long>{

	List<Producto> findByNombre(String nombre);
	List<Producto> findByCategoria(String categoria);
}
