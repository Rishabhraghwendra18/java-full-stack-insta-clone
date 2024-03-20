package com.instagram.server.utils;

import com.instagram.server.collection.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "QO1S52zJW5FVHq84B9E6n0c2LW4uGhdOJKqMkG2exBLdwsTLo9cMlzH8pF4fYjR7HzYX8VIxT62pWukIotMv54vDu6PQq8gakqCvdA0GiWmTUSblEyDxLs9PfSNryUx"; // Replace with your actual secret key
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDetails.getEmail());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        System.out.println("secret is: "+SECRET_KEY);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    //validate token
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
