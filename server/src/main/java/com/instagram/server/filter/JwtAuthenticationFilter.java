package com.instagram.server.filter;

import com.instagram.server.collection.User;
import com.instagram.server.utils.JwtUtil;
import com.mongodb.lang.NonNullApi;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    public JwtAuthenticationFilter(){}
    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3001");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST , PUT , DELETE , OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
//        response.setHeader("Cache-Control","no-transform, public, max-age=86400");
        System.out.println("Request is: "+request.getHeader(HttpHeaders.AUTHORIZATION));
        String jwtToken = getAuthorizationCookie(request);
        jwtToken = getTokenFromRequest(jwtToken);
        System.out.println("JWT Token123: "+jwtToken);
        if (jwtToken != null){
            try{
                boolean isTokenValid = jwtUtil.validateToken(jwtToken);
                System.out.println("TOken is valid: "+isTokenValid);
                if(isTokenValid){
                    Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    System.out.println("In security context check");
//                    creating a user object to store it in security context holder
                    User user = new User(claims.getSubject(),claims.get("email").toString(),null,null,null);
                    user.setId(claims.get("id").toString());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("Setted the security context: "+auth.toString());
                }
                }
            }
            catch (Exception e){
                System.out.println("Exception in doFilterInternal: "+e.getMessage());
            }
        }
        System.out.println("Going to next filter");
        filterChain.doFilter(request, response);
    }
    private String getAuthorizationCookie(HttpServletRequest request){
        var cookies = request.getCookies();
        if(cookies == null) return null;
        for (var cookie: cookies){
            if (cookie.getName().equals("Authorization")){
                return cookie.getValue();
            }
        }
        return null;
    }
    private String getTokenFromRequest(String authHeader) {
        if (authHeader == null) return null;

//        Bearer <JWT Token>
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer%20")){
            // %20 in end because after Bearer there is space which is replaced by %20 in cookie
        return authHeader.substring(9);
        }
        return null;
    }
}
