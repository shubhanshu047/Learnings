package com.example.demo.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Users;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.ApiResponse;
import com.example.demo.Services.JWTservice;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	AuthenticationManager AuthManager;
	
	@Autowired
	UserRepository userRepo;
	
	private BCryptPasswordEncoder bc = new BCryptPasswordEncoder(12);
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> verify(@RequestBody Users user) {
		Authentication auth = AuthManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		try {
			if(auth.isAuthenticated()) {
				String token = JWTservice.getMyToken(user.getUsername()); 
				return ResponseEntity.ok(new ApiResponse(true, "Logged in", token));
			}
			else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, "Invalid username or password",""));
			}
		}catch(BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, "Invalid username or password",""));			// forbidden is 403
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Internal server error", ""));
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> addUser(@RequestBody Users user) {
		user.setPassword(bc.encode(user.getPassword()));
		try {			
			Users added = userRepo.save(user);
			if(added!=null) {
				return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", added.getUsername()));

			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false,"Registration failed, server error",""));
			}
		} catch(DataIntegrityViolationException e) {
			return ResponseEntity.ok(new ApiResponse(false, "Registeration failed, try a different username and ID", e.getMessage()));
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Internal Server Error", e.getMessage()));
        }
	}
	
}
