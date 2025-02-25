package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigi{
	
	@Autowired
	UserDetailsService UDS;
	
	@Bean
	public SecurityFilterChain myChain(HttpSecurity http) throws Exception {
		http.csrf(Customizer->Customizer.disable());
		http.authorizeHttpRequests(Customizer->Customizer.requestMatchers("login","register").permitAll().anyRequest().authenticated());
		http.httpBasic(Customizer.withDefaults());
//		http.formLogin(Customizer.withDefaults());
		http.sessionManagement(Customizer->Customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider myAuthPro() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(UDS);
//		dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		dao.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return dao;
	}
	
}