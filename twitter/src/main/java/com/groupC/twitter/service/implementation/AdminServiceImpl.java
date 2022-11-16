package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.exceptions.UserNotFoundException;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getRequestBluetick() {
        List<User> requestList =  userRepository.findByIsVerified(2);
        List<UserDto> request = requestList.stream().map((user1)->this.modelMapper.map(user1,UserDto.class))
                .collect(Collectors.toList());
        return request;
    }

    @Override
    public boolean setBluetick(long userId,boolean resp) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
//        User user = userRepository.getReferenceById(userId);
        if(resp==true) {
            user.setIsVerified(3);
        }
        else{
            user.setIsVerified(1);
        }
        userRepository.save(user);
        return true;
    }
}
