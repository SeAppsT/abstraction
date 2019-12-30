package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.User;

@Viewer
public class AuthViewer {
    public String token;
    public User user;

    public AuthViewer(User user, String token){
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}