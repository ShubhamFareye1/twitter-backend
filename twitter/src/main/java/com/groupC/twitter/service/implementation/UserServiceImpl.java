package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto getUser(long userId){
        this.userRepository.findById(userId).orElseThrow(()->new NoSuchElementException(("This user Id Does not exist")));
        User user = this.userRepository.findById(userId).get();
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserByUserName(String userName){
        User user = this.userRepository.getReferenceByUserName(userName);
        return this.modelMapper.map(user,UserDto.class);
    }
   
    @Override
    public UserDto addUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        User newUser = this.userRepository.save(user);
        return  this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        User updateUser= this.userRepository.save(user);
        return this.modelMapper.map(updateUser,UserDto.class);
    }
    @Override
    public void deleteUser(long userId){userRepository.deleteById(userId);}

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
    public List<UserDto> getFollowers(long userId) {
        //List<User> users = (List<User>) this.userRepository.getReferenceById(userId);
        //List<UserDto> followers = new ArrayList<>();
        User user = userRepository.getReferenceById(userId);
        List<User> users = userRepository.findAllById(user.getFollower().keySet());
        List<UserDto> followers = users.stream().map((user1)->this.modelMapper.map(user1,UserDto.class))
                .collect(Collectors.toList());
        return followers;
    }


    @Override
    public List<UserDto> getFollowings(long userId) {
        //List<User> users = (List<User>) this.userRepository.getReferenceById(userId);
        //List<UserDto> followings = new ArrayList<>();
        User user = userRepository.getReferenceById(userId);
        List<User> users = userRepository.findAllById(user.getFollowing().keySet());
        List<UserDto> following = users.stream().map((user1)->this.modelMapper.map(user1,UserDto.class))
                .collect(Collectors.toList());
        return following;
    }


}
