package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {

	@GetMapping("/home")
	public String gethome(HttpServletRequest http) {
		return "This is home page "+http.getSession().getId();
	}
	
	@GetMapping("getcsrf")
	public String giveCsrf(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        return csrfToken != null ? "CSRF Token - " + csrfToken.getToken() : "CSRF Token not found!";
    }
	
	@PostMapping("/products")
	public String getproduct() {
		return "This is product page.";
	}
	
}
