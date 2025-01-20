package com.example.SBoot1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@RequestMapping("/login")
	public String loginMeth() {
		return "This is login page";
	}
	
}
