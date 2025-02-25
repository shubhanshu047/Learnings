package com.example.demo.Services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

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

}
