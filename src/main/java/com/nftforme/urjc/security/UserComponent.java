package com.nftforme.urjc.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioActualUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;

@Component
@SessionScope
public class UserComponent {
	
	@Autowired
	private RepositorioActualUser userLog;
	
	@Autowired 
	private RepositorioWebUser repoUser;
	
	
	public WebUser getLoggedUser() {
		Optional<WebUser> temp = null;
		if(userLog.findAll().size()!=0) {
			temp = repoUser.findByName(userLog.findAll().get(0).getName());
			if(temp.isPresent()) {
				return temp.get();
			}else {
				return null;
			}
		}else {
			return null;
		}		
	}
	
	public boolean isAdmin() {
		if(userLog.findAll().size()!=0) {
			if(repoUser.findByName(userLog.findAll().get(0).getName()).get().getRoles().contains("ADMIN")) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public void logout() {
		userLog.deleteById(userLog.findAll().get(0).getId());
	}
}
