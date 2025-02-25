package com.example.demo.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Users;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.JWTservice;

@RestController
public class LoginController {
	
	@Autowired
	AuthenticationManager AuthManager;
	
	@Autowired
	UserRepository userRepo;
	
	private BCryptPasswordEncoder bc = new BCryptPasswordEncoder(12);
	
	@PostMapping("/login")
	public String verify(@RequestBody Users user) {
		Authentication auth = AuthManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		
		if(auth.isAuthenticated()) {
			return JWTservice.getMyToken(user.getUsername()); 
		}
		else {
			return "User not recognised";
		}
	}
	
	@PostMapping("/register")
	public String addUser(@RequestBody Users user) {
		user.setPassword(bc.encode(user.getPassword()));
		Users added = userRepo.save(user);
		if(added!=null) {
			return "User registered Successfully";
		}else {
			return "User not registered";
		}
	}
	
}
