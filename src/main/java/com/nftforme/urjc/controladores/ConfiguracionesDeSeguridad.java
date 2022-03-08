package com.nftforme.urjc.controladores;

import org.springframework.context.annotation.Configuration;
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
		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		 http.formLogin().passwordParameter("password");
		 http.formLogin().defaultSuccessUrl("/home");
		 http.formLogin().failureUrl("/loginerror");

		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/");
		 

		//usuario
		//usuario.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
	}
	
}


