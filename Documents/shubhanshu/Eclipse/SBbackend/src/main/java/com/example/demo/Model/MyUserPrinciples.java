package com.example.demo.Model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


public class MyUserPrinciples implements UserDetails {

	private Users user;
	
	public MyUserPrinciples(Users user) {
		System.out.println(user.toString());
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getPassword() {
		System.out.println("password - "+user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.println("username - "+user.getUsername());
		return user.getUsername();
	}

}
