package com.example.beck.dto;

import com.example.beck.annotations.Dto;

import javax.validation.constraints.NotBlank;

@Dto
public class AuthDto {

    @NotBlank(message = "Login can't be empty")
    private String login;

    @NotBlank(message = "Password can't be empty")
    private String password;

    public AuthDto(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
