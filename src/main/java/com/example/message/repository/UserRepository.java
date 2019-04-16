package com.example.message.repository;

import com.example.message.domain.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String>{
    
    User findByUsername(String username);
    User save(User user);   
}
