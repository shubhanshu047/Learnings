package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration																												// This class is a configuration class
@EnableWebSecurity																											// Pick the spring security filters from here
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean																													// below method is a bean providing security filters
	public SecurityFilterChain Applyfilter(HttpSecurity sec) throws Exception {
		sec.csrf(Customizer -> Customizer.disable());																		// No need to mention csrf token for post,put,delete
		sec.authorizeHttpRequests(Customizer -> Customizer.requestMatchers("login","register").permitAll().anyRequest().authenticated());									// check the user authenticated or not before serving each request					
//		sec.formLogin(Customizer.withDefaults());																			// show a login form on every request
		sec.httpBasic(Customizer.withDefaults());																			// show the username password validation
		sec.sessionManagement(Customizer -> Customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));				// Mention wether the user session will be stateless or stateful
		
		return sec.build();		 																							// return a filter chain
	}
	
	@Bean
	public AuthenticationProvider Myauthprovider() {
		DaoAuthenticationProvider pro = new DaoAuthenticationProvider();
		pro.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		pro.setPasswordEncoder(new BCryptPasswordEncoder(12));
		pro.setUserDetailsService(userDetailsService);
		return pro;
	}
	
	@Bean
	public AuthenticationManager authenticationmanager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
//	@Bean
//	public UserDetailsService UserDetailsService() {
//		UserDetails ud = User
//				.withDefaultPasswordEncoder()
//				.username("shubham")
//				.password("singh")
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(ud);
//	}
	
}
