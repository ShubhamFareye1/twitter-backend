package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.BookmarkDto;
import com.groupC.twitter.dto.MessagesDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.exceptions.UserNameAlredyExistException;
import com.groupC.twitter.exceptions.UserNotFoundException;
import com.groupC.twitter.model.Bookmark;
import com.groupC.twitter.model.Messages;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.BookmarkRepository;
import com.groupC.twitter.repository.MessageRepository;
import com.groupC.twitter.repository.TweetRepository;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public UserDto getUser(long userId){
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.BAD_REQUEST,"User doesn't exist"));
        User user = this.userRepository.findById(userId).get();
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUserName(String userName){
        User user = this.userRepository.getReferenceByUserName(userName);
        if(user==null){
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist");
        }
        return this.modelMapper.map(user,UserDto.class);
    }
   
    @Override
    @Transactional
    public UserDto addUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        User user1 = userRepository.getReferenceByUserName(user.getUserName());
        if(user1!=null){
            throw new UserNameAlredyExistException(HttpStatus.BAD_REQUEST,"User already exist");
        }
        User newUser = this.userRepository.save(user);
        return  this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto){
//        User user = this.modelMapper.map(userDto,User.class);
        User user=this.userRepository.findById(userDto.getUserId()).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        if(userDto.getUserName()!=null)
            user.setUserName(userDto.getUserName());
        if(userDto.getPassword()!=null)
            user.setPassword(userDto.getPassword());
        if(userDto.getAvatar()!=null)
            user.setAvatar(userDto.getAvatar());
        if(userDto.getBannerImage()!=null)
            user.setBannerImage(userDto.getBannerImage());
        if(userDto.getBio()!=null)
            user.setBio(userDto.getBio());
        if(userDto.getName()!=null)
            user.setName(userDto.getName());
        User updateUser= this.userRepository.save(user);
        return this.modelMapper.map(updateUser,UserDto.class);
    }
    @Override
    @Transactional
    public void deleteUser(long userId){
       // getUser(userId);
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public boolean addFollower(long followerId, long userId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        this.userRepository.findById(followerId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        user.setFollower(followerId);
        user.setNumberOfFollower(user.getNumberOfFollower()+1);
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean removeFollower(long followerId, long userId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        this.userRepository.findById(followerId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        user.removeFollower(followerId);
        user.setNumberOfFollower(user.getNumberOfFollower()-1);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDto> getFollowers(long userId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        List<User> users = userRepository.findAllById(user.getFollower().keySet());
        List<UserDto> followers = users.stream().map((user1)->this.modelMapper.map(user1,UserDto.class))
                .collect(Collectors.toList());
        return followers;
    }


    @Override
    public List<UserDto> getFollowings(long userId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        List<User> users = userRepository.findAllById(user.getFollowing().keySet());
        List<UserDto> following = users.stream().map((user1)->this.modelMapper.map(user1,UserDto.class))
                .collect(Collectors.toList());
        return following;
    }

    @Override
    @Transactional
    public boolean addFollowing(long userId, long followingId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        this.userRepository.findById(followingId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        user.setFollowing(followingId);
        user.setNumberOfFollowing(user.getNumberOfFollowing()+1);
        userRepository.save(user);
        return addFollower(userId,followingId);

    }

    @Override
    @Transactional
    public boolean deleteFollowing(long userId, long followingId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        this.userRepository.findById(followingId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        user.removeFollowing(followingId);
        user.setNumberOfFollowing(user.getNumberOfFollowing()-1);
        return removeFollower(userId,followingId);
    }

    @Override
    public List<UserDto> topTenUser() {
        List<User> users = userRepository.findTop10ByOrderByNumberOfFollowerDesc();
        List<UserDto>userDtos = users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    @Transactional
    public void requestBluetick(long userId) {
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        User user = userRepository.getReferenceById(userId);
        user.setIsVerified(2);
        userRepository.save(user);
    }


    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.searchByName("%"+keyword+"%");
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }


}
