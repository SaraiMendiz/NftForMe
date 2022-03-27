package com.nftforme.urjc.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.nftforme.urjc.objetos.CarritoCompra;
import com.nftforme.urjc.objetos.PedidosCliente;
import com.nftforme.urjc.objetos.WebUser;

@Service
public interface RepositorioPedidosCliente extends JpaRepository<PedidosCliente,Long>{

	@Override
	Optional<PedidosCliente> findById(Long id);
	List<PedidosCliente> findAllByCliente(WebUser user);
}