package com.nftforme.urjc.repositorios;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.Producto;
import com.nftforme.urjc.objetos.WebUser;

@Service
public interface RepositorioCarroCompra extends JpaRepository <CarritoCompra,Long>{
	Optional<CarritoCompra> findByProducto(Optional<Producto> optional);
	List<CarritoCompra> findAllByCliente(WebUser user);
}
