package com.groupC.twitter.service.implementation;

import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;



    public User getUser(long userId){
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
    public void deleteUser(long userId){userRepository.deleteById(userId);}

    public void updateUserData(User user){
        userRepository.findById(user.getUserId());
        userRepository.save(user);
    }

    @Override
    public boolean addFollower(long followerId, long userId) {
        User user = userRepository.getReferenceById(userId);
        user.setFollower(followerId);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean removeFollower(long followerId, long userId) {
        User user = userRepository.getReferenceById(userId);
        user.removeFollower(followerId);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getFollowers(long userId) {
        List<User> followers = new ArrayList<>();
        User user = userRepository.getById(userId);
        List<User> users = userRepository.findAllById(user.getFollower().keySet());
        Optional.ofNullable(users)
                .ifPresent(
                        usersList ->
                                usersList.forEach(eachUser -> followers.add(userMapper.transform(eachUser))));
        return followers;
    }

    @Override
    public List<User> getFollowings(long userId) {
        List<User> followings = new ArrayList<>();
        User user = userRepository.getReferenceById(userId);
        List<User> users = userRepository.findAllById(user.getFollowing().keySet());
        Optional.ofNullable(users)
                .ifPresent(
                        usersList ->
                                usersList.forEach(eachUser -> followings.add(userMapper.transform(eachUser))));
        return followings;
    }
}


}
