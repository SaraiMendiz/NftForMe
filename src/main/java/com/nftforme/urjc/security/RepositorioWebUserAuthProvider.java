package com.nftforme.urjc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nftforme.urjc.controladores.ControladorSesion;
import com.nftforme.urjc.objetos.WebUser;
import com.nftforme.urjc.repositorios.RepositorioWebUser;

@Component
public class RepositorioWebUserAuthProvider implements AuthenticationProvider {
		
	@Autowired
	private UserComponent userLog;
	
	 @Autowired
	 private RepositorioWebUser userRepository;
	 
	 @Override
	 public Authentication authenticate(Authentication auth) throws AuthenticationException {
		 WebUser user = userRepository.findByName(auth.getName());
		 if (user == null) {
			 throw new BadCredentialsException("Usuario no encontrado");
		 }
		 String password = (String) auth.getCredentials();
		 if (!new BCryptPasswordEncoder().matches(password, user.getPasswordHash())) {
			 throw new BadCredentialsException("Contraseña incorrecta");
		 }
		 
		 List<GrantedAuthority> roles = new ArrayList<>();
		 for (String role : user.getRoles()) {
			 roles.add(new SimpleGrantedAuthority(role));
		 }
		 
		 userLog.setLoggedUser(user);
		 return(new UsernamePasswordAuthenticationToken(user.getName(), password, roles));
	 }

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}