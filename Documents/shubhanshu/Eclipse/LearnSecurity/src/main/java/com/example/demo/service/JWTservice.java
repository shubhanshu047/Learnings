package com.example.demo.service;

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
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {

	private static String secretKey="";
	
	public JWTservice() {
		try {
			KeyGenerator KeyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = KeyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getMyToken(String username) {
		Map<String,Object> claims=new HashMap<>();
		
		return Jwts.builder()
		.claims()
		.add(claims)
		.subject(username)
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis() + 60 * 30 * 30))
		.and()
		.signWith(myKey())
		.compact();
	}
	
	public static SecretKey myKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	
	// Validating the token
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims=Jwts
				.parser()
				.verifyWith(myKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		return claimResolver.apply(claims);
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean validateToken(String token, UserDetails userdetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userdetails.getUsername()) && !isTokenExpired(token) );
	}
	
}
