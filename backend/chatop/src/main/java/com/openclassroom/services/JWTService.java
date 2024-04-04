package com.openclassroom.services;


import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.openclassroom.models.UserModel;




@Service
public class JWTService {


    private final JwtEncoder jwtEncoder;

	private final String jwtKey;
	
	public JWTService(JwtEncoder jwtEncoder, @Value("${jwt.secret}") String jwtKey) {
        this.jwtEncoder = jwtEncoder;
        this.jwtKey = jwtKey;
    }
	
	public String generateToken(UserModel authentication) {
        		Instant now = Instant.now();
     		JwtClaimsSet claims = JwtClaimsSet.builder()
              		  .issuer("self")
               		 .issuedAt(now)
              		  .expiresAt(now.plus(1, ChronoUnit.DAYS))
              		  .subject(authentication.getName())
              		  .build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}
	
}
	

