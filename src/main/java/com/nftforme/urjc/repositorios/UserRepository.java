package com.nftforme.urjc.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.nftforme.urjc.objetos.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
