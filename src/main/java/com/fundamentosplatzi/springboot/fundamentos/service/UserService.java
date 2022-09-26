package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransaccional(List<UserEntity> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: " + user))
                .forEach(userRepository::save);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserEntity update(UserEntity newUser, Long id) {
         return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setBirthDate(newUser.getBirthDate());
                    return userRepository.save(user);
                }).get();
    }
}
