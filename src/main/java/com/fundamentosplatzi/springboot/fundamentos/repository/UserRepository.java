package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email=?1")
    Optional<UserEntity> findByUserEmail(String email);

    //esta query indica que buscamos por el nombre cualquier coincidencia que comience por el parametro dado
    @Query("SELECT u from UserEntity u WHERE u.name LIKE ?1%")
    List<UserEntity> findAndSort(String name, Sort sort);

    //Usando queryMethods (Hay un pluggin )
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByNameAndEmail(String name, String email);


}
