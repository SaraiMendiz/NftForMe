package com.nftforme.urjc.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.Producto;

@Service
public interface RepositorioCarroCompra extends JpaRepository <CarritoCompra,Long>{
	Optional<CarritoCompra> findByProducto(Optional<Producto> optional);
}
