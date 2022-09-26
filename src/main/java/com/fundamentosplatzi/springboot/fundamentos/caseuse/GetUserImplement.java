package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetUserImplement  implements GetUser{

    UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserEntity> getAll() {
        return userService.getAllUsers();
    }
}
