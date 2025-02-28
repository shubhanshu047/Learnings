package com.example.demo.Services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
	
	private static String secretKey="";
	
	public JWTservice(){
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey key = keygen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getMyToken(String username) {
		Map<String,Object> claim = new HashMap<>();
		return Jwts.builder()
				.claims()
				.add(claim)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 30 * 30))
				.and()
				.signWith(mykey())
				.compact();
	}

	public static SecretKey mykey() {
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean validateToken(String token, UserDetails userdetails) {
		final String userName=extractUsername(token);
		return (userName.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		
		final Claims claims = Jwts.parser()
				.verifyWith(mykey())
				.build()
				.parseSignedClaims(token)
				.getPayload();

		return claimResolver.apply(claims);
	}
	
	private boolean isTokenExpired(String token) {
		return extractClaim(token,Claims::getExpiration).before(new Date());
	}

//	@Bean
//	public UserDetailsService UserDetailsService() {
//		UserDetails ud = User
//				.withDefaultPasswordEncoder()
//				.username("shubham")
//				.password("singh")
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(ud);
//	}

}
