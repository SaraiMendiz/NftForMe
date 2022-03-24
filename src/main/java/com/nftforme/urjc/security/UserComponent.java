package com.nftforme.urjc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.nftforme.urjc.controladores.ControladorSesion;
import com.nftforme.urjc.objetos.WebUser;

@Component
@SessionScope
public class UserComponent {
	
	@Autowired
	private ControladorSesion control;

	private WebUser user;
	
	public WebUser getLoggedUser() {
		return user;
	}
	
	public void setLoggedUser(WebUser user) {
		this.user=user;
		control.setCurrentUser(user);
	}
	
	public boolean isLoggedUser() {
		if(this.user!=null) {
			return true;
		}else {
			return false;
		}
	}
}
