package com.example.TaskList.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaskList.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}