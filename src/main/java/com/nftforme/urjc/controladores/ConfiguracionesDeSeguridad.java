package com.nftforme.urjc.controladores;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class ConfiguracionesDeSeguridad  extends WebSecurityConfigurerAdapter{
	
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
		
		//Solo Usuario
		http.authorizeRequests().antMatchers("/mispedidos").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/carrito").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/micuenta").hasAnyRole("USER");
		//Solo Admin
		http.authorizeRequests().antMatchers("/nuevoproducto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/resultadonuevo").hasAnyRole("ADMIN");

		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		 http.formLogin().passwordParameter("password");
		 http.formLogin().defaultSuccessUrl("/home");
		 http.formLogin().failureUrl("/loginerror");

		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/");
		 
		 //http.csrf().disable();

	}
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	 // User
	 auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
	 //admin
	 auth.inMemoryAuthentication().withUser("admin").password("adminpass").roles("USER", "ADMIN");

	 }

	
}


