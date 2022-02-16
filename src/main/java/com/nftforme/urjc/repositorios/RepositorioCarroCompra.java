package com.nftforme.urjc.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nftforme.urjc.objetos.CarritoCompra;

@Service
public interface RepositorioCarroCompra extends JpaRepository <CarritoCompra,Long>{

	List<CarritoCompra> findBynombreArticulo(String nombre);
}
