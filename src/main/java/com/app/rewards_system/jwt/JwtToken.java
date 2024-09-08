package com.app.rewards_system.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtToken {
    private static final Logger logger= LoggerFactory.getLogger(JwtToken.class);

    @Value("${jwt.secret.key}")
    private String SECRETKEY;

    private Key secretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRETKEY));

    }

   public String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(secretKey())
                .compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey())
                    .build()
                    .parseClaimsJws(token);
            logger.error("JWT claims string is valid: {}", "valid");
            return true;
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token{}", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT token{}", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token{}", e.getMessage());

        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty{}", e.getMessage());
        }
        return false;
    }
    public String getSubject(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
