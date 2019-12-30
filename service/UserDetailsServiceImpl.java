package com.example.beck.service;

import com.example.beck.domain.Role;
import com.example.beck.domain.User;
import com.example.beck.repository.UserRepository;
import com.example.beck.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User foundedUser = this.userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
        if (foundedUser == null)
            throw new UsernameNotFoundException("User not found");
        return this.castToSecurityUser(foundedUser);
    }

    private SecurityUser castToSecurityUser(User user){
        return new SecurityUser(user, this.castToGrantedAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> castToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}