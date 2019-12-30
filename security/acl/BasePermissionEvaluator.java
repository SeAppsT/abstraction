package com.example.beck.security.acl;

import com.example.beck.domain.User;
import com.example.beck.repository.UserRepository;
import com.example.beck.service.UserDetailsServiceImpl;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class BasePermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object model, Object permission) {

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
