package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email=?1")
    Optional<UserEntity> findByUserEmail(String email);

    //esta query indica que buscamos por el nombre cualquier coincidencia que comience por el parametro dado
    @Query("SELECT u from UserEntity u WHERE u.name LIKE ?1%")
    List<UserEntity> findAndSort(String name, Sort sort);

    //Usando queryMethods (Hay un pluggin )
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByNameAndEmail(String name, String email);

    List<UserEntity> findByNameLike(String name);

    List<UserEntity> findByNameOrEmail(String name, String email);

    List<UserEntity> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //Los tres metodos siguientes hacen los mismo la diferencia es como se le pasa el valor por el metodo
    List<UserEntity> findByNameLikeOrderByIdDesc(String name);
    List<UserEntity> findByNameContainingOrderByIdDesc(String name);
    List<UserEntity> findByNameContainsOrderByIdAsc(String name);
    //*************************************************************************************

    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
            " FROM UserEntity u" +
            " WHERE u.birthDate=:parametroFecha " +
            " AND u.email=:parametroEmail ")
    List<UserDto> encontrarUsuarioConDTO(@Param("parametroFecha") LocalDate date,
                                         @Param("parametroEmail") String email);



    List<UserEntity> findAll();

}
