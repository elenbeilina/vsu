package com.aqualen.vsu.services;


import com.aqualen.vsu.entity.enums.UserRole;
import com.aqualen.vsu.repository.UserRepository;
import com.aqualen.vsu.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordLogic passwordLogic;

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public User getById(long id) {
        return userRepository.getOne(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }
    public void update(User user){
        userRepository.saveAndFlush(user);
    }
    public void add(User user){
        passwordLogic.encodePassword(user);

        userRepository.saveAndFlush(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
