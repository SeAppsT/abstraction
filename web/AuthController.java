package com.example.beck.web;


import com.example.beck.dto.AuthDto;
import com.example.beck.domain.User;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.service.AuthService;
import com.example.beck.view.AuthViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public AuthViewer login(@RequestBody @Valid AuthDto userData) throws EntityNotFoundException {
        return this.authService.login(userData);
    }

    @DeleteMapping
    public void logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);
    }
}
