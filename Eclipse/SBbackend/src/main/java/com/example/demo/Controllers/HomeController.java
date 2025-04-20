package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
	
	@RequestMapping("/")
	public String meth() {
		return "This is a meth";
	}
	
	@RequestMapping("/about")
	public String aboutMeth() {
		return "This is About page";
	}
	
}
