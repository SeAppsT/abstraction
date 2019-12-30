package com.example.beck.manager;

import com.example.beck.domain.Role;
import com.example.beck.domain.User;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.exception.SuchEntityExistsException;
import com.example.beck.repository.RoleRepository;
import com.example.beck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    public void addUser(User user) throws SuchEntityExistsException, EntityNotFoundException {
        User mockUser = new User();
        User foundedUser = this.userRepository.findByLogin(user.getLogin()).orElse(mockUser);
        if (!foundedUser.equals(mockUser))
            throw new SuchEntityExistsException(foundedUser);
        System.out.println("Creating new user...");
        Role roleUser = this.roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        this.userRepository.save(user);
        System.out.println("User created. Login: " + user.getLogin() + ", Password: " + user.getPassword() + ".");
    }

    public void editUser(User user){
        
    }
}