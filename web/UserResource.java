package com.example.beck.web;

import com.example.beck.domain.User;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.exception.SuchEntityExistsException;
import com.example.beck.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    private UserManager userManager;

    @Autowired
    public UserResource(UserManager userManager){
        this.userManager = userManager;
    }

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userManager.getAll();
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) throws EntityNotFoundException, SuchEntityExistsException {
        this.userManager.addUser(user);
        return ResponseEntity.ok().build();
    }
}