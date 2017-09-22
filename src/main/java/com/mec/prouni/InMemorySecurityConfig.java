package com.mec.prouni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.mec.prouni.model.Instituicao;
import com.mec.prouni.repository.Instituicoes;

@Configuration
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Instituicoes instituicoes;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		// Carregar acessos das instituicoes que estao no banco de dados
		List<Instituicao> listaInstituicao = instituicoes.findAll();
		for (Instituicao u : listaInstituicao) {
			builder.inMemoryAuthentication().withUser(u.getNome()).password(u.getHashId()).roles("USER");
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.csrf().disable();
	}

}