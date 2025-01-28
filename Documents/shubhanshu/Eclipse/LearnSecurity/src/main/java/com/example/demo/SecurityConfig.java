package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration										// This class is a configuration class
@EnableWebSecurity									// Pick the spring security filters from here
public class SecurityConfig {

	@Bean											// below method is a bean providing security filters
	public SecurityFilterChain Applyfilter(HttpSecurity sec) throws Exception {
		sec.csrf(Customizer -> Customizer.disable());																		// No need to mention csrf token for post,put,delete
		sec.authorizeHttpRequests(Customizer -> Customizer.anyRequest().authenticated());									// check the user authenticated or not before serving each request					
//		sec.formLogin(Customizer.withDefaults());																			// show a login form on every request
		sec.httpBasic(Customizer.withDefaults());																			// show the username password validation
		sec.sessionManagement(Customizer -> Customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));				// Mention wether the user session will be stateless or stateful
		
		return sec.build();		 																							// return a filter chain
	}
	
}
