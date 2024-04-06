package com.openclassroom.services;


import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.openclassroom.models.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    
    private final String SECRET_KEY = "4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";
    
//    private final TokenRepository tokenRepository;
//
//    public JWTokenService(TokenRepository tokenRepository) {
//        this.tokenRepository = tokenRepository;
//    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isValid(String token, UserModel user) {
        String username = extractEmail(token);

//        boolean validToken = tokenRepository
//                .findByToken(token)
//                .map(t -> !t.isLoggedOut())
//                .orElse(false);

        return (username.equals(user.getEmail())) && !isTokenExpired(token) ; //&& validToken
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String generateToken(UserModel user) {
        String token = Jwts
                .builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    }