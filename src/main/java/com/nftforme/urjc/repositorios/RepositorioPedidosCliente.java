package com.nftforme.urjc.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nftforme.urjc.objetos.PedidosCliente;

@Service
public interface RepositorioPedidosCliente extends JpaRepository<PedidosCliente,Long>{

	@Override
	Optional<PedidosCliente> findById(Long id);
}