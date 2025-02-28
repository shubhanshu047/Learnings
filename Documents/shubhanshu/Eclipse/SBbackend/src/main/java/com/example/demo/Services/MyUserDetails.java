package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Model.MyUserPrinciples;
import com.example.demo.Model.Users;
import com.example.demo.Repositories.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.getUserByUsername(username);
		if(user == null) {
			System.out.println("Username Not Found");
			throw new UsernameNotFoundException("Username not found");
		}else {
			return new MyUserPrinciples(user);
		}
	}

}
