package com.nftforme.urjc.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.nftforme.urjc.objetos.ActualUser;

@Service
public interface RepositorioActualUser extends JpaRepository <ActualUser,Long>{
	List<ActualUser> findAll();
}
