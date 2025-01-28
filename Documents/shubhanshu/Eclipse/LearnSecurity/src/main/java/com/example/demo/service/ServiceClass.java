package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.EntityClass;
import com.example.demo.repo.UserRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ServiceClass {

	@Autowired
	UserRepo rp;
	
	private BCryptPasswordEncoder bc = new BCryptPasswordEncoder(12);			// 12 is the strength.. means number of rounds in encrypting.
	
	public String adduser(EntityClass user) {
		user.setPassword(bc.encode(user.getPassword()));
		System.out.println(user.toString());
		EntityClass added = rp.save(user);
		if(added!=null) {
			return "User registered successfuly";
		}
		else {
			return "User not registered";
		}
	}
	
	
}
