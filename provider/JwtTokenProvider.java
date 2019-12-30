package com.example.beck.provider;

import com.example.beck.domain.Role;
import com.example.beck.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider{

    @Value("${token.actiontime}")
    private int actionTime;

    @Value("${token.secretkey}")
    private String secretKey;

    private UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<Role> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", this.getRolesNames(roles));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + this.actionTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.getLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getLogin(String token){
        return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null)
            return token;
        else
            return null;
    }

    public boolean isTokenValid(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("JWT token is expired or invalid");
        }
    }

    public List<String> getRolesNames(List<Role> userRoles){
        List<String> roleNames = new ArrayList<>();
        userRoles.forEach(role -> {
            roleNames.add(role.getName());
        });
        return roleNames;
    }
}