package com.example.beck.provider;

import com.example.beck.domain.User;
import com.example.beck.repository.UserRepository;
import com.example.beck.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class AuditorAwareProvider implements AuditorAware<User> {

    private final UserRepository userRepository;

    @Autowired
    public AuditorAwareProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        return Optional.of(securityUser.getDomainUser());
    }
}