package com.nftforme.urjc.repositorios;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.nftforme.urjc.objetos.WebUser;

public interface RepositorioWebUser extends CrudRepository<WebUser, Long> {
    Optional<WebUser> findByName(String name);
}
