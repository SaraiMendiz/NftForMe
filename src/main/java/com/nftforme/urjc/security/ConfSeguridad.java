package com.nftforme.urjc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ConfSeguridad  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public RepositorioWebUserAuthProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//páginas públicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/quienessomos").permitAll();
		http.authorizeRequests().antMatchers("/ayuda").permitAll();
		http.authorizeRequests().antMatchers("/productos").permitAll();
		http.authorizeRequests().antMatchers("/productos/{categoria}").permitAll();
		http.authorizeRequests().antMatchers("/productos/ver/{nombre}").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/nuevouser").permitAll();
		
		//Solo Usuario
		http.authorizeRequests().antMatchers("/mispedidos").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/carrito").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/micuenta").hasAnyRole("USER");
		//Solo Admin
		http.authorizeRequests().antMatchers("/nuevoproducto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/resultadonuevo").hasAnyRole("ADMIN");

		
		http.authorizeRequests().anyRequest().authenticated();
		
		//http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		 http.formLogin().passwordParameter("password");
		 http.formLogin().defaultSuccessUrl("/");
		 http.formLogin().failureUrl("/login");

		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/");
		 
		 //http.csrf().disable();

	}
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
	 }
}