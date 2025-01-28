package com.example.demo.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {

	private EntityClass s;
	
	public MyUserPrincipal(EntityClass s) {
		System.out.println("hiii-----1");
		System.out.println(s.toString());;
		this.s=s;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		System.out.println("username - "+s.getUsername());
		return s.getUsername();
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		System.out.println("password - "+s.getPassword());
		return s.getPassword();
	}

	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
