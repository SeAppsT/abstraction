package com.example.beck.service;

import com.example.beck.domain.User;
import com.example.beck.dto.AuthDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.provider.JwtTokenProvider;
import com.example.beck.repository.UserRepository;
import com.example.beck.view.AuthViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public AuthViewer login(AuthDto userData) throws EntityNotFoundException {
        String login = userData.getLogin();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, userData.getPassword()));
        System.out.println("Authentication... ");
        User user = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
        System.out.println("Generating token...");
        String token = jwtTokenProvider.createToken(login, user.getRoles());
        System.out.println("SUCCESS");
        return new AuthViewer(user, token);
    }
}