package com.hotelmangementapi.demo.service.securityservices;

import com.hotelmangementapi.demo.security.jwt.JwtConfig;
import com.hotelmangementapi.demo.security.jwt.JwtToken;
import com.hotelmangementapi.demo.repository.JwtTokenModelRep;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class JwtTokenServices {

    private final JwtConfig jwtConfig;
    private final JwtTokenModelRep jwtTokenModelRep;

    public String generateToken(Authentication authentication) {
        String refresh_token_id = randomTokenId();
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .claim("refreshId", refresh_token_id)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(jwtConfig.getSecretKeyBytes())
                .compact();
        return token;
    }

    public Claims getTokenClaims(String token) {
        return  Jwts.parser().setSigningKey(jwtConfig.getSecretKeyBytes()).
                parseClaimsJws(token).getBody();
    }

    public boolean tokenValidation(String tokenId) {
        boolean jwtTokenEx = jwtTokenModelRep.existsJwtTokenModelByRefreshTokenId(tokenId);
        return jwtTokenEx;
    }



    public  String randomTokenId() {
        String chars = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
        String numbers = "1234567890";
        Random random = new Random();
        StringBuilder randomToBeReturned = new StringBuilder();
        for (int i = 0 ; i < 35 ; i++) {
            randomToBeReturned.append(chars.charAt(random.nextInt(chars.length())));
        }
        for (int i = 0 ; i < 14 ; i++ ) {
            randomToBeReturned.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return randomToBeReturned.toString();
    };
    public void  saveTokenToUsed(String token) {
        Claims tokenClaims = getTokenClaims(token);
        Object refreshId = tokenClaims.get("refreshId");
        JwtToken jwtToken = new JwtToken((String) refreshId,token,tokenClaims.getSubject(),"false");
        jwtTokenModelRep.save(jwtToken);
    }















}
