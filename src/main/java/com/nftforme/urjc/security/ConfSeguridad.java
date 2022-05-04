package com.nftforme.urjc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ConfSeguridad  extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/error").permitAll();
		http.authorizeRequests().antMatchers("/micuenta").permitAll();
		http.authorizeRequests().antMatchers("/ayuda").permitAll();
		http.authorizeRequests().antMatchers("/quienessomos").permitAll();
		
		http.authorizeRequests().antMatchers("/mispedidos").permitAll();
		http.authorizeRequests().antMatchers("/carrito").permitAll();
		http.authorizeRequests().antMatchers("/buy/{id}").permitAll();
		http.authorizeRequests().antMatchers("/moverapedido").permitAll();
		http.authorizeRequests().antMatchers("/deletebag").permitAll();
		
		http.authorizeRequests().antMatchers("/nuevoproducto").permitAll();
		http.authorizeRequests().antMatchers("/resultadonuevo").permitAll();
		
		http.authorizeRequests().antMatchers("/productos").permitAll();
		http.authorizeRequests().antMatchers("/productos/{categoria}").permitAll();
		http.authorizeRequests().antMatchers("/productos/ver/{nombre}").permitAll();
		http.authorizeRequests().antMatchers("/hash/{id}").permitAll();
		http.authorizeRequests().antMatchers("/deleteproduct/{id}").permitAll();
		
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/nuevouser").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/nuevologin").permitAll();
		
		http.authorizeRequests().anyRequest().authenticated();
	}
}