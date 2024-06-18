package com.asistencia.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JwtProvider {
    static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        //Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .signWith(key).compact();
    }

    public static String getEmailFromToken(String jwt) {
        //    String authorities=String.valueOf(claims.get("authorities"));
        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }

//    public static String getAuthoritiesFromToken(String jwt){
//        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//        return String.valueOf(claims.get("authorities"));
//    }

    public static LocalDateTime getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expirationDate = claims.getExpiration();
        return expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
