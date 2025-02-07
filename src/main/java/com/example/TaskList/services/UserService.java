package com.example.TaskList.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TaskList.model.User;
import com.example.TaskList.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user){
        try {
            userRepository.save(user);
            return user;    
        } catch (Exception e) {
            throw new Error("Erro ao criar usuário: " + e);
        }
    }

    public List<User> list(){
        return userRepository.findAll();
    }
    

}
