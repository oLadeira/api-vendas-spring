package com.lucasladeira.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lucasladeira.services.UsuarioService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/* AuthenticationManagerBuilder = classe responsavel pelo gerenciamento de autenticacao da aplicacao,
	 * é atraves dele que sabemos quem são os usuarios e suas respectivas senhas*/
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(usuarioService)
		.passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	/* metodo configure = responsavel em fazer algumas configuracoes 
	 * por exemplo autorizar urls, habilitar CORS, controle de sessao etc */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //não utilizado em api's		
		.cors() //habilitar cors
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //desabilita a criacao de sessoes, pois vamos utilizar tokens para fazer o controle de sessao		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
