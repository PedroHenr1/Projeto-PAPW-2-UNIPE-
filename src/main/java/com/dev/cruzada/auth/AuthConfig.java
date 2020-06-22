package com.dev.cruzada.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dev.cruzada.service.UsuarioAuthService;
import com.dev.cruzada.auth.Filters.ConstantsSecurity;
import com.dev.cruzada.auth.Filters.JWTAuthenticationFilter;
import com.dev.cruzada.auth.Filters.JWTAuthorizationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UsuarioAuthService usuarioAuthService;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		System.out.println("'''''''''''''''''''''''''''''''''------------------------------");
		//http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, ConstantsSecurity.SIGN_UP_URL).permitAll()
			.antMatchers(HttpMethod.POST, "/cruzada/app/user").permitAll()
				.antMatchers(HttpMethod.GET, "/cruzada/app/produto").permitAll()
			//	.antMatchers(HttpMethod.GET, "/cruzada/app/user").permitAll()
			.antMatchers("/cruzada/admin/**").hasRole("ADMIN")
			.antMatchers("/cruzada/app/**").hasRole("USER")
				.antMatchers("/login").permitAll()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(), usuarioAuthService))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), usuarioAuthService));

	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		System.out.println("'''''''''''''''''''''''''''''''''------------------------------2");
		auth.userDetailsService(usuarioAuthService).passwordEncoder(new BCryptPasswordEncoder());
		System.out.println(usuarioAuthService);
	}
}
