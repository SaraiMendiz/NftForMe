package com.nftforme.urjc.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nftforme.urjc.objetos.WebUser;

@Service
public interface RepositorioUsers extends JpaRepository<WebUser,Long>{

	Optional<WebUser> findByName(String name);
}
