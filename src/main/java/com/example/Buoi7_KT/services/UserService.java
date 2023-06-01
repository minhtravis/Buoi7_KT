package com.example.Buoi7_KT.services;

import com.example.Buoi7_KT.entity.User;
import com.example.Buoi7_KT.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public void save(User user){
        userRepository.save(user);
    }
}
