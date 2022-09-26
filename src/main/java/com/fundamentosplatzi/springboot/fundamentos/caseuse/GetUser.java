package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;

import java.util.List;

public interface GetUser {
    List<UserEntity> getAll();
}
