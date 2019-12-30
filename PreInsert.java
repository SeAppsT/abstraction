package com.example.beck;

import com.example.beck.domain.Role;
import com.example.beck.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreInsert {

    @Autowired
    public PreInsert(RoleRepository roleRepository){
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null){
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
    }
}