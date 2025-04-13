package com.example.demo.Config;

import java.util.List;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigi{
	
	@Autowired
	private JwtFilter jwtfilter;
	
	@Autowired
	private UserDetailsService UDS;
	
	@Bean
	public SecurityFilterChain myChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults());
		http.csrf(Customizer->Customizer.disable());
		http.authorizeHttpRequests(Customizer->Customizer.requestMatchers("/login","/register").permitAll().anyRequest().authenticated());
//		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		http.httpBasic(Customizer.withDefaults());
//		http.formLogin(Customizer.withDefaults());
		http.sessionManagement(Customizer->Customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
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
	
	@Bean
	public AuthenticationManager myAuthManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://localhost:5173")); // 👈 your React origin
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
	    configuration.setAllowCredentials(true); // 👈 important for cookies or auth headers

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	
}