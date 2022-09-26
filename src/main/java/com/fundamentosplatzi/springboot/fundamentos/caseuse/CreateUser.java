package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public UserEntity save(UserEntity user) {
       return userService.save(user);
    }
}
