package com.nftforme.urjc.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.nftforme.urjc.objetos.WebUser;

public interface RepositorioWebUser extends CrudRepository<WebUser, Long> {
    WebUser findByName(String name);
}
