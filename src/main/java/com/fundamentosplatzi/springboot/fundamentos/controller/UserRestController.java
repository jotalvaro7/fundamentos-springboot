package com.fundamentosplatzi.springboot.fundamentos.controller;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private UserRepository userRepository;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<UserEntity> get() {
        return getUser.getAll();
    }

    @PostMapping()
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        return new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody UserEntity user, @PathVariable Long id) {

        return new ResponseEntity(updateUser.update(user, id), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<UserEntity> getUsersPageable(@RequestParam int page, @RequestParam int size){
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
