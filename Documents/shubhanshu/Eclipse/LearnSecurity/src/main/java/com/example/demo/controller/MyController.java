package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EntityClass;
import com.example.demo.service.ServiceClass;

@RestController
public class MyController {

	@Autowired
	ServiceClass sc;
	
	@PostMapping("/login")
	public String loginuser(@RequestBody EntityClass user) {
		return sc.verify(user);
	}	
	@PostMapping("/register")
	public String registeruser(@RequestBody EntityClass user) {
		return sc.adduser(user);
	}
	
	
}
