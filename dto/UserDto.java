package com.example.beck.dto;

import com.example.beck.annotations.Dto;
import com.example.beck.domain.BaseEntity;
import com.example.beck.domain.User;

@Dto
public class UserDto extends BaseDto {

    public String login;
    public String password;

    @Override
    public BaseEntity cast(BaseEntity entity) {
        User user = (User) entity;
        user.setName(this.name);
        user.setLogin(this.login);
        user.setPassword(this.password);
        return null;
    }
}