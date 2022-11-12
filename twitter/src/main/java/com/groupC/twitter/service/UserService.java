package com.groupC.twitter.service;

import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(Long userId){
        return userRepository.getReferenceById(userId);
    }
    public User getUserByUserName(String userName){
        return userRepository.getReferenceByUserName(userName);
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void updateUser(User user){
        userRepository.findById((user.getUserId()));
        userRepository.save(user);
    }

}
