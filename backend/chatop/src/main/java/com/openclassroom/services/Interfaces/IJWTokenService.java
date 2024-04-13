package com.openclassroom.services.Interfaces;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

public interface IJWTokenService {
	
	String extractEmail(String token);
	
	boolean isValid(String token, String email);
	
	boolean isTokenExpired(String token);
	
	Date extractExpiration(String token);
	
	<T> T extractClaim(String token, Function<Claims, T> resolver);
	
	Claims extractAllClaims(String token);
	
	String generateToken(String email);
	
	SecretKey getSigninKey();
	

}
