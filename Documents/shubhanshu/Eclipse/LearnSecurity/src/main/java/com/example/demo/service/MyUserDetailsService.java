package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.EntityClass;
import com.example.demo.model.MyUserPrincipal;
import com.example.demo.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EntityClass student = ur.getByUsername(username);
		if(student == null) {
			System.out.println("Username Not Found");
			throw new UsernameNotFoundException("Username Not Found");
		}
		else {
			return new MyUserPrincipal(student);
		}
	}

}
