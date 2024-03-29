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
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        var jwtToken = getTokenFromRequest(request);
        if (jwtToken != null){
            try{
                boolean isTokenValid = jwtUtil.validateToken(jwtToken);
                if(isTokenValid){
                    Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
                if(SecurityContextHolder.getContext().getAuthentication() == null){
//                    creating a user object to store it in security context holder
                    User user = new User(claims.getSubject(),claims.get("email").toString(),null,null,null);
                    user.setId(claims.get("id").toString());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                }
            }
            catch (Exception e){
                System.out.println("Exception in doFilterInternal: "+e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
//        Extract authentication header
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

//        Bearer <JWT Token>
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
        return authHeader.substring(7);
        }
        return null;
    }
}
